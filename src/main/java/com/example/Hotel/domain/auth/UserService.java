package com.example.Hotel.domain.auth;


import org.apache.ibatis.session.RowBounds;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {

    /* ユーザ登録 */
    public void signup(UserEntity user);

    /* ユーザ検索用　*/
    public Optional<UserEntity> findUser(String userName);

    /* ユーザ検索用（複数）　*/
    public List<UserEntity> findUsers(String userName);

    /* ユーザ情報更新*/
    public void modify(UserEntity user, int id);

    /* Idからユーザー情報取得 */
    public UserEntity findUserId(int id);



    /* ユーザー一覧用　*/
    public Page<UserEntity> findAllUser(Pageable pageable);


}
