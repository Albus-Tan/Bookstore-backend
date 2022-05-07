package com.mybookstore.mybookstorebackend.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.text.DateFormat;

@Entity
@Table(name = "order")
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Integer order_id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName="user_id")
    private User user;

    private DateFormat time;
    private Double total_price;
    private Integer num;
    private Integer status;

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public DateFormat getTime() {
        return time;
    }

    public void setTime(DateFormat time) {
        this.time = time;
    }

    public Double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Double total_price) {
        this.total_price = total_price;
    }

}
