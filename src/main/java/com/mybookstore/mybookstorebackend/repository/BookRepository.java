package com.mybookstore.mybookstorebackend.repository;

import com.mybookstore.mybookstorebackend.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.relational.core.sql.In;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends CrudRepository<Book,Integer> {

    @Query("select b from Book b")
    List<Book> getAll();

    @Query("select b from Book b where b.id=:id")
    Book getById(@Param("id") Integer id);

}
