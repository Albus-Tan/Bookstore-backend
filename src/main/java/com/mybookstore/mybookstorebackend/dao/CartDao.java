package com.mybookstore.mybookstorebackend.dao;

import com.mybookstore.mybookstorebackend.result.CartResult;

import java.util.List;

public interface CartDao {

    Integer addItem(Integer user_id, Integer book_id, Integer num);

    Integer deleteItem(Integer user_id, Integer book_id);

    Integer modifyItemNum(Integer user_id, Integer book_id, Integer num);

    Integer clearAllByUserId(Integer user_id);

    List<CartResult> getAllByUserId(Integer user_id);
}
