package com.mybookstore.mybookstorebackend.controller;

import com.mybookstore.mybookstorebackend.constant.Constant;
import com.mybookstore.mybookstorebackend.constant.SolrConstant;
import com.mybookstore.mybookstorebackend.entity.Book;
import com.mybookstore.mybookstorebackend.service.BookService;
import com.mybookstore.mybookstorebackend.solr.BookObjectBinding;
import com.mybookstore.mybookstorebackend.solr.SolrBookObject;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(path = "/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookObjectBinding bookObjectBinding;

    @PostMapping(path = "/add")
    public @ResponseBody
    Integer AddBook(@RequestParam String isbn, @RequestParam String name,
                    @RequestParam String type, @RequestParam String author, @RequestParam BigDecimal price,
                    @RequestParam String description, @RequestParam Integer inventory, @RequestParam String image) {
        return bookService.addBook(isbn, name, type, author, price, description, inventory, image);
    }

    @RequestMapping(path ="/getAll")
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

    @PostMapping(path = "/indexing")
    public @ResponseBody String indexing() throws SolrServerException, IOException {
        bookObjectBinding.indexing();
        return "OK";
    }

    @PostMapping(path = "/querying/description")
    public @ResponseBody List<SolrBookObject> queryingDescription(@RequestParam String keyword) throws SolrServerException, IOException {
        return bookObjectBinding.querying(SolrConstant.DESCRIPTION_FIELD_NAME, keyword);
    }

}
