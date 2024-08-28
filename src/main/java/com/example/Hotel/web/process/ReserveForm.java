package com.example.Hotel.web.process;

import com.example.Hotel.domain.top.HotelEntity;
import com.example.Hotel.validation.CheckDateRange;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@CheckDateRange
public class ReserveForm  implements Serializable {
    @NotNull(message = "チェックイン日は必須です。")
    private Date checkin_date;

    @NotNull(message = "チェックアウト日は必須です。")
    private Date checkout_date;

    @Min(value = 1, message = "ゲスト数は1人以上でなければなりません。")
    private int guestNum;

    @Min(value = 0, message = "価格は0以上でなければなりません。")
    private int price;

    @Min(value = 0, message = "合計価格は0以上でなければなりません。")
    private int totalPrice;
}
