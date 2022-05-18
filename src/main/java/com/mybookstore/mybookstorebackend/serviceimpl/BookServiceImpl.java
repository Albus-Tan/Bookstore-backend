package com.mybookstore.mybookstorebackend.serviceimpl;

import com.mybookstore.mybookstorebackend.dao.BookDao;
import com.mybookstore.mybookstorebackend.entity.Book;
import com.mybookstore.mybookstorebackend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public Book getBookById(Integer id){
        return bookDao.getById(id);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookDao.getAll();
    }

    @Override
    public Integer deleteBookById(Integer id){ return bookDao.deleteById(id); }

    @Override
    public Integer updateBookById(Integer id, String isbn, String name,
                                  String type, String author, BigDecimal price,
                                  String description, Integer inventory, String image){
        return bookDao.updateById(id, isbn, name, type, author, price, description, inventory, image);
    }

    @Override
    public Integer addBook(String isbn, String name, String type, String author,
                           BigDecimal price, String description, Integer inventory, String image){
        return bookDao.add(isbn, name, type, author, price, description, inventory, image);
    }
}
