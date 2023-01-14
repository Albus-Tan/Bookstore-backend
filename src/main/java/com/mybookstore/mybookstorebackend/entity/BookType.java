package com.mybookstore.mybookstorebackend.entity;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@NodeEntity
public class BookType {

    @Id
    @GeneratedValue
    private Long id;

    private String type;

    private BookType() {
        // Empty constructor required as of Neo4j API 2.0.5
    };

    public BookType(String type) {
        this.type = type;
    }

    @Relationship(type = "SUBCLASS")
    public Set<BookType> relatedTypes;

    public void relateWith(BookType type) {
        if (relatedTypes == null) {
            relatedTypes = new HashSet<>();
        }
        relatedTypes.add(type);
    }

    public String toString() {
        return this.type + "'s related types => "
                + Optional.ofNullable(this.relatedTypes).orElse(
                        Collections.emptySet()).stream()
                .map(BookType::getType)
                .collect(Collectors.toList());
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
