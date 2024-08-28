package com.example.Hotel.config;

import com.example.Hotel.domain.auth.UserEntity;
import com.example.Hotel.domain.auth.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.*;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    // テスト用の固定値ユーザー名とパスワード
    //private static final String FIXED_USERNAME = "testUser";
    //private static final String FIXED_PASSWORD = "testPass123";

    @Autowired
    private UserMapper usermapper;

    @Autowired
    private Optional<UserEntity> userEntity;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // ブラウザから入力したユーザ名・パスワードを取得
        String username = authentication.getName();
        //String password =  passwordEncoder.encode((String)authentication.getCredentials());
        String password =  (String)authentication.getCredentials();

        //password = passwordEncoder.encode(password);

        if((username == null || username.isEmpty()) || (password == null || password.isEmpty())) {
            throw new BadCredentialsException("Invalid username or password");
        }

        //最適なメソッド使用
        Collection<? extends GrantedAuthority> authority = authentication.getAuthorities();

        userEntity = usermapper.findUser(authentication.getName());

        if(userEntity.isEmpty()){
            throw new BadCredentialsException("入力したユーザ情報とパスワードに紐づく会員情報はありません。");
        }


        if (userEntity.get().getName().equals(username) && userEntity.get().getPassword().equals(password)) {
            // 認証成功時は、認証トークン(ユーザ名、パスワード、権限)を作成
            return new UsernamePasswordAuthenticationToken(username, password, toGrantedAuthority(userEntity.get().getAuthority()));
        } else {
            // 認証失敗は、エラーを返す
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        // authentication(認証方式)がUsernamePasswordAuthenticationToken.class(ユーザー名とパスワード認証)か判定
        return authentication.equals(UsernamePasswordAuthenticationToken.class);


}
    private List<GrantedAuthority> toGrantedAuthority(UserEntity.Authority authority) {
        return Collections.singletonList(new SimpleGrantedAuthority(authority.name()));
    }
}
