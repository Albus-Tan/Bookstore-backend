package com.mybookstore.mybookstorebackend.repository;
import com.mybookstore.mybookstorebackend.entity.OrderItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderItemRepository extends CrudRepository<OrderItem,Integer> {

    @Query("select oi from OrderItem oi where oi.order_id=:order_id")
    List<OrderItem> getByOrderId(@Param("order_id") Integer order_id);

    @Query("select oi from OrderItem oi where oi.order_id=:order_id and oi.book_id=:book_id")
    OrderItem getByOrderIdAndBookId(@Param("order_id") Integer order_id, @Param("book_id") Integer book_id);
}

