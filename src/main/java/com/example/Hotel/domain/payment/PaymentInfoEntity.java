package com.example.Hotel.domain.payment;

import com.example.Hotel.domain.auth.UserEntity;
import com.example.Hotel.domain.top.HotelEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Repository
public class PaymentInfoEntity {
    private int id;
    private HotelEntity hotel;
    private UserEntity user;
    private String payDate;
    private Date reserveDateFrom;
    private Date reserveDateTo;
    private int amount;
    private Date createdAt;
    private int guestNum;
}
