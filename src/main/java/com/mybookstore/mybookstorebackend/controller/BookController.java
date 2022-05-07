package com.mybookstore.mybookstorebackend.controller;

import com.mybookstore.mybookstorebackend.entity.Book;
import com.mybookstore.mybookstorebackend.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/book", method = RequestMethod.POST)
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @PostMapping(path = "/add")
    public @ResponseBody
    Integer AddBook(@RequestParam String isbn, @RequestParam String name,
                    @RequestParam String type, @RequestParam String author, @RequestParam Double price,
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

}
