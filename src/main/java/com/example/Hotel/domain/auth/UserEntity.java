package com.example.Hotel.domain.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    private int id;
    private String name;
    private String nameKana;
    private String password;
    private String post;
    private String address;
    private String tel;
    private String mail;
    private String resetToken;  //パスワード再設定用
    private Authority authority;
    public enum Authority {
        ADMIN, USER
    }
}
