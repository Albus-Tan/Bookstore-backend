package com.mybookstore.mybookstorebackend.result;

public class UserAuthResult {
    private Integer user_id;
    private Integer user_type;

    public UserAuthResult(Integer user_id, Integer user_type) {
        this.user_id = user_id;
        this.user_type = user_type;
    }

    public Integer getUser_type() {
        return user_type;
    }

    public Integer getUser_id() {
        return user_id;
    }
}
