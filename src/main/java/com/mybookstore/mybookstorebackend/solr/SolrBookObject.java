package com.mybookstore.mybookstorebackend.solr;

import com.mybookstore.mybookstorebackend.entity.Book;
import org.apache.solr.client.solrj.beans.Field;

public class SolrBookObject {

    // need to use string for id
    @Field("id")
    private String id;

    @Field("isbn")
    private String isbn;

    @Field("name")
    private String name;

    @Field("author")
    private String author;

    @Field("description")
    private String description;

    @Field("image")
    private String image;

    public SolrBookObject(String id, String isbn, String name, String author, String description, String image) {
        this.id = id;
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.description = description;
        this.image = image;
    }

    public SolrBookObject() {
    }

    public SolrBookObject(Book b) {
        this.id = String.valueOf(b.getId());
        this.isbn = b.getIsbn();
        this.name = b.getName();
        this.author = b.getAuthor();
        this.description = b.getDescription();
        this.image = b.getImage();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
