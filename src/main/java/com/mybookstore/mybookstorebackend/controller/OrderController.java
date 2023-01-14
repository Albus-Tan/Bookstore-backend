package com.mybookstore.mybookstorebackend.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mybookstore.mybookstorebackend.constant.Constant;
import com.mybookstore.mybookstorebackend.entity.Order;
import com.mybookstore.mybookstorebackend.result.BookSalesResult;
import com.mybookstore.mybookstorebackend.result.OrderItemWithTotalResult;
import com.mybookstore.mybookstorebackend.result.UserConsumeResult;
import com.mybookstore.mybookstorebackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

import static com.mybookstore.mybookstorebackend.constant.Constant.*;

@Controller
@RequestMapping(path = "/order", method = RequestMethod.POST)
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @PostMapping(path = "/createOrderFromUserCart")
    public @ResponseBody Integer CreateOrderFromUserCart(@RequestParam Integer user_id){

        kafkaTemplate.send("CreateOrder",  "key", user_id.toString());

        return SUCCESS;
    }

    @PostMapping(path = "/createOrderFromUserCartNoMsg")
    public @ResponseBody Integer CreateOrderFromUserCartNoMsg(@RequestParam Integer user_id){
        return orderService.createOrderFromUserCart(user_id);
    }

    @PostMapping(path = "/getAllOrders")
    public @ResponseBody List<Order> getAllOrders(){
        return orderService.getAll();
    }

    @PostMapping(path = "/getAllOrdersWithItems")
    public @ResponseBody List<OrderItemWithTotalResult> getAllOrdersWithItems(){
        return orderService.getAllOrdersWithItems();
    }

    @PostMapping(path = "/deleteByOrderId")
    public @ResponseBody Integer deleteByOrderId(@RequestParam Integer order_id) {
        return orderService.deleteById(order_id);
    }

    @PostMapping(path = "/getByOrderId")
    public @ResponseBody Order getOrderById(@RequestParam Integer order_id){
        return orderService.getById(order_id);
    }

    @PostMapping(path = "/getOrderStatusById")
    public @ResponseBody Integer getOrderStatusById(@RequestParam Integer order_id){
        return orderService.getOrderStatusById(order_id);
    }

    @PostMapping(path = "/changeOrderStatusById")
    public @ResponseBody Integer changeOrderStatusById(@RequestParam Integer order_id, @RequestParam Integer status){
        return orderService.changeOrderStatusById(order_id,status);
    }

    @PostMapping(path = "/getByUserId")
    public @ResponseBody List<Order> getByUserId(@RequestParam Integer user_id){
        return orderService.getOrdersByUserId(user_id);
    }

    @PostMapping(path = "/getByUserIdAndStatus")
    public @ResponseBody List<OrderItemWithTotalResult> getByUserIdAndStatus(@RequestParam Integer user_id, @RequestParam Integer status){
        return orderService.getByUserIdAndStatus(user_id, status);
    }

    @PostMapping(path = "/getItemsAndTotalByOrderId")
    public @ResponseBody OrderItemWithTotalResult getItemsAndTotalById(@RequestParam Integer order_id){
        return orderService.getItemsAndTotalById(order_id);
    }

    @PostMapping(path = "/analysisBookSales")
    public @ResponseBody List<BookSalesResult> analysisBookSales(@JsonFormat(pattern = DATE_FORMAT_SECOND, timezone = TIME_ZONE) Timestamp startDate, @JsonFormat(pattern = DATE_FORMAT_SECOND, timezone = TIME_ZONE) Timestamp endDate){
        return orderService.analysisBookSales(startDate, endDate);
    }

    @PostMapping(path = "/analysisUserConsume")
    public @ResponseBody List<UserConsumeResult> analysisUserConsume(@JsonFormat(pattern = DATE_FORMAT_SECOND, timezone = TIME_ZONE) Timestamp startDate, @JsonFormat(pattern = DATE_FORMAT_SECOND, timezone = TIME_ZONE) Timestamp endDate){
        return orderService.analysisUserConsume(startDate, endDate);
    }

    @PostMapping(path = "/getUserConsumeResultByUserId")
    public @ResponseBody List<BookSalesResult> getUserBookConsumeDetailResultByUserId(@RequestParam Integer user_id, @JsonFormat(pattern = DATE_FORMAT_SECOND, timezone = TIME_ZONE) Timestamp startDate, @JsonFormat(pattern = DATE_FORMAT_SECOND, timezone = TIME_ZONE) Timestamp endDate){
        return orderService.getUserBookConsumeDetailResultByUserId(user_id, startDate, endDate);
    }

    @PostMapping(path = "/getUserConsumeTotalResultByUserId")
    public @ResponseBody UserConsumeResult getUserBookConsumeDetailTotalResultByUserId(@RequestParam Integer user_id, @JsonFormat(pattern = DATE_FORMAT_SECOND, timezone = TIME_ZONE) Timestamp startDate, @JsonFormat(pattern = DATE_FORMAT_SECOND, timezone = TIME_ZONE) Timestamp endDate){
        return orderService.getUserConsumeResultByUserId(user_id, startDate, endDate);
    }

}
