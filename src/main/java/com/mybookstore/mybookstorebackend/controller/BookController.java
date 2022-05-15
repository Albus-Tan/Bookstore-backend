package com.mybookstore.mybookstorebackend.controller;

import com.mybookstore.mybookstorebackend.constant.Constant;
import com.mybookstore.mybookstorebackend.entity.Book;
import com.mybookstore.mybookstorebackend.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(path = "/book", method = RequestMethod.POST)
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @PostMapping(path = "/add")
    public @ResponseBody
    Integer AddBook(@RequestParam String isbn, @RequestParam String name,
                    @RequestParam String type, @RequestParam String author, @RequestParam BigDecimal price,
                    @RequestParam String description, @RequestParam Integer inventory, @RequestParam String image) {
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

    @PostMapping(path ="/getAll")
    public @ResponseBody List<Book> getAll() {
        return bookRepository.getAll();
    }

    @PostMapping(path ="/getById")
    public @ResponseBody Book getById(@RequestParam Integer id) {
        return bookRepository.getById(id);
    }

    @PostMapping(path ="/delete")
    public @ResponseBody Integer deleteById(@RequestParam Integer id) {
        Book b = bookRepository.getById(id);
        if(b == null) return Constant.FAIL;
        bookRepository.delete(b);
        return Constant.SUCCESS;
    }

    @PostMapping(path ="/update")
    public @ResponseBody Integer update(@RequestParam Integer id, @RequestParam String isbn, @RequestParam String name,
                                     @RequestParam String type, @RequestParam String author, @RequestParam BigDecimal price,
                                     @RequestParam String description, @RequestParam Integer inventory, @RequestParam String image) {
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

}
