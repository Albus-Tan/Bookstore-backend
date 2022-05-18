package com.mybookstore.mybookstorebackend.controller;

import com.mybookstore.mybookstorebackend.entity.Book;
import com.mybookstore.mybookstorebackend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(path = "/book", method = RequestMethod.POST)
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping(path = "/add")
    public @ResponseBody
    Integer AddBook(@RequestParam String isbn, @RequestParam String name,
                    @RequestParam String type, @RequestParam String author, @RequestParam BigDecimal price,
                    @RequestParam String description, @RequestParam Integer inventory, @RequestParam String image) {
        return bookService.addBook(isbn, name, type, author, price, description, inventory, image);
    }

    @PostMapping(path ="/getAll")
    public @ResponseBody List<Book> getAll() {
        return bookService.getAllBooks();
    }

    @PostMapping(path ="/getById")
    public @ResponseBody Book getById(@RequestParam Integer id) {
        return bookService.getBookById(id);
    }

    @PostMapping(path ="/delete")
    public @ResponseBody Integer deleteById(@RequestParam Integer id) {
        return bookService.deleteBookById(id);
    }

    @PostMapping(path ="/update")
    public @ResponseBody Integer update(@RequestParam Integer id, @RequestParam String isbn, @RequestParam String name,
                                     @RequestParam String type, @RequestParam String author, @RequestParam BigDecimal price,
                                     @RequestParam String description, @RequestParam Integer inventory, @RequestParam String image) {
        return bookService.updateBookById(id, isbn, name, type, author, price, description, inventory, image);
    }

}
