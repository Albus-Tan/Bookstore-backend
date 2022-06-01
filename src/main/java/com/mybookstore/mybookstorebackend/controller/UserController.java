package com.mybookstore.mybookstorebackend.controller;

import com.mybookstore.mybookstorebackend.result.UserAuthResult;
import com.mybookstore.mybookstorebackend.entity.User;
import com.mybookstore.mybookstorebackend.result.UserResult;
import com.mybookstore.mybookstorebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/user", method = RequestMethod.POST)
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/register")
    public @ResponseBody
    Integer register(@RequestParam String username, @RequestParam String password, @RequestParam Integer user_type) {
        return userService.addUser(username, password, user_type);
    }

    @PostMapping(path = "/login")
    public @ResponseBody UserAuthResult login(@RequestParam String username, @RequestParam String password) {
        return userService.authUser(username, password);
    }

    @PostMapping(path ="/getAll")
    public @ResponseBody List<User> getAll() {
        return userService.getAllUsers();
    }

    @PostMapping(path ="/getAllUsersAndStatusAndType")
    public @ResponseBody List<UserResult> getAllUsersAndStatusAndType() {return userService.getAllUsersAndStatusAndType();}

    @PostMapping(path ="/getById")
    public @ResponseBody User getById(@RequestParam Integer user_id) {
        return userService.getUserById(user_id);
    }

    @PostMapping(path ="/setById")
    public @ResponseBody Integer setById(@RequestParam Integer user_id, @RequestParam String name, @RequestParam String nickname,
                                         @RequestParam String tel, @RequestParam String address) {
        return userService.updateUserById(user_id, name, nickname, tel, address);
    }

    @PostMapping(path = "/modifyStatusById")
    public @ResponseBody Integer modifyUserStatus(@RequestParam Integer user_id, @RequestParam Integer user_status){
        return userService.modifyUserStatus(user_id, user_status);
    }

}
