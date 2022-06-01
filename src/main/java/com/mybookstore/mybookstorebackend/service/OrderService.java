package com.mybookstore.mybookstorebackend.service;

import com.mybookstore.mybookstorebackend.entity.Order;
import com.mybookstore.mybookstorebackend.result.BookSalesResult;
import com.mybookstore.mybookstorebackend.result.OrderItemWithTotalResult;
import com.mybookstore.mybookstorebackend.result.UserConsumeResult;

import java.util.List;

public interface OrderService {

    Integer createOrderFromUserCart(Integer user_id);

    Order getById(Integer id);

    List<Order> getAll();

    Integer deleteById(Integer id);

    List<OrderItemWithTotalResult> getAllOrdersWithItems();

    Integer getOrderStatusById(Integer order_id);

    Integer changeOrderStatusById(Integer order_id, Integer status);

    List<Order> getOrdersByUserId(Integer user_id);

    List<OrderItemWithTotalResult> getByUserIdAndStatus(Integer user_id, Integer status);

    OrderItemWithTotalResult getItemsAndTotalById(Integer order_id);

    List<BookSalesResult> analysisBookSales();

    BookSalesResult getBookSalesResultByBookId(Integer id);

    List<UserConsumeResult> analysisUserConsume();

    UserConsumeResult getUserConsumeResultByUserId(Integer user_id);

}
