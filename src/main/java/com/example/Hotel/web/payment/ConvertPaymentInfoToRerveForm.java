package com.example.Hotel.web.payment;

import com.example.Hotel.domain.payment.PaymentInfoEntity;
import com.example.Hotel.domain.top.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


public class ConvertPaymentInfoToRerveForm {

    @Autowired
    private HotelRepository hotelRepository;

    public List<PayInfoListForm> convert(List<PaymentInfoEntity> paymentInfoEntityList) {
        PayInfoListForm payInfoListForm = new PayInfoListForm();
        List<PayInfoListForm> payInfoListFormList = new ArrayList<>();

        for (PaymentInfoEntity paymentInfoEntity : paymentInfoEntityList) {
            payInfoListForm.setId(paymentInfoEntity.getId());
            payInfoListForm.setHotelName(paymentInfoEntity.getHotel().getName());
            payInfoListForm.setReserveDateFrom(paymentInfoEntity.getReserveDateFrom());
            payInfoListForm.setReserveDateTo(paymentInfoEntity.getReserveDateTo());
            payInfoListForm.setGuest_num(paymentInfoEntity.getGuestNum());
            payInfoListForm.setAmount(paymentInfoEntity.getAmount());

            payInfoListFormList.add(payInfoListForm);
        }

        return payInfoListFormList;
    }

}
