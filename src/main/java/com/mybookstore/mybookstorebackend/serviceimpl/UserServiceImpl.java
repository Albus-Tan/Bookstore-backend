package com.mybookstore.mybookstorebackend.serviceimpl;

import com.mybookstore.mybookstorebackend.dao.UserDao;
import com.mybookstore.mybookstorebackend.entity.User;
import com.mybookstore.mybookstorebackend.result.UserAuthResult;
import com.mybookstore.mybookstorebackend.result.UserResult;
import com.mybookstore.mybookstorebackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public List<User> getAllUsers(){
        return userDao.getAll();
    }

    @Override
    public List<UserResult> getAllUsersAndStatusAndType(){ return userDao.getAllUsersAndStatusAndType();}

    @Override
    public User getUserById(Integer user_id){
        return userDao.getById(user_id);
    }

    @Override
    public Integer deleteUserById(Integer id){
        return userDao.deleteById(id);
    }

    @Override
    public Integer addUser(String username, String password, Integer user_type){
        return userDao.add(username, password, user_type);
    }

    @Override
    public UserAuthResult authUser(String username, String password){
        return userDao.auth(username, password);
    }

    @Override
    public Integer updateUserById(Integer user_id, String name, String nickname,
                                  String tel, String address){
        return userDao.updateById(user_id, name, nickname, tel, address);
    }

    @Override
    public Integer modifyUserStatus(Integer user_id, Integer user_status){
        return userDao.modifyUserStatus(user_id, user_status);
    }
}
