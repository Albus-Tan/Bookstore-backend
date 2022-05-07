package com.mybookstore.mybookstorebackend.constant;

import org.springframework.data.relational.core.sql.In;

public class Constant {
    public static final String USER_ID = "userId";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String USER_TYPE = "userType";
    public static final String REMEMBER_ME = "remember";
    public static final String JSESSIONID = "JSESSIONID";

    public static final Integer NO_SUCH_USER = -1;
    public static final Integer MANAGER = 1;
    public static final Integer CUSTOMER = 0;

    public static final Integer NO_SUCH_CART_ITEM = -1;

    public static final Integer SUCCESS = 0;
    public static final Integer FAIL = -1;

}
