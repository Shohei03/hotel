package com.example.Hotel.domain.payment;

import com.example.Hotel.domain.auth.UserEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PayMapper {

    /* 決済情報登録*/
    public int insert(PaymentInfoEntity paymentInfo);

    /* 旅館予約一覧検索用（複数）　過去含む　*/
    public List<PaymentInfoEntity> findPaymentInfo(int userId);

    /* 旅館予約一覧検索用（複数）　未来 */
    public List<PaymentInfoEntity> findReserveInfo(int userId);

    //対象の支払い情報レコードを取得
    public PaymentInfoEntity findOnePaymentInfo(int id);

    //予約キャンセル
    public void reserveCanecel(int id);



}
