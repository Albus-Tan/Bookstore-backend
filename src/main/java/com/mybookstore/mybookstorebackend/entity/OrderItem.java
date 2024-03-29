package com.mybookstore.mybookstorebackend.entity;

import com.mybookstore.mybookstorebackend.idClass.OrderItemId;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "order_item")
@IdClass(OrderItemId.class)
public class OrderItem {

    @Id
    private Integer order_id;

//    // 修改或删除 OrderItem，Order 不删除
//    // Cascade merge 级联更新（合并）操作: 当 OrderItem 中的数据改变，会相应地更新 Order 中的数据
//    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
//    @JoinColumn(name = "order_id", referencedColumnName="order_id", insertable = false, updatable = false)
//    private Order order;

    @Id
    private Integer book_id;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName="id", insertable = false, updatable = false)
    private Book book;

    private Integer num;
    private BigDecimal price;

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

//    public Order getOrder() {
//        return order;
//    }
//
//    public void setOrder(Order order) {
//        this.order = order;
//    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
