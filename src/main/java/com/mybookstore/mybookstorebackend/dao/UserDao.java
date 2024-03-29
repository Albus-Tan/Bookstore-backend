package com.mybookstore.mybookstorebackend.dao;

import com.mybookstore.mybookstorebackend.entity.User;
import com.mybookstore.mybookstorebackend.result.UserAuthResult;
import com.mybookstore.mybookstorebackend.result.UserResult;

import java.util.List;

public interface UserDao {

    List<User> getAll();

    List<UserResult> getAllUsersAndStatusAndType();

    User getById(Integer user_id);

    Integer deleteById(Integer id);

    Integer updateById(Integer user_id, String name, String nickname,
                    String tel, String address);

    Integer add(String username, String password, Integer user_type);

    UserAuthResult auth(String username, String password);

    Integer modifyUserStatus(Integer user_id, Integer user_status);

}
