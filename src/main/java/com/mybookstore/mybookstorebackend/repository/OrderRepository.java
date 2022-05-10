package com.mybookstore.mybookstorebackend.repository;

import com.mybookstore.mybookstorebackend.entity.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order,Integer> {

    @Query("select o from Order o where o.order_id=:order_id")
    Order getByOrderId(@Param("order_id") Integer order_id);

    @Query("select o from Order o where o.user.user_id=:user_id")
    List<Order> getByUserId(@Param("user_id") Integer user_id);

    @Query("select o from Order o where o.user.user_id=:user_id and o.status=:status")
    List<Order> getByUserIdAndStatus(@Param("user_id") Integer user_id, @Param("status") Integer status);

    @Query("select o from Order o")
    List<Order> getAll();

}
