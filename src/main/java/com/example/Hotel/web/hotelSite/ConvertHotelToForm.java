package com.example.Hotel.web.hotelSite;

import com.example.Hotel.domain.top.HotelEntity;
import org.springframework.beans.factory.annotation.Autowired;


public class ConvertHotelToForm {
    //@Autowired
    //HotelDetailForm hotelForm;

    public HotelDetailForm convert(HotelEntity hotel) {
        HotelDetailForm hotelForm = new HotelDetailForm();

        hotelForm.setId(hotel.getId());
        hotelForm.setName(hotel.getName());
        hotelForm.setDescription(hotel.getDescription());
        hotelForm.setAddress(fixAddress(hotel.getAddress()));
        hotelForm.setFullAddress(hotel.getPrefecture() + hotel.getCity()  + hotel.getOtherParts());
        hotelForm.setPhoneNumber(fixTel(hotel.getPhoneNumber()));
        hotelForm.setPrice(hotel.getPrice());
        hotelForm.setHotelPicture(hotel.getHotelPicture());
        hotelForm.setHotelPicture2(hotel.getHotelPicture2());
        hotelForm.setHotelPicture3(hotel.getHotelPicture3());
        hotelForm.setHotelPicture4(hotel.getHotelPicture4());

        return hotelForm;
    }

    // 電話番号を分割して、”-”を追加して連結
    private String fixTel(String phoneNumber){
        String formatTel = phoneNumber.substring(0, 3) + "-"
                + phoneNumber.substring(3, 7) + "-"
                + phoneNumber.substring(7);
        return formatTel;
    }

    private String fixAddress(String address) {
        String formatAdddress = address.substring(0, 3) + "-"
                + address.substring(3, 7);
        return formatAdddress;
    }



}
