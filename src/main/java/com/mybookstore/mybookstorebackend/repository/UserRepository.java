package com.mybookstore.mybookstorebackend.repository;

import com.mybookstore.mybookstorebackend.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User,Integer> {

    @Query("select u from User u")
    List<User> getAll();

    @Query("select u from User u where u.user_id=:user_id")
    User getById(@Param("user_id") Integer user_id);
}
