package com.mybookstore.mybookstorebackend.service;

import com.mybookstore.mybookstorebackend.entity.Book;

import java.math.BigDecimal;
import java.util.List;

public interface BookService {

    Book getBookById(Integer id);

    List<Book> getAllBooks();

    Integer deleteBookById(Integer id);

    Integer updateBookById(Integer id, String isbn, String name,
                       String type, String author, BigDecimal price,
                       String description, Integer inventory, String image);

    Integer addBook(String isbn, String name, String type, String author,
                BigDecimal price, String description, Integer inventory, String image);
}
