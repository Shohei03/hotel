package com.example.Hotel.web.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


import java.io.Serializable;

@Data
@NoArgsConstructor
@Component
public class UserForm   implements Serializable {
    private String id;

    @NotBlank(message = "名前を入力してください")
    @Size(max = 50, message = "名前は50文字以内で入力してください")
    private String name;

    @NotBlank(message = "名前（カナ）を入力してください")
    @Pattern(regexp = "^[ァ-ンヴー\\s\\u3000]*$", message = "名前（カナ）はカタカナで入力してください")
    @Size(max = 50, message = "名前（カナ）は50文字以内で入力してください")
    private String nameKana;

    @NotBlank(message = "パスワードを入力してください")
    @Size(min = 8, max = 100, message = "パスワードは8文字以上100文字以内で入力してください")
    private String password;

    @NotBlank(message = "郵便番号を入力してください")
    @Pattern(regexp = "^[0-9]{7}$", message = "郵便番号はXXXXXXXの形式で入力してください")
    private String post;

    @NotBlank(message = "住所を入力してください")
    @Size(max = 100, message = "住所は100文字以内で入力してください")
    private String address;

    @NotBlank(message = "電話番号を入力してください")
    @Pattern(regexp = "\\d{8,12}", message = "電話番号はXXXXXXXXXXXXの形式で入力してください")
    private String tel;

    @NotBlank(message = "メールアドレスを入力してください")
    @Email(message = "正しいメールアドレスの形式で入力してください")
    private String mail;

    @NotBlank(message = "権限を選択してください")
    private String authority;
}
