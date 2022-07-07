package com.mybookstore.mybookstorebackend.repository;

import com.mybookstore.mybookstorebackend.entity.User;
import com.mybookstore.mybookstorebackend.entity.UserAuth;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserAuthRepository extends CrudRepository<UserAuth,Integer> {

    @Query(value = "from UserAuth where username = :username and password = :password")
    UserAuth auth(@Param("username") String username, @Param("password") String password);

    @Query("select ua from UserAuth ua where ua.user_id = :user_id")
    UserAuth getUserAuthByUser_id(@Param("user_id") Integer user_id);

    @Query("select u from UserAuth u where u.username=:username")
    UserAuth getByUsername(@Param("username") String username);
}
