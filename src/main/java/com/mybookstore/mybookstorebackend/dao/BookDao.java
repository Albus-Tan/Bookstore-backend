package com.mybookstore.mybookstorebackend.dao;

import com.mybookstore.mybookstorebackend.entity.Book;
import com.mybookstore.mybookstorebackend.entity.BookType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface BookDao {
    Book getById(Integer id);

    List<Book> getAll();

    Integer deleteById(Integer id);

    Integer updateById(Integer id, String isbn, String name,
                       String type, String author, BigDecimal price,
                       String description, Integer inventory, String image);

    Integer add(String isbn, String name, String type, String author,
                BigDecimal price, String description, Integer inventory, String image);

    Integer modifyInventory(Integer id, Integer inventory);

    void RebuildTypeLabelGraph();

    List<BookType> GetRelatedSubclass(String type);

    Set<Book> getByBookType(Set<String> type);
}
