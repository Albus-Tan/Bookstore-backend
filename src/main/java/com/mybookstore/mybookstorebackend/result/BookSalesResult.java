package com.mybookstore.mybookstorebackend.result;

import com.mybookstore.mybookstorebackend.entity.Book;

import java.math.BigDecimal;

public class BookSalesResult {
    private Book book;
    private Integer num_sales;
    private BigDecimal total_price;

    public BookSalesResult(Book book, Integer num_sales, BigDecimal total_price) {
        this.book = book;
        this.num_sales = num_sales;
        this.total_price = total_price;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Integer getNum_sales() {
        return num_sales;
    }

    public void setNum_sales(Integer num_sales) {
        this.num_sales = num_sales;
    }

    public BigDecimal getTotal_price() {
        return total_price;
    }

    public void setTotal_price(BigDecimal total_price) {
        this.total_price = total_price;
    }
}
