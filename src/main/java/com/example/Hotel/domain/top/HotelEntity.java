package com.example.Hotel.domain.top;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SessionScope
public class HotelEntity implements Serializable {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String address;
    private String prefecture;
    private String city;
    private String otherParts;
    private String phoneNumber;
    private String hotelPicture;
    private Integer price;
    private Date registDate;
    private Date updateDate;
    private String hotelPicture2;
    private String hotelPicture3;
    private String hotelPicture4;
}
