package com.mybookstore.mybookstorebackend.service;

import com.mybookstore.mybookstorebackend.entity.Order;
import com.mybookstore.mybookstorebackend.entity.OrderItem;
import com.mybookstore.mybookstorebackend.entity.User;
import com.mybookstore.mybookstorebackend.result.BookSalesResult;
import com.mybookstore.mybookstorebackend.result.OrderItemWithTotalResult;
import com.mybookstore.mybookstorebackend.result.UserConsumeResult;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public interface OrderService {

    Integer createOrderFromUserCart(Integer user_id);

    Integer createOrder(Integer tot_num, BigDecimal tot_price, User user, List<OrderItem> orderItem);

    Order getById(Integer id);

    List<Order> getAll();

    Integer deleteById(Integer id);

    List<OrderItemWithTotalResult> getAllOrdersWithItems();

    Integer getOrderStatusById(Integer order_id);

    Integer changeOrderStatusById(Integer order_id, Integer status);

    List<Order> getOrdersByUserId(Integer user_id);

    List<OrderItemWithTotalResult> getByUserIdAndStatus(Integer user_id, Integer status);

    OrderItemWithTotalResult getItemsAndTotalById(Integer order_id);

    List<BookSalesResult> analysisBookSales(Timestamp start, Timestamp end);

    BookSalesResult getBookSalesResultByBookId(Integer id, Timestamp start, Timestamp end, Integer uid);

    List<UserConsumeResult> analysisUserConsume(Timestamp start, Timestamp end);

    UserConsumeResult getUserConsumeResultByUserId(Integer user_id, Timestamp start, Timestamp end);

    List<BookSalesResult> getUserBookConsumeDetailResultByUserId(Integer user_id, Timestamp start, Timestamp end);

}
