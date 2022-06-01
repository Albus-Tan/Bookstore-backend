package com.mybookstore.mybookstorebackend.result;

public class UserAuthResult {
    private Integer user_id;
    private Integer user_type;
    private Integer user_status;

    public UserAuthResult(Integer user_id, Integer user_type, Integer user_status) {
        this.user_id = user_id;
        this.user_type = user_type;
        this.user_status = user_status;
    }

    public Integer getUser_type() {
        return user_type;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public void setUser_type(Integer user_type) {
        this.user_type = user_type;
    }

    public Integer getUser_status() {
        return user_status;
    }

    public void setUser_status(Integer user_status) {
        this.user_status = user_status;
    }
}
