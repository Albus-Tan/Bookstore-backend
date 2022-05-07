package com.mybookstore.mybookstorebackend.entity;

import com.mybookstore.mybookstorebackend.idClass.OrderItemId;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@IdClass(OrderItemId.class)
public class OrderItem {

    @Id
    private Integer order_id;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName="order_id", insertable = false, updatable = false)
    private Order order;

    @Id
    private Integer book_id;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName="id", insertable = false, updatable = false)
    private Book book;

    private Integer num;
    private Double price;

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getOrder_id() {
        return order_id;
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

    public void setBook(Book book) {
        this.book = book;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
