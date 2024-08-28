package com.example.Hotel.web.hotelSite;

import com.example.Hotel.domain.hotelinfo.HotelForm;
import com.example.Hotel.domain.top.HotelEntity;


public class ConvertFormToHotel {
    //@Autowired
    //HotelDetailForm hotelForm;

    public HotelEntity convert(HotelForm form,String hotelPicture,String hotelPicture2, String hotelPicture3) {
        HotelEntity hotel = new HotelEntity();

        hotel.setName(form.getName());
        hotel.setDescription(form.getDescription());
        hotel.setHotelPicture(hotelPicture);
        hotel.setHotelPicture2(hotelPicture2);
        hotel.setHotelPicture3(hotelPicture3);
        hotel.setAddress(form.getAddress());
        hotel.setPrefecture(form.getPrefecture());
        hotel.setCity(form.getCity());
        hotel.setOtherParts(form.getOtherParts());
        hotel.setPhoneNumber(form.getPhoneNumber());
        hotel.setPrice(form.getPrice());

        return hotel;
    }

//    // 電話番号を分割して、”-”を追加して連結
//    private String fixTel(String phoneNumber){
//        String formatTel = phoneNumber.substring(0, 3) + "-"
//                + phoneNumber.substring(3, 7) + "-"
//                + phoneNumber.substring(7);
//        return formatTel;
//    }
//
//    private String fixAddress(String address) {
//        String formatAdddress = address.substring(0, 3) + "-"
//                + address.substring(3, 7);
//        return formatAdddress;
//    }



}
