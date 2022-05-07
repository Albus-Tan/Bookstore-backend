package com.mybookstore.mybookstorebackend.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_auth")
public class UserAuth {

    @Id
    private Integer user_id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String username;
    private String password;
    private Integer user_type;

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUser_type(Integer user_type) {
        this.user_type = user_type;
    }

    public Integer getUser_type() {
        return user_type;
    }
}

