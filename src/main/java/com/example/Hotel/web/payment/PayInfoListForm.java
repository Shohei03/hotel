package com.example.Hotel.web.payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayInfoListForm {
    private int id;  //id
    private String hotelName;       // 民宿名
    private Date reserveDateFrom; // チェックイン日
    private Date reserveDateTo;   // チェックアウト日
    private int guest_num;          // 宿泊人数
    private int amount;             // 宿泊料金
}
