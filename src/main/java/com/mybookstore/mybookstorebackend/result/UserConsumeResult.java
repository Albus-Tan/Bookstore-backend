package com.mybookstore.mybookstorebackend.result;

import com.mybookstore.mybookstorebackend.entity.User;

import java.math.BigDecimal;

public class UserConsumeResult {
    private User user;
    private Integer num;
    private BigDecimal total_price;

    public UserConsumeResult(User user, Integer num, BigDecimal total_price) {
        this.user = user;
        this.num = num;
        this.total_price = total_price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public BigDecimal getTotal_price() {
        return total_price;
    }

    public void setTotal_price(BigDecimal total_price) {
        this.total_price = total_price;
    }
}
