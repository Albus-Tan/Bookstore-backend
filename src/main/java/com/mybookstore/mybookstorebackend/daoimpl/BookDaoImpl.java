package com.mybookstore.mybookstorebackend.daoimpl;

import com.alibaba.fastjson.JSONArray;
import com.mybookstore.mybookstorebackend.constant.Constant;
import com.mybookstore.mybookstorebackend.constant.Neo4jConstant;
import com.mybookstore.mybookstorebackend.constant.RedisKey;
import com.mybookstore.mybookstorebackend.dao.BookDao;
import com.mybookstore.mybookstorebackend.entity.Book;
import com.mybookstore.mybookstorebackend.entity.BookImage;
import com.mybookstore.mybookstorebackend.entity.BookType;
import com.mybookstore.mybookstorebackend.repository.BookImageRepository;
import com.mybookstore.mybookstorebackend.repository.BookRepository;
import com.mybookstore.mybookstorebackend.repository.BookTypeRepository;
import com.mybookstore.mybookstorebackend.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;

@Repository
public class BookDaoImpl implements BookDao {

    @Autowired
    private BookImageRepository bookImageRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private BookTypeRepository bookTypeRepository;

    @Override
    public Book getById(Integer id) {
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

        Optional<BookImage> bookImage = bookImageRepository.findById(id);
        if (bookImage.isPresent()) {
            System.out.println("getById: bookImage Not Null " + id);
            book.setBookImageContent(bookImage.get());
        } else {
            System.out.println("getById: bookImage is Null " + id);
            book.setBookImageContent(null);
        }

        return book;
    }

    @Override
    public List<Book> getAll() {
        List<Book> bookList = null;
        System.out.println("Getting allBooksList in Redis");
        Object p = redisUtil.get(RedisKey.ALL_BOOKS_LIST);
        if (p == null) {
            System.out.println("AllBooksList is not in Redis");
            System.out.println("Getting allBooksList in DB");
            bookList = bookRepository.getAll();
            redisUtil.set(RedisKey.ALL_BOOKS_LIST, JSONArray.toJSON(bookList));
        } else {
            bookList = JSONArray.parseArray(p.toString(), Book.class);
            System.out.println("AllBooksList is in Redis");
        }

        for (Book book : bookList) {
            Integer id = book.getId();
            Optional<BookImage> bookImage = bookImageRepository.findById(id);
            if (bookImage.isPresent()) {
                System.out.println("getAll: bookImage Not Null " + id);
                book.setBookImageContent(bookImage.get());
            } else {
                System.out.println("getAll: bookImage is Null " + id);
                book.setBookImageContent(null);
            }
        }

        return bookList;
    }

    @Override
    public Integer deleteById(Integer id) {
        // disable all book cache
        if (redisUtil.hasKey(RedisKey.ALL_BOOKS_LIST)) {
            redisUtil.del(RedisKey.ALL_BOOKS_LIST);
            System.out.println("book delete, AllBooksList cache deleted in Redis");
        }

        // get book
        Book b = getById(id);

        if (b == null) return Constant.FAIL;

        // delete in cache and in DB
        System.out.println("Delete Book: " + id + " in Redis");
        if (redisUtil.hasKey(RedisKey.BOOK_PREFIX + id)) redisUtil.del(RedisKey.BOOK_PREFIX + id);
        System.out.println("Delete Book: " + id + " in DB");
        bookRepository.delete(b);

        // delete image
        bookImageRepository.deleteById(id);

        return Constant.SUCCESS;
    }

    @Override
    public Integer updateById(Integer id, String isbn, String name,
                              String type, String author, BigDecimal price,
                              String description, Integer inventory, String image) {
        // disable all book cache
        if (redisUtil.hasKey(RedisKey.ALL_BOOKS_LIST)) {
            redisUtil.del(RedisKey.ALL_BOOKS_LIST);
            System.out.println("book update, AllBooksList cache deleted in Redis");
        }

        Book b = getById(id);

        if (b == null) return Constant.FAIL;

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

        // update image content

        return Constant.SUCCESS;
    }

    @Override
    public Integer add(String isbn, String name, String type, String author,
                       BigDecimal price, String description, Integer inventory, String image) {

        // disable all book cache
        if (redisUtil.hasKey(RedisKey.ALL_BOOKS_LIST)) {
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

        // add image content
        BookImage bookImage = new BookImage(id, "");
        bookImageRepository.save(bookImage);

        return id;
    }

    @Override
    public Integer modifyInventory(Integer id, Integer inventory) {

        // disable all book cache
        if (redisUtil.hasKey(RedisKey.ALL_BOOKS_LIST)) {
            redisUtil.del(RedisKey.ALL_BOOKS_LIST);
            System.out.println("book inventory modify, AllBooksList cache deleted in Redis");
        }

        Book b = getById(id);

        if (inventory < 0) return Constant.FAIL;

        b.setInventory(inventory);
        System.out.println("Modify Book: " + id + " in DB");
        bookRepository.save(b);
        System.out.println("Modify Book: " + id + " in Redis");
        redisUtil.update(RedisKey.BOOK_PREFIX + id, JSONArray.toJSON(b));
        return Constant.SUCCESS;
    }

    @Override
    public void RebuildTypeLabelGraph() {
        bookTypeRepository.deleteAll();

        // construct nodes
        List<Book> bookList = bookRepository.getAll();
        for (Book b : bookList) {
            String type = b.getType();
            if (bookTypeRepository.findByType(type) == null) {
                BookType bookType = new BookType(type);
                bookTypeRepository.save(bookType);
                System.out.println("Construct and save BookType: " + type + " in Neo4j");
            }
        }

        for (String type : Neo4jConstant.TYPES) {
            BookType bookType = new BookType(type);
            bookTypeRepository.save(bookType);
            System.out.println("Construct and save Type: " + type + " in Neo4j");
        }

        // build relations
        for (Map.Entry<String, HashSet<String>> entry : Neo4jConstant.RELATIONS.entrySet()) {
            BookType key = bookTypeRepository.findByType(entry.getKey());
            HashSet<String> val_set = entry.getValue();
            for (String val_ : val_set) {
                BookType val = bookTypeRepository.findByType(val_);
                key.relateWith(val);
            }
            bookTypeRepository.save(key);
        }

    }

    @Override
    public List<BookType> GetRelatedSubclass(String type) {
        List<BookType> res = new ArrayList<>();
        BookType bookType = bookTypeRepository.findByType(type);
        if(bookType != null){
            res.add(bookType);
            if(bookType.relatedTypes != null){
                res.addAll(bookType.relatedTypes);
                for (BookType bt : bookType.relatedTypes) {
                    Set<BookType> bookTypeSet = (bookTypeRepository.findByType(bt.getType())).relatedTypes;
                    if(bookTypeSet != null) res.addAll(bookTypeSet);
                }
            }
        }

        System.out.println("GetRelatedSubclass " + type + " result");
        for (BookType bt : res) {
            System.out.println(bt.getType());
        }

        return res;
    }

    @Override
    public Set<Book> getByBookType(Set<String> types){
        Set<Book> res = new HashSet<>();
        for(String type : types){
            List<Book> search_res = bookRepository.getBooksByType(type);
            if(search_res != null) res.addAll(search_res);
        }
        return res;
    }

}
