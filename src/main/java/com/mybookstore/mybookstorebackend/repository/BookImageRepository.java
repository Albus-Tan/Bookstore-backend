package com.mybookstore.mybookstorebackend.repository;

import com.mybookstore.mybookstorebackend.entity.BookImage;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookImageRepository extends MongoRepository<BookImage, Integer> {

}
