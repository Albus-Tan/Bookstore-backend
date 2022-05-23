package com.mybookstore.mybookstorebackend.service;

import com.mybookstore.mybookstorebackend.result.CartResult;

import java.util.List;

public interface CartService {
    Integer addCartItem(Integer user_id, Integer book_id, Integer num);

    Integer deleteCartItem(Integer user_id, Integer book_id);

    Integer modifyCartItemNum(Integer user_id, Integer book_id, Integer num);

    Integer clearAllInCartByUserId(Integer user_id);

    List<CartResult> getAllInCartByUserId(Integer user_id);
}
