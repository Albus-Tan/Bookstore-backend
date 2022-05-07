package com.mybookstore.mybookstorebackend.controller;

import com.mybookstore.mybookstorebackend.constant.Constant;
import com.mybookstore.mybookstorebackend.result.UserAuthResult;
import com.mybookstore.mybookstorebackend.entity.User;
import com.mybookstore.mybookstorebackend.entity.UserAuth;
import com.mybookstore.mybookstorebackend.repository.UserAuthRepository;
import com.mybookstore.mybookstorebackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/user", method = RequestMethod.POST)
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserAuthRepository userAuthRepository;

    @PostMapping(path = "/register")
    public @ResponseBody
    Integer register(@RequestParam String username, @RequestParam String password, @RequestParam Integer user_type) {

        // Check if username exist

        // No type, change to CUSTOMER
        if(user_type == null) user_type = Constant.CUSTOMER;

        // Create user first
        User user = new User();
        user.setName(username);
        userRepository.save(user);

        UserAuth userAuth = new UserAuth();
        userAuth.setUser_id(user.getUser_id());
        userAuth.setUsername(username);
        userAuth.setPassword(password);
        userAuth.setUser_type(user_type);
        userAuthRepository.save(userAuth);

        return user.getUser_id();
    }


    @PostMapping(path = "/login")
    public @ResponseBody UserAuthResult login(@RequestParam String username, @RequestParam String password) {

        UserAuth userAuth = userAuthRepository.auth(username, password);
        if(userAuth == null) return new UserAuthResult(Constant.NO_SUCH_USER, Constant.NO_SUCH_USER);
        else return new UserAuthResult(userAuth.getUser_id(), userAuth.getUser_type());

    }

    @PostMapping(path ="/getAll")
    public @ResponseBody List<User> getAll() {
        return userRepository.getAll();
    }

    @PostMapping(path ="/getById")
    public @ResponseBody User getById(@RequestParam Integer user_id) {
        return userRepository.getById(user_id);
    }

    @PostMapping(path ="/setById")
    public @ResponseBody Integer setById(@RequestParam Integer user_id, @RequestParam String name, @RequestParam String nickname,
                                         @RequestParam String tel, @RequestParam String address) {

        User user = userRepository.getById(user_id);
        if(user == null) return Constant.NO_SUCH_USER;
        user.setName(name);
        user.setNickname(nickname);
        user.setTel(tel);
        user.setAddress(address);
        userRepository.save(user);

        return user.getUser_id();

    }



}
