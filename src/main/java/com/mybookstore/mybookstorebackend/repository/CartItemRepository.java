package com.mybookstore.mybookstorebackend.repository;

import com.mybookstore.mybookstorebackend.entity.CartItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartItemRepository extends CrudRepository<CartItem,Integer> {
    @Query("select ci from CartItem ci where ci.user_id=:user_id")
    List<CartItem> getByUserId(@Param("user_id") Integer user_id);

    @Query("select ci from CartItem ci where ci.user_id=:user_id and ci.book_id=:book_id")
    CartItem getByUserIdAndBookId(@Param("user_id") Integer user_id, @Param("book_id") Integer book_id);
}
