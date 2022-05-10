package com.mybookstore.mybookstorebackend.result;

public class OrderItemResult {
    private Integer num;
    private String name;
    private Double price;
    private Integer book_id;

    public OrderItemResult(Integer num, String name, Double price, Integer book_id) {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getBook_id() {
        return book_id;
    }

    public void setBook_id(Integer book_id) {
        this.book_id = book_id;
    }
}
