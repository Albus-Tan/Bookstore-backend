package com.mybookstore.mybookstorebackend.daoimpl;
import com.alibaba.fastjson.JSONArray;
import com.mybookstore.mybookstorebackend.constant.Constant;
import com.mybookstore.mybookstorebackend.constant.RedisKey;
import com.mybookstore.mybookstorebackend.dao.BookDao;
import com.mybookstore.mybookstorebackend.entity.Book;
import com.mybookstore.mybookstorebackend.repository.BookRepository;
import com.mybookstore.mybookstorebackend.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Book getById(Integer id){
        Book book = null;
        System.out.println("Searching Book: " + id + " in Redis");
        Object p = redisUtil.get(RedisKey.BOOK_PREFIX + id);
        if (p == null) {
            System.out.println("Book: " + id + " is not in Redis");
            System.out.println("Searching Book: " + id + " in DB");
            book = bookRepository.getById(id);
            redisUtil.set(RedisKey.BOOK_PREFIX + id, JSONArray.toJSON(book));
        } else {
            book = JSONArray.parseObject(p.toString(), Book.class);
            System.out.println("Book: " + id + " is in Redis");
        }
        return book;
    }

    @Override
    public List<Book> getAll() {
        List<Book> bookList = null;
        System.out.println("Getting allBooksList in Redis");
        Object p = redisUtil.get(RedisKey.ALL_BOOKS_LIST);
        if(p == null){
            System.out.println("AllBooksList is not in Redis");
            System.out.println("Getting allBooksList in DB");
            bookList = bookRepository.getAll();
            redisUtil.set(RedisKey.ALL_BOOKS_LIST, JSONArray.toJSON(bookList));
        } else {
            bookList = JSONArray.parseArray(p.toString(), Book.class);
            System.out.println("AllBooksList is in Redis");
        }
        return bookList;
    }

    @Override
    public Integer deleteById(Integer id){
        // disable all book cache
        if(redisUtil.hasKey(RedisKey.ALL_BOOKS_LIST)){
            redisUtil.del(RedisKey.ALL_BOOKS_LIST);
            System.out.println("book delete, AllBooksList cache deleted in Redis");
        }

        // get book
        Book b = getById(id);

        if(b == null) return Constant.FAIL;

        // delete in cache and in DB
        System.out.println("Delete Book: " + id + " in Redis");
        if(redisUtil.hasKey(RedisKey.BOOK_PREFIX + id)) redisUtil.del(RedisKey.BOOK_PREFIX + id);
        System.out.println("Delete Book: " + id + " in DB");
        bookRepository.delete(b);
        return Constant.SUCCESS;
    }

    @Override
    public Integer updateById(Integer id, String isbn, String name,
                              String type, String author, BigDecimal price,
                              String description, Integer inventory, String image){
        // disable all book cache
        if(redisUtil.hasKey(RedisKey.ALL_BOOKS_LIST)){
            redisUtil.del(RedisKey.ALL_BOOKS_LIST);
            System.out.println("book update, AllBooksList cache deleted in Redis");
        }

        Book b = getById(id);

        if(b == null) return Constant.FAIL;

        b.setIsbn(isbn);
        b.setPrice(price);
        b.setType(type);
        b.setName(name);
        b.setInventory(inventory);
        b.setDescription(description);
        b.setImage(image);
        b.setAuthor(author);
        System.out.println("Update Book: " + id + " in Redis");
        redisUtil.update(RedisKey.BOOK_PREFIX + id, JSONArray.toJSON(b));
        System.out.println("Update Book: " + id + " in DB");
        bookRepository.save(b);
        return Constant.SUCCESS;
    }

    @Override
    public Integer add(String isbn, String name, String type, String author,
                       BigDecimal price, String description, Integer inventory, String image){

        // disable all book cache
        if(redisUtil.hasKey(RedisKey.ALL_BOOKS_LIST)){
            redisUtil.del(RedisKey.ALL_BOOKS_LIST);
            System.out.println("book add, AllBooksList cache deleted in Redis");
        }


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
        Integer id = book.getId();
        System.out.println("Add Book: " + id + " in DB");
        System.out.println("Add Book: " + id + " in Redis");
        redisUtil.set(RedisKey.BOOK_PREFIX + id, JSONArray.toJSON(book));
        return id;
    }

    @Override
    public Integer modifyInventory(Integer id, Integer inventory){

        // disable all book cache
        if(redisUtil.hasKey(RedisKey.ALL_BOOKS_LIST)){
            redisUtil.del(RedisKey.ALL_BOOKS_LIST);
            System.out.println("book inventory modify, AllBooksList cache deleted in Redis");
        }

        Book b = getById(id);

        if(inventory < 0) return Constant.FAIL;

        b.setInventory(inventory);
        System.out.println("Modify Book: " + id + " in DB");
        bookRepository.save(b);
        System.out.println("Modify Book: " + id + " in Redis");
        redisUtil.update(RedisKey.BOOK_PREFIX + id, JSONArray.toJSON(b));
        return Constant.SUCCESS;
    }

}
