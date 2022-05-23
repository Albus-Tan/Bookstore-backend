package com.mybookstore.mybookstorebackend.serviceimpl;

import com.mybookstore.mybookstorebackend.dao.CartDao;
import com.mybookstore.mybookstorebackend.result.CartResult;
import com.mybookstore.mybookstorebackend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartDao cartDao;

    @Override
    public Integer addCartItem(Integer user_id, Integer book_id, Integer num){
        return cartDao.addItem(user_id, book_id, num);
    }

    @Override
    public Integer deleteCartItem(Integer user_id, Integer book_id){
        return cartDao.deleteItem(user_id, book_id);
    }

    @Override
    public Integer modifyCartItemNum(Integer user_id, Integer book_id, Integer num){
        return cartDao.modifyItemNum(user_id, book_id, num);
    }

    @Override
    public Integer clearAllInCartByUserId(Integer user_id){
        return cartDao.clearAllByUserId(user_id);
    }

    @Override
    public List<CartResult> getAllInCartByUserId(Integer user_id){
        return cartDao.getAllByUserId(user_id);
    }
}
