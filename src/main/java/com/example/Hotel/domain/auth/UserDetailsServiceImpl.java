package com.example.Hotel.domain.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {
    //DBからユーザ情報を検索するメソッドを実装したクラス
    @Autowired
    private UserMapper userMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userMapper.findUser(username)
                .map(
                        user -> new LoginUser(
                                user.getName(),
                                user.getPassword(),
                                toGrantedAuthority(user.getAuthority())
                        )
                )
                .orElseThrow(
                        () -> new UsernameNotFoundException(
                                "Given username is not found. (username = '" + username + "')"
                        )
                );


    }

    private List<GrantedAuthority> toGrantedAuthority(UserEntity.Authority authority) {
        return Collections.singletonList(new SimpleGrantedAuthority(authority.name()));
    }



}
