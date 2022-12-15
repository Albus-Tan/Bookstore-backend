package com.mybookstore.mybookstorebackend.entity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Integer id;

    private String isbn;
    private String name;
    private String type;
    private String author;
    private BigDecimal price;
    private String description;
    private Integer inventory;
    private String image;

    @Transient
    private BookImage bookImageContent;  // store in mongodb


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public BigDecimal getPrice() { return price;}
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getInventory() { return inventory; }
    public void setInventory(Integer inventory) { this.inventory = inventory; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    @Transient
    public BookImage getBookImageContent() {
        return bookImageContent;
    }

    public void setBookImageContent(BookImage bookImageContent) {
        this.bookImageContent = bookImageContent;
    }
}
