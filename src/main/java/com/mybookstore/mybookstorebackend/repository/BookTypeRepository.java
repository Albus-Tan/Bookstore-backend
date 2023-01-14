package com.mybookstore.mybookstorebackend.repository;

import com.mybookstore.mybookstorebackend.entity.BookType;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface BookTypeRepository extends Neo4jRepository<BookType, Long> {

    BookType findByType(String type);
//    List<BookType> findByRelatedTypesType(String type);
//    List<BookType> findBookTypesByRelatedTypes(Set<BookType> relatedTypes);
}
