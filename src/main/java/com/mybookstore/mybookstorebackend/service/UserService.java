package com.mybookstore.mybookstorebackend.service;

import com.mybookstore.mybookstorebackend.entity.User;
import com.mybookstore.mybookstorebackend.result.UserAuthResult;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    User getUserById(Integer user_id);

    Integer deleteUserById(Integer id);

    Integer addUser(String username, String password, Integer user_type);

    UserAuthResult authUser(String username, String password);

    Integer updateUserById(Integer user_id, String name, String nickname,
                       String tel, String address);
}
