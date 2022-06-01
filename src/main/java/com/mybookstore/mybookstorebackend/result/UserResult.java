package com.mybookstore.mybookstorebackend.result;

import com.mybookstore.mybookstorebackend.entity.User;

public class UserResult {
    private UserAuthResult userAuthResult;
    private User user;

    public UserResult(UserAuthResult userAuthResult, User user) {
        this.userAuthResult = userAuthResult;
        this.user = user;
    }

    public UserAuthResult getUserAuthResult() {
        return userAuthResult;
    }

    public void setUserAuthResult(UserAuthResult userAuthResult) {
        this.userAuthResult = userAuthResult;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
