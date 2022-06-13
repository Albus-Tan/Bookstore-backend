package com.mybookstore.mybookstorebackend.daoimpl;

import com.mybookstore.mybookstorebackend.constant.Constant;
import com.mybookstore.mybookstorebackend.dao.OrderDao;
import com.mybookstore.mybookstorebackend.entity.Order;
import com.mybookstore.mybookstorebackend.entity.OrderItem;
import com.mybookstore.mybookstorebackend.entity.User;
import com.mybookstore.mybookstorebackend.repository.OrderItemRepository;
import com.mybookstore.mybookstorebackend.repository.OrderRepository;
import com.mybookstore.mybookstorebackend.result.OrderItemResult;
import com.mybookstore.mybookstorebackend.result.OrderItemWithTotalResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Repository
public class OrderDaoImpl implements OrderDao {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Integer addOrder(Integer tot_num, BigDecimal tot_Price, User user){
        Order order = new Order();
        order.setUser(user);
        order.setNum(tot_num);
        order.setTotal_price(tot_Price);
        Date date =new Date();
        order.setTime(new Timestamp(date.getTime()));
        order.setStatus(Constant.UNPAID);
        // store order
        orderRepository.save(order);
        return order.getOrder_id();
    }

    @Override
    public Integer addOrderItems(Integer orderId, List<OrderItem> orderItems){
        // store order item
        for(OrderItem oi: orderItems){
            oi.setOrder_id(orderId);
        }
        orderItemRepository.saveAll(orderItems);
        return Constant.SUCCESS;
    }

    @Override
    public Order getById(Integer id){
        return orderRepository.getByOrderId(id);
    }

    @Override
    public List<Order> getAll(){
        return orderRepository.getAll();
    }

    @Override
    public Integer deleteById(Integer id){
        orderRepository.deleteById(id);
        return Constant.SUCCESS;
    }

    @Override
    public Integer getOrderStatusById(Integer order_id){
        Order o = orderRepository.getByOrderId(order_id);
        if(o != null) return o.getStatus();
        else return Constant.FAIL;
    }


    @Override
    public Integer changeOrderStatusById(Integer order_id, Integer status){
        Order o = orderRepository.getByOrderId(order_id);
        o.setStatus(status);
        orderRepository.save(o);
        if(Objects.equals(o.getStatus(), status)) return Constant.SUCCESS;
        else return Constant.FAIL;
    }

    @Override
    public List<Order> getOrdersByUserId(Integer user_id){
        return orderRepository.getByUserId(user_id);
    }


    @Override
    public List<OrderItemWithTotalResult> getAllOrdersWithItems(){
        List<Order> orderList = orderRepository.getAll();
        List<OrderItemWithTotalResult> orderItemResultList = new ArrayList<>();
        for(Order o : orderList){
            OrderItemWithTotalResult oir = getItemsAndTotalById(o.getOrder_id());
            orderItemResultList.add(oir);
        }
        return orderItemResultList;
    }

    @Override
    public List<OrderItemWithTotalResult> getByUserIdAndStatus(Integer user_id, Integer status){
        List<Order> orderList;
        if(Objects.equals(status, Constant.ALL)) orderList = orderRepository.getByUserId(user_id);
        else orderList = orderRepository.getByUserIdAndStatus(user_id, status);
        List<OrderItemWithTotalResult> orderItemResultList = new ArrayList<>();
        for(Order o : orderList){
            OrderItemWithTotalResult oir = getItemsAndTotalById(o.getOrder_id());
            orderItemResultList.add(oir);
        }
        return orderItemResultList;
    }

    @Override
    public OrderItemWithTotalResult getItemsAndTotalById(Integer order_id){
        List<OrderItem> orderItemList = orderItemRepository.getByOrderId(order_id);
        List<OrderItemResult> orderItemResultList = new ArrayList<>();
        for(OrderItem oi : orderItemList){
            OrderItemResult oir = new OrderItemResult(oi.getNum(), oi.getBook().getName(), oi.getPrice(), oi.getBook_id());
            orderItemResultList.add(oir);
        }
        Order order = orderRepository.getByOrderId(order_id);
        OrderItemWithTotalResult orderItemWithTotalResult = new OrderItemWithTotalResult(orderItemResultList,order.getTotal_price(),order.getNum(), order.getStatus(), order.getOrder_id(), order.getTime());
        return orderItemWithTotalResult;
    }

    @Override
    public List<OrderItem> getOrderItemsByBookId(Integer book_id){
        return orderItemRepository.getByBookId(book_id);
    }

    @Override
    public Timestamp getOrderTimestampById(Integer order_id){
        return orderRepository.getByOrderId(order_id).getTime();
    }

}
