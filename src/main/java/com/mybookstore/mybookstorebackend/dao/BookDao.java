package com.mybookstore.mybookstorebackend.dao;

import com.mybookstore.mybookstorebackend.entity.Book;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.List;

public interface BookDao {
    Book getById(Integer id);

    List<Book> getAll();

    Integer deleteById(Integer id);

    Integer updateById(Integer id, String isbn, String name,
                       String type, String author, BigDecimal price,
                       String description, Integer inventory, String image);

    Integer add(String isbn, String name, String type, String author,
                BigDecimal price, String description, Integer inventory, String image);

}
