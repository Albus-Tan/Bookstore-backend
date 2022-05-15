package com.mybookstore.mybookstorebackend.result;

import java.math.BigDecimal;

public class OrderItemResult {
    private Integer num;
    private String name;
    private BigDecimal price;
    private Integer book_id;

    public OrderItemResult(Integer num, String name, BigDecimal price, Integer book_id) {
        this.num = num;
        this.name = name;
        this.price = price;
        this.book_id = book_id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }
}
