package com.mybookstore.mybookstorebackend.dao;


import com.mybookstore.mybookstorebackend.entity.Order;
import com.mybookstore.mybookstorebackend.entity.OrderItem;
import com.mybookstore.mybookstorebackend.entity.User;
import com.mybookstore.mybookstorebackend.result.OrderItemWithTotalResult;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public interface OrderDao {

    Integer addOrder(Integer tot_num, BigDecimal tot_Price, User user);

    Integer addOrderItems(Integer orderId, List<OrderItem> orderItems);

    Order getById(Integer id);

    List<OrderItemWithTotalResult> getAllOrdersWithItems();

    List<Order> getAll();

    Integer deleteById(Integer id);

    Integer getOrderStatusById(Integer order_id);

    Integer changeOrderStatusById(Integer order_id, Integer status);

    List<Order> getOrdersByUserId(Integer user_id);

    List<OrderItemWithTotalResult> getByUserIdAndStatus(Integer user_id, Integer status);

    OrderItemWithTotalResult getItemsAndTotalById(Integer order_id);

    List<OrderItem> getOrderItemsByBookId(Integer book_id);

    Timestamp getOrderTimestampById(Integer order_id);

    Integer getOrderUidById(Integer order_id);
}
