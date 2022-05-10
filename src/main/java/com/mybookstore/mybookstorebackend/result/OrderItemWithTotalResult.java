package com.mybookstore.mybookstorebackend.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mybookstore.mybookstorebackend.constant.Constant;

import java.sql.Timestamp;
import java.util.List;

public class OrderItemWithTotalResult {
    private List<OrderItemResult> orderItemResultList;
    private Double totalPrice;
    private Integer totalNum;
    private Integer status;
    private Integer order_id;

    @JsonFormat(pattern = Constant.DATE_FORMAT_SECOND, timezone = Constant.TIME_ZONE)
    private Timestamp time;

    public OrderItemWithTotalResult(List<OrderItemResult> orderItemResultList, Double totalPrice, Integer totalNum, Integer status, Integer order_id, Timestamp time) {
        this.orderItemResultList = orderItemResultList;
        this.totalPrice = totalPrice;
        this.totalNum = totalNum;
        this.status = status;
        this.order_id = order_id;
        this.time = time;
    }

    public List<OrderItemResult> getOrderItemResultList() {
        return orderItemResultList;
    }

    public void setOrderItemResultList(List<OrderItemResult> orderItemResultList) {
        this.orderItemResultList = orderItemResultList;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}