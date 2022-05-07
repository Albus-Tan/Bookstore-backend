package com.mybookstore.mybookstorebackend.idClass;

import java.io.Serializable;
import java.util.Objects;

public class OrderItemId implements Serializable {

    private Integer order_id;
    private Integer book_id;

    public OrderItemId(){

    }

    public OrderItemId(Integer order_id, Integer book_id) {
        this.order_id = order_id;
        this.book_id = book_id;
    }

    @Override
    public boolean equals(Object o){
        if(this == o) return false;
        if(o == null || getClass() != o.getClass()) return false;
        OrderItemId that = (OrderItemId) o;
        return Objects.equals(book_id, that.book_id) && Objects.equals(order_id, that.order_id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(book_id, order_id);
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }
}
