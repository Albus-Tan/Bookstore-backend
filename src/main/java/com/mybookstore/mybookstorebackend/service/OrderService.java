package com.mybookstore.mybookstorebackend.service;

import com.mybookstore.mybookstorebackend.entity.Order;
import com.mybookstore.mybookstorebackend.result.OrderItemWithTotalResult;

import java.util.List;

public interface OrderService {

    Integer createOrderFromUserCart(Integer user_id);

    Order getById(Integer id);

    List<Order> getAll();

    Integer deleteById(Integer id);

    Integer getOrderStatusById(Integer order_id);

    Integer changeOrderStatusById(Integer order_id, Integer status);

    List<Order> getOrdersByUserId(Integer user_id);

    List<OrderItemWithTotalResult> getByUserIdAndStatus(Integer user_id, Integer status);

    OrderItemWithTotalResult getItemsAndTotalById(Integer order_id);

}
