package com.mybookstore.mybookstorebackend.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mybookstore.mybookstorebackend.constant.Constant;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "`order`")  // !!! order is SQL keyword, add ``
public class Order {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Integer order_id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName="user_id")
    private User user;

    // 删除 Order，需要级联删除 OrderItem
    // 初始加载的时候不需要把 OrderItem 也拿进来，到要用的时候再建立连接从数据库拿
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", referencedColumnName="order_id")
    private List<OrderItem> orderItemList;

    @JsonFormat(pattern = Constant.DATE_FORMAT_SECOND, timezone = Constant.TIME_ZONE)
    private Timestamp time;

    private BigDecimal total_price;
    private Integer num;
    private Integer status;

    public void setOrder_id(Integer order_id) {
        this.order_id = order_id;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Timestamp getTime() {
        return time;
    }

    public BigDecimal getTotal_price() {
        return total_price;
    }

    public void setTotal_price(BigDecimal total_price) {
        this.total_price = total_price;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public void setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
    }
}
