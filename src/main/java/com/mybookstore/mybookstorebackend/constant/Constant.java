package com.mybookstore.mybookstorebackend.constant;

public class Constant {

    // Date
    // 统一时间格式
    public static final String DATE_FORMAT = "yyyy-MM-dd";
    public static final String DATE_FORMAT_SECOND = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_ZONE = "GMT+8";

    // Cart
    // 购物车结果
    public static final Integer NO_SUCH_CART_ITEM = -1;

    // Result
    // 操作结果
    public static final Integer SUCCESS = 0;
    public static final Integer FAIL = -1;

    // Order status
    // 订单状态
    public static final Integer ALL = -1; // 返回该用户的全部状态订单，但是此状态不记录在数据库中
    public static final Integer UNPAID = 0;  // 0 已下单未付款
    public static final Integer PAID = 1;  // 1 下单已付款
    public static final Integer ON_SHIP = 2;  // 2 配送中
    public static final Integer TO_CONFIRM = 3;  // 3 待签收
    public static final Integer FINISH = 4;  // 4 已完成
    public static final Integer CANCEL= 5;  // 5 已取消

    // User status
    // 用户状态
    public static final Integer NORMAL = 0; // 0 正常
    public static final Integer BANNED = 1; // 1 被禁用

    // User type
    // 用户类型
    public static final Integer NO_SUCH_USER = -1;
    public static final Integer MANAGER = 1;
    public static final Integer CUSTOMER = 0;

    // 注册结果
    public static final Integer ALREADY_EXIST = -2;


}
