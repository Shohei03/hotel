package com.example.Hotel.domain.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService{

    @Autowired
    private PayMapper paymapper;

    /* 決済登録 */
    public void regist(PaymentInfoEntity paymentInfo){
        paymapper.insert(paymentInfo);
    };

    /* 旅館予約一覧検索用（複数）過去含む　*/
    public List<PaymentInfoEntity> findPaymentInfo(int userId){
        return paymapper.findPaymentInfo(userId);
    };

    /* 旅館予約一覧検索用（複数） 未来　*/
    public List<PaymentInfoEntity> findReserveInfo(int userId){
        return paymapper.findReserveInfo(userId);
    };

    //対象の支払い情報レコードを取得
    public PaymentInfoEntity findOnePaymentInfo(int id){
        return paymapper.findOnePaymentInfo(id);
    };

    //予約キャンセル
    public void reserveCanecel(int id){
        paymapper.reserveCanecel(id);
    };



}
