package com.example.Hotel.domain.auth;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import java.util.Optional;
import java.util.List;

@Mapper
public interface UserMapper {

    /* ユーザ登録*/
    public int insertOne(UserEntity user);

    /* ユーザ検索用　*/
    public Optional<UserEntity> findUser(String userName);

    /* ユーザ検索用（複数）　*/
    public List<UserEntity> findUsers(String userName);

    /* パスワードリセット用　メールアドレスからユーザ情報取得　*/
    public UserEntity findByEmail(String mail);

    /* パスワードリセット用　token情報更新　*/
    public int updateToken(UserEntity user);

    /* パスワードリセット用　token情報からユーザ情報を取得　*/
    public UserEntity findByResetToken(String resetToken);

    /* パスワード更新用　*/
    public int updatePassword(UserEntity user);

    /* ユーザー情報更新*/
    public int updateUser(UserEntity user, int user_id);

    /* ユーザー情報一括取得　*/
    public List<UserEntity> findAllUser(RowBounds rowBounds);

    /* idからユーザー情報取得 */
    public UserEntity findUserId(int id);

    /* ユーザ情報更新 */
    public void updateUserInfo(UserEntity user);

    public void updateRole(int user_id, String authority);


    /* ユーザーカウント　*/
    public int getCount();
}
