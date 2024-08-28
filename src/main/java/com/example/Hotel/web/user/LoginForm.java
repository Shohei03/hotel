package com.example.Hotel.web.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginForm {

    @NotBlank(message = "ユーザ名を入力してください")
    @Size(max = 50, message = "ユーザ名は50文字以内で入力してください")
    private String username;

    @NotBlank(message = "パスワードを入力してください")
    @Size(min = 8, max = 100, message = "パスワードは8文字以上100文字以内で入力してください")
    private String password;
}
