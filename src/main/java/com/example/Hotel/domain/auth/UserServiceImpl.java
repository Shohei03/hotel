package com.example.Hotel.domain.auth;

import com.example.Hotel.domain.top.HotelEntity;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper mapper;
    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;



    /* ユーザ登録 */
    @Override
    public void signup(UserEntity user) {
        user.setAuthority(UserEntity.Authority.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //user.setPassword(passwordEncoder.encode(user.getPassword()));
        mapper.insertOne(user);
    }

    /* ユーザ検索用　*/
    public Optional<UserEntity> findUser(String userName){
        return mapper.findUser(userName);
    }

    /* ユーザ検索用（複数）　*/
    public List<UserEntity> findUsers(String userName){
        return mapper.findUsers(userName);
    };

    public UserEntity findUserId(int id){
        return mapper.findUserId(id);
    };

    /* ユーザ情報更新*/
    public void modify(UserEntity user, int id){
        mapper.updateUser(user, id);
    };

    /* ユーザー一覧用　*/
    public Page<UserEntity> findAllUser(Pageable pageable){
        RowBounds rowBounds = new RowBounds(
                (int)pageable.getOffset(), pageable.getPageSize());
        List<UserEntity> userList = mapper.findAllUser(rowBounds);

        int total = mapper.getCount();
        return new PageImpl<>(userList, pageable, total);
    };
}
