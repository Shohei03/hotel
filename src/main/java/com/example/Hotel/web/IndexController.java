package com.example.Hotel.web;

import com.example.Hotel.domain.auth.UserEntity;
import com.example.Hotel.domain.auth.UserMapper;
import com.example.Hotel.domain.payment.PaymentInfoEntity;
import com.example.Hotel.domain.payment.PaymentService;
import com.example.Hotel.domain.top.HotelEntity;
import com.example.Hotel.domain.top.HotelService;
import com.example.Hotel.web.hotelSite.ConvertHotelToForm;
import com.example.Hotel.web.hotelSite.HotelDetailForm;
import com.example.Hotel.web.hotelSite.SearchKeyword;
import com.example.Hotel.web.payment.ConvertPaymentInfoToRerveForm;
import com.example.Hotel.web.payment.PayInfoListForm;
import com.example.Hotel.web.process.ReserveForm;
import com.example.Hotel.web.user.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.rmi.server.RemoteServer;
import java.util.List;
import java.util.Locale;

@Controller
public class IndexController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/")
    public String index(Model model) {

        //ホテル10件取得
        List<HotelEntity> hotelList = hotelService.getHotelTop();

        String uniqueVersion = String.valueOf(System.currentTimeMillis());

        model.addAttribute("imageVersion", uniqueVersion);
        model.addAttribute("hotelList", hotelList);

        return "index";
    }

    @GetMapping("/detail/{id}")
    public String showDetail(Model model,
                             @PathVariable("id") Long id) {
        if (!model.containsAttribute("reserveForm")) {
            model.addAttribute("reserveForm", new ReserveForm());
        }
        HotelEntity hotel = hotelService.getHotelById(id);

        ConvertHotelToForm convertHotelToForm = new ConvertHotelToForm();
        HotelDetailForm hotelDetailForm = convertHotelToForm.convert(hotel);

        //ReserveForm reserveForm = new ReserveForm();

        SearchKeyword searchKeyword = new SearchKeyword();

        model.addAttribute("hotelDetailForm", hotelDetailForm);
        model.addAttribute("hotel_id", id);
        model.addAttribute("searchKeyword", searchKeyword);

        return "detail";
    }



    @GetMapping("/search")
    public String search(Model model,
                         SearchKeyword searchKeyword,
                         String orderKey,
                         @PageableDefault(size = 5) Pageable pageable) {
        //List<HotelEntity> hotelList = null;
        /* 検索結果件数*/
        int hotelCount = 0;
        /* ホテル名検索*/

        if(searchKeyword.getBudget_upper() == 0) {
            searchKeyword.setBudget_upper(30000);
        }

            //hotelList = hotelService.getHotelByName(keyword);
            /* 検索結果カウント "keyword"*/
            hotelCount = hotelService.getResultCount(searchKeyword);

        Page<HotelEntity> pageList = hotelService.getHotelByNamePaging(searchKeyword, orderKey, pageable);
        List<HotelEntity> hotelList = pageList.getContent();

        model.addAttribute("page", pageList);
        model.addAttribute("hotelList", hotelList);
        model.addAttribute("searchKeyword", searchKeyword);
        model.addAttribute("orderitem", orderKey);

        model.addAttribute("hotelCount", hotelCount);
        return "hotel/hotelList";
    }

    @GetMapping("/reserveList")
    public String reserveList(Model model) {
        // ログインユーザ名を取得可能！
        final String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        //ログインユーザ情報を取得する処理
        UserEntity user = userMapper.findUser(userName).get();

        PayInfoListForm payInfoListForm = new PayInfoListForm();

        //ログインユーザの予約ホテル取得
        List<PaymentInfoEntity> payInfoList = paymentService.findPaymentInfo(user.getId());

        ConvertPaymentInfoToRerveForm convertPaymentInfoToRerveForm = new ConvertPaymentInfoToRerveForm();

        //List<PaymentInfoEntity> → List<payInfoListForm>
        List<PayInfoListForm> payInfoListFormList = convertPaymentInfoToRerveForm.convert(payInfoList);

        model.addAttribute("payInfoListFormList", payInfoListFormList);
        return "user/reserveHotelList";
    }

}


