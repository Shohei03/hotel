package com.example.Hotel.web.hotelSite;

import com.example.Hotel.domain.hotelinfo.HotelForm;
import com.example.Hotel.domain.top.HotelEntity;
import com.example.Hotel.domain.top.HotelService;
import com.example.Hotel.rest.UploadService;
import com.example.Hotel.web.process.ReserveForm;
import com.example.Hotel.web.user.UserForm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;

@Controller
public class hotelInfoController {
    @Autowired
    private HotelService hotelService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UploadService uploadService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/hotel/register")
    public String getRegister(Model model, HotelForm hotelForm) {
        model.addAttribute("hotelForm", hotelForm);
        model.addAttribute("picture", null);

        return "hotel/hotelregist";
    }

    @PostMapping("/hotel/register")
    public String postRegister(Model model,
                               MultipartFile picture,
                               MultipartFile picture2,
                               MultipartFile picture3,
                               @ModelAttribute @Validated HotelForm hotelForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            // ホテル登録画面に戻る
            return getRegister(model, hotelForm);
        }

        /* ファイルをアップロードしてDB取得用のパスを取得*/
        String hotelPicture = uploadService.fileUpload(picture);

        String hotelPicture2 = null;
        String hotelPicture3 = null;

        if (picture2 != null) {
            hotelPicture2 = uploadService.fileUpload(picture2);
        }
        if (picture2 != null & picture3 != null){
            hotelPicture3 = uploadService.fileUpload(picture3);
    }

        ConvertFormToHotel convert = new ConvertFormToHotel();
        HotelEntity hotel = convert.convert(hotelForm, hotelPicture, hotelPicture2, hotelPicture3);

        hotelService.insert(hotel);
        //HotelEntity hotel = modelMapper.map(hotelForm, HotelEntity.class);

        String successMessage = messageSource.getMessage("registration.success", null, null);
        // 成功メッセージをFlashAttributesに追加
        redirectAttributes.addFlashAttribute("successMessage", successMessage);

        return "redirect:/";
    }

    @GetMapping("/hotel/infoList")
    public String infoList(Model model,
                           @PageableDefault(size = 5) Pageable pageable) {
        Page<HotelEntity> pageList = null;
        List<HotelEntity> hotelList = null;

        pageList = hotelService.getHotelAll(pageable);
        hotelList = pageList.getContent();
        /* 検索結果カウント "keyword"*/
        //hotelCount = hotelService.getResultCount(keyword, 1);

        model.addAttribute("page", pageList);
        model.addAttribute("hotelList", hotelList);

        return  "hotel/hotelInfoList";

    }

    @GetMapping("/hotel/detail/{id}")
    public String showDetail(Model model, @PathVariable("id") Long id) {
        HotelEntity hotel = hotelService.getHotelById(id);

        ConvertHotelToForm convertHotelToForm = new ConvertHotelToForm();
        HotelDetailForm hotelDetailForm = convertHotelToForm.convert(hotel);

        model.addAttribute("hotelDetailForm", hotelDetailForm);
        model.addAttribute("id", id);

        return "hotel/detailInfo";
    }

    @PostMapping("/hotel/delete")
    public String hotelDelete(Model model, String id) {
        hotelService.deleteById(id);

        System.out.println("formData" + id);
        return "hotel/hotelInfoList";
    }

    @GetMapping("/hotel/delete/{id}")
    public String hotelDeletes(Model model, @PathVariable("id") String id) {
        hotelService.deleteById(id);

        System.out.println("formData" + id);
        return "hotel/hotelInfoList";
    }

    @GetMapping("/hotel/modify")
    public String modify(Model model,long id, @ModelAttribute HotelForm hotelForm) {

        model.addAttribute("hotelForm", hotelForm);
        model.addAttribute("id", id);

        return "hotel/hotelInfochange";
    }

    @GetMapping("/hotel/infoChange")
    public String infoChange(Model model,long id, @ModelAttribute HotelForm hotelForm) {
        HotelEntity hotel = hotelService.getHotelById(id);
        hotelForm = modelMapper.map(hotel, HotelForm.class);

        model.addAttribute("hotelForm", hotelForm);
        model.addAttribute("id", id);

        return "hotel/hotelInfochange";
    }

    @PostMapping("/hotel/infochangefin")
    public String hotelInfoChange(Model model, Long id, @ModelAttribute @Validated HotelForm hotelForm,  BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            // ホテル編集画面に戻る
            return modify(model, id, hotelForm);
        }
        HotelEntity hotel = modelMapper.map(hotelForm, HotelEntity.class);

        hotelService.updateHotel(id, hotel);

        String successMessage = messageSource.getMessage("update.success", null, null);
        // 成功メッセージをFlashAttributesに追加
        redirectAttributes.addFlashAttribute("successMessage", successMessage);

        return "redirect:/hotel/infoList";
    }

}
