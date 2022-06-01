package com.mybookstore.mybookstorebackend.daoimpl;

import com.mybookstore.mybookstorebackend.constant.Constant;
import com.mybookstore.mybookstorebackend.dao.UserDao;
import com.mybookstore.mybookstorebackend.entity.User;
import com.mybookstore.mybookstorebackend.entity.UserAuth;
import com.mybookstore.mybookstorebackend.repository.UserAuthRepository;
import com.mybookstore.mybookstorebackend.repository.UserRepository;
import com.mybookstore.mybookstorebackend.result.UserAuthResult;
import com.mybookstore.mybookstorebackend.result.UserResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.jws.soap.SOAPBinding;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    UserAuthRepository userAuthRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAll(){
        return userRepository.getAll();
    }

    @Override
    public List<UserResult> getAllUsersAndStatusAndType(){
        List<User> users = userRepository.getAll();
        List<UserResult> userResults = new ArrayList<>();
        for(User u: users){
            UserAuth userAuth = u.getUserAuth();
            UserResult userResult = new UserResult(new UserAuthResult(u.getUser_id(), userAuth.getUser_type(), userAuth.getUser_status()),u);
            userResults.add(userResult);
        }
        return userResults;
    }

    @Override
    public User getById(Integer user_id){
        return userRepository.getById(user_id);
    }

    @Override
    public Integer deleteById(Integer id){
        User u = userRepository.getById(id);
        if(u == null) return Constant.FAIL;
        userAuthRepository.deleteById(id);
        userRepository.deleteById(id);
        return Constant.SUCCESS;
    }

    @Override
    public Integer add(String username, String password, Integer user_type){
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
        userAuth.setUser_status(Constant.NORMAL);
        userAuthRepository.save(userAuth);

        return user.getUser_id();
    }

    @Override
    public UserAuthResult auth(String username, String password){
        UserAuth userAuth = userAuthRepository.auth(username, password);
        if(userAuth == null) return new UserAuthResult(Constant.NO_SUCH_USER, Constant.NO_SUCH_USER, Constant.NO_SUCH_USER);
        else return new UserAuthResult(userAuth.getUser_id(), userAuth.getUser_type(), userAuth.getUser_status());
    }

    @Override
    public Integer updateById(Integer user_id, String name, String nickname,
                              String tel, String address){
        User user = userRepository.getById(user_id);
        if(user == null) return Constant.NO_SUCH_USER;
        user.setName(name);
        user.setNickname(nickname);
        user.setTel(tel);
        user.setAddress(address);
        userRepository.save(user);

        return user.getUser_id();
    }

    @Override
    public Integer modifyUserStatus(Integer user_id, Integer user_status){
        UserAuth userAuth = userAuthRepository.getUserAuthByUser_id(user_id);
        userAuth.setUser_status(user_status);
        userAuthRepository.save(userAuth);
        return  Constant.SUCCESS;
    }
}
