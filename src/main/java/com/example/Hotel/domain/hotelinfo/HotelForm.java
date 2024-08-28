package com.example.Hotel.domain.hotelinfo;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;


@Data
@NoArgsConstructor
@Component
public class HotelForm {
    private Long id;

    @NotEmpty(message = "ホテル名は必須です。")
    @Size(max = 100, message = "ホテル名は100文字以内でなければなりません。")
    private String name;

    @Size(max = 500, message = "説明は500文字以内でなければなりません。")
    private String description;

    @NotEmpty(message = "郵便番号は必須です。")
    private String address;

    @NotEmpty(message = "都道府県は必須です。")
    private String prefecture;

    @NotEmpty(message = "市区町村は必須です。")
    private String city;

    @NotEmpty(message = "その他の情報は必須です。")
    @Size(max = 100, message = "その他の情報は100文字以内でなければなりません。")
    private String otherParts;

    @NotEmpty(message = "電話番号は必須です。")
    @Pattern(regexp = "^\\+?\\d{10,15}$", message = "電話番号は10〜15桁の数字でなければなりません。")
    private String phoneNumber;

    private String hotelPicture;

    @NotNull(message = "宿泊料金は必須です。")
    @Min(value = 0, message = "宿泊料金は0円以上でなければなりません。")
    private Integer price;
}