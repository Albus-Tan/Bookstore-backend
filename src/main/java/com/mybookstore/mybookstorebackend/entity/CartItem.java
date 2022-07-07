package com.mybookstore.mybookstorebackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mybookstore.mybookstorebackend.idClass.CartItemId;

import javax.persistence.*;

@Entity
@Table(name = "cart_item")
@IdClass(CartItemId.class)
public class CartItem {

    @Id
    private Integer user_id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName="user_id", insertable = false, updatable = false)
    private User user;

    @Id
    private Integer book_id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "book_id", referencedColumnName="id", insertable = false, updatable = false)
    private Book book;

    private Integer num;

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getNum() {
        return num;
    }

    public Book getBook() {
        return book;
    }

    public User getUser() {
        return user;
    }
}
