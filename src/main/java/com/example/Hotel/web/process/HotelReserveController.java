package com.example.Hotel.web.process;

import com.example.Hotel.domain.auth.UserEntity;
import com.example.Hotel.domain.auth.UserMapper;
import com.example.Hotel.domain.payment.PaymentInfoEntity;
import com.example.Hotel.domain.payment.PaymentService;
import com.example.Hotel.domain.top.HotelEntity;
import com.example.Hotel.domain.top.HotelService;
//import jakarta.servlet.http.HttpSession;
import com.example.Hotel.web.IndexController;
import com.example.Hotel.web.payment.ConvertPaymentInfoToRerveForm;
import com.example.Hotel.web.payment.PayInfoListForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes(types = {ReserveForm.class, HotelEntity.class})
public class HotelReserveController {


    private HttpSession session;

    @Autowired
    private IndexController indexController;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PaymentInfoEntity paymentInfoEntity;

    @Autowired
    private ConfirmToPay confirmToPay;

    @Autowired
    private ReserveForm reserveForm;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MessageSource messageSource;

    @ModelAttribute(value = "reserveForm")
    public ReserveForm setUpReserveForm(){
        return reserveForm;
    }

    /* 予約確認*/
    @PostMapping("/reserve")
    public String reserveConfirm(Model model,
                                 @ModelAttribute("reserveForm") @Validated ReserveForm reserveForm,
                                 BindingResult bindingResult,
                                 Long hotel_id,
                                 int stayLength,
                                 RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            // バリデーションエラーがある場合、リダイレクトしてエラーを渡す
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.reserveForm", bindingResult);
            redirectAttributes.addFlashAttribute("reserveForm", reserveForm);

            // エラー時のリダイレクト
            return "redirect:/detail/" + hotel_id;
        }

        HotelEntity hotel_get = hotelService.getHotelById(hotel_id);

        /* 宿泊費の合計を計算 */
        int totalPrice = stayLength * hotel_get.getPrice() * reserveForm.getGuestNum();
        reserveForm.setTotalPrice(totalPrice);

        /* 宿泊日に、既に予約している場合（予約確認画面にて内容表示とホテル予約情報画面へ遷移リンク）*/
        /* 1.未来日にて予約しているホテルエンティティ取得 */
        // ログインユーザ名を取得可能！
        //ログインユーザ情報を取得する処理
        UserEntity user = getLoginUser();
        //ログインユーザの予約ホテル取得
        List<PaymentInfoEntity> payInfoList = paymentService.findReserveInfo(user.getId());

        List<PaymentInfoEntity> duplicateList = new ArrayList<>();


        for(PaymentInfoEntity paymentInfo  : payInfoList) {
            if(reserveForm.getCheckin_date().before(paymentInfo.getReserveDateTo()) &&
                    reserveForm.getCheckout_date().after(paymentInfo.getReserveDateFrom())
                || reserveForm.getCheckin_date().equals(paymentInfo.getReserveDateFrom())
                || reserveForm.getCheckout_date().equals(paymentInfo.getReserveDateTo())){

                duplicateList.add(paymentInfo);
           }

        }

        ConvertPaymentInfoToRerveForm convertPaymentInfoToRerveForm = new ConvertPaymentInfoToRerveForm();

        //List<PaymentInfoEntity> → List<payInfoListForm>
        List<PayInfoListForm> payInfoListFormList = convertPaymentInfoToRerveForm.convert(duplicateList);

        model.addAttribute("payInfoListFormList", payInfoListFormList);
        model.addAttribute("reserveForm", reserveForm);
        model.addAttribute("hotel", hotel_get);
        model.addAttribute("stayLength", stayLength);

        return "hotel/reserveConfirm";
    }

    /* 決済→トップページへ遷移　*/
    @PostMapping("/pay")
    public String payProcess(Model model,
                             @ModelAttribute("reserveForm") ReserveForm reserveForm,
                             @ModelAttribute("hotel") HotelEntity hotel,
                             RedirectAttributes redirectAttributes) {

        paymentInfoEntity = confirmToPay.confirmToPay(reserveForm, hotel);
        paymentService.regist(paymentInfoEntity);

        String successMessage = messageSource.getMessage("registration.success", null, null);
        // 成功メッセージをFlashAttributesに追加
        redirectAttributes.addFlashAttribute("successMessage", successMessage);

        return "redirect:/";

    }

    /* 予約キャンセル*/
    @GetMapping("/cancel/{id}")
    public String reserveCancel(Model model,
                                @PathVariable("id") int id,
                                RedirectAttributes redirectAttributes){
        //対象の支払い情報レコードをキャンセル扱いに
        paymentService.reserveCanecel(id);

        String cancelSuccessMessage = messageSource.getMessage("cancel.success", null, null);
        redirectAttributes.addFlashAttribute("successMessage", cancelSuccessMessage);

        return "redirect:/";

    }

    private UserEntity getLoginUser() {
        final String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        //ログインユーザ情報を取得する処理
        UserEntity user = userMapper.findUser(userName).get();
        return user;
    }

    }
