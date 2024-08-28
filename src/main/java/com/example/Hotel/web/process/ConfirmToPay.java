package com.example.Hotel.web.process;

import com.example.Hotel.domain.auth.UserEntity;
import com.example.Hotel.domain.auth.UserMapper;
import com.example.Hotel.domain.payment.PaymentInfoEntity;
import com.example.Hotel.domain.top.HotelEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

@Component
public class ConfirmToPay {
    @Autowired
    UserMapper userMapper;

    @Autowired
    PaymentInfoEntity paymentInfoEntity;

    public PaymentInfoEntity confirmToPay(ReserveForm reserveForm, HotelEntity hotel) {

        // ログインユーザ名を取得可能！
        final String userName = SecurityContextHolder.getContext().getAuthentication().getName();

        //後で処理組む DB等からユーザ情報を取得する処理
        UserEntity user = userMapper.findUser(userName).get();

        paymentInfoEntity.setUser(user);
        paymentInfoEntity.setHotel(hotel);
        paymentInfoEntity.setReserveDateFrom(reserveForm.getCheckin_date());
        paymentInfoEntity.setReserveDateTo(reserveForm.getCheckout_date());

        paymentInfoEntity.setAmount(reserveForm.getTotalPrice());
        paymentInfoEntity.setGuestNum(reserveForm.getGuestNum());

        return paymentInfoEntity;

    }

}
