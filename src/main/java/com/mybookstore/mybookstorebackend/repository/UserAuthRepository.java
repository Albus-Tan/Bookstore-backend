package com.mybookstore.mybookstorebackend.repository;

import com.mybookstore.mybookstorebackend.entity.UserAuth;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserAuthRepository extends CrudRepository<UserAuth,Integer> {
    @Query(value = "from UserAuth where username = :username and password = :password")
    UserAuth auth(@Param("username") String username, @Param("password") String password);

}
