package com.mybookstore.mybookstorebackend.daoimpl;
import com.mybookstore.mybookstorebackend.constant.Constant;
import com.mybookstore.mybookstorebackend.dao.BookDao;
import com.mybookstore.mybookstorebackend.entity.Book;
import com.mybookstore.mybookstorebackend.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book getById(Integer id){
        return bookRepository.getById(id);
    }

    @Override
    public List<Book> getAll() {
        return bookRepository.getAll();
    }

    @Override
    public Integer deleteById(Integer id){
        Book b = bookRepository.getById(id);
        if(b == null) return Constant.FAIL;
        bookRepository.delete(b);
        return Constant.SUCCESS;
    }

    @Override
    public Integer updateById(Integer id, String isbn, String name,
                              String type, String author, BigDecimal price,
                              String description, Integer inventory, String image){
        Book b = bookRepository.getById(id);
        if(b == null) return Constant.FAIL;
        b.setIsbn(isbn);
        b.setPrice(price);
        b.setType(type);
        b.setName(name);
        b.setInventory(inventory);
        b.setDescription(description);
        b.setImage(image);
        b.setAuthor(author);
        bookRepository.save(b);
        return Constant.SUCCESS;
    }

    @Override
    public Integer add(String isbn, String name, String type, String author,
                       BigDecimal price, String description, Integer inventory, String image){
        Book book = new Book();
        book.setIsbn(isbn);
        book.setName(name);
        book.setType(type);
        book.setAuthor(author);
        book.setPrice(price);
        book.setDescription(description);
        book.setInventory(inventory);
        book.setImage(image);
        bookRepository.save(book);
        return book.getId();
    }

}
