package com.mybookstore.mybookstorebackend.controller;

import com.mybookstore.mybookstorebackend.constant.Constant;
import com.mybookstore.mybookstorebackend.entity.CartItem;
import com.mybookstore.mybookstorebackend.entity.Order;
import com.mybookstore.mybookstorebackend.entity.OrderItem;
import com.mybookstore.mybookstorebackend.entity.User;
import com.mybookstore.mybookstorebackend.repository.CartItemRepository;
import com.mybookstore.mybookstorebackend.repository.OrderItemRepository;
import com.mybookstore.mybookstorebackend.repository.OrderRepository;
import com.mybookstore.mybookstorebackend.repository.UserRepository;
import com.mybookstore.mybookstorebackend.result.OrderItemResult;
import com.mybookstore.mybookstorebackend.result.OrderItemWithTotalResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping(path = "/order", method = RequestMethod.POST)
public class OrderController {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @PostMapping(path = "/createOrderFromUserCart")
    public @ResponseBody Integer CreateOrderFromUserCart(@RequestParam Integer user_id){

        Order order = new Order();


        User user = userRepository.getById(user_id);
        if(user == null) return Constant.FAIL;
        order.setUser(user);

        // get all cart items that will turn into order
        List<CartItem> cart = cartItemRepository.getByUserId(user.getUser_id());
        if(cart == null) return Constant.FAIL;

        // change to order item
        // calculate total num and price
        Integer tot_num = 0;
        BigDecimal tot_price = BigDecimal.valueOf(0);
        List<OrderItem> orderItem = new ArrayList<>();
        for(CartItem ci: cart){
            OrderItem oi = new OrderItem();
            oi.setBook_id(ci.getBook_id());
            Integer num = ci.getNum();
            oi.setNum(num);
            tot_num += num;
            BigDecimal price = ci.getBook().getPrice();
            oi.setPrice(price);
            tot_price = tot_price.add(price.multiply(BigDecimal.valueOf(num)));
            orderItem.add(oi);
        }

        order.setNum(tot_num);
        order.setTotal_price(tot_price);
        Date date =new Date();
        order.setTime(new Timestamp(date.getTime()));
        order.setStatus(Constant.UNPAID);

        // delete cart items turned into order
        cartItemRepository.deleteAll(cart);

        // store order
        orderRepository.save(order);
        Integer orderId = order.getOrder_id();

        // store order item
        for(OrderItem oi: orderItem){
            oi.setOrder_id(orderId);
        }
        orderItemRepository.saveAll(orderItem);

        return orderId;
    }

    @PostMapping(path = "/getByOrderId")
    public @ResponseBody Order getOrderById(@RequestParam Integer order_id){
        return orderRepository.getByOrderId(order_id);
    }

    @PostMapping(path = "/getOrderStatusById")
    public @ResponseBody Integer getOrderStatusById(@RequestParam Integer order_id){
        Order o = orderRepository.getByOrderId(order_id);
        if(o != null) return o.getStatus();
        else return Constant.FAIL;
    }

    @PostMapping(path = "/changeOrderStatusById")
    public @ResponseBody Integer changeOrderStatusById(@RequestParam Integer order_id, @RequestParam Integer status){
        Order o = orderRepository.getByOrderId(order_id);
        o.setStatus(status);
        orderRepository.save(o);
        if(Objects.equals(o.getStatus(), status)) return Constant.SUCCESS;
        else return Constant.FAIL;
    }

    @PostMapping(path = "/getByUserId")
    public @ResponseBody List<Order> getByUserId(@RequestParam Integer user_id){
        return orderRepository.getByUserId(user_id);
    }


    @PostMapping(path = "/getByUserIdAndStatus")
    public @ResponseBody List<OrderItemWithTotalResult> getByUserIdAndStatus(@RequestParam Integer user_id, @RequestParam Integer status){
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

    @PostMapping(path = "/getItemsAndTotalByOrderId")
    public @ResponseBody OrderItemWithTotalResult getItemsAndTotalById(@RequestParam Integer order_id){
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

}
