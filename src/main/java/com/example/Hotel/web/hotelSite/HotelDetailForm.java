package com.example.Hotel.web.hotelSite;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelDetailForm {
    private Long id;
    private String name;
    private String description;
    private String address;
    private String fullAddress;
    private String phoneNumber;
    private String hotelPicture;
    private Integer price;
    private String hotelPicture2;
    private String hotelPicture3;
    private String hotelPicture4;
}
