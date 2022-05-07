package com.mybookstore.mybookstorebackend.result;

import com.mybookstore.mybookstorebackend.entity.Book;

public class CartResult {
    private Book book;
    private Integer num;

    public CartResult(Book book, Integer num) {
        this.book = book;
        this.num = num;
    }

    public Book getBook() {
        return book;
    }

    public Integer getNum() {
        return num;
    }
}
