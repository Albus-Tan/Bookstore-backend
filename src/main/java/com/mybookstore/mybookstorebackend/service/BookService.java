package com.mybookstore.mybookstorebackend.service;

import com.mybookstore.mybookstorebackend.entity.Book;
import com.mybookstore.mybookstorebackend.entity.BookType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface BookService {

    Book getBookById(Integer id);

    List<Book> getAllBooks();

    Integer deleteBookById(Integer id);

    Integer updateBookById(Integer id, String isbn, String name,
                       String type, String author, BigDecimal price,
                       String description, Integer inventory, String image);

    Integer addBook(String isbn, String name, String type, String author,
                BigDecimal price, String description, Integer inventory, String image);

    void RebuildTypeLabelGraph();

    List<BookType> GetRelatedSubclass(String type);

    Set<Book> getBooksByTypeRelated(String type);
}
