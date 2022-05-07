package com.mybookstore.mybookstorebackend.idClass;

import java.io.Serializable;
import java.util.Objects;


public class CartItemId implements Serializable {
    private Integer user_id;
    private Integer book_id;

    public CartItemId(){

    }

    public CartItemId(Integer user_id, Integer book_id){
        this.book_id = book_id;
        this.user_id = user_id;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return false;
        if(o == null || getClass() != o.getClass()) return false;
        CartItemId that = (CartItemId) o;
        return Objects.equals(book_id, that.book_id) && Objects.equals(user_id, that.user_id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(book_id, user_id);
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }
}
