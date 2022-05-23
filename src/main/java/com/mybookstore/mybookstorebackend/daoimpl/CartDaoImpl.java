package com.mybookstore.mybookstorebackend.daoimpl;

import com.mybookstore.mybookstorebackend.constant.Constant;
import com.mybookstore.mybookstorebackend.dao.CartDao;
import com.mybookstore.mybookstorebackend.entity.CartItem;
import com.mybookstore.mybookstorebackend.repository.CartItemRepository;
import com.mybookstore.mybookstorebackend.result.CartResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CartDaoImpl implements CartDao {

    @Autowired
    CartItemRepository cartItemRepository;

    @Override
    public Integer addItem(Integer user_id, Integer book_id, Integer num){
        // check if is added already
        CartItem ci = cartItemRepository.getByUserIdAndBookId(user_id, book_id);
        if(ci == null){
            CartItem cartItem = new CartItem();
            cartItem.setBook_id(book_id);
            cartItem.setUser_id(user_id);
            cartItem.setNum(num);
            cartItemRepository.save(cartItem);
        } else {
            // added already
            ci.setNum(num+ci.getNum());
            cartItemRepository.save(ci);
        }
        return (ci == null) ? num : ci.getNum();
    }

    @Override
    public Integer deleteItem(Integer user_id, Integer book_id){
        // check if exist
        CartItem ci = cartItemRepository.getByUserIdAndBookId(user_id, book_id);
        if(ci == null){
            // not exist
            return Constant.FAIL;
        } else {
            // exist
            cartItemRepository.delete(ci);
            return Constant.SUCCESS;
        }
    }

    @Override
    public Integer modifyItemNum(Integer user_id, Integer book_id, Integer num){
        // check if exist
        CartItem ci = cartItemRepository.getByUserIdAndBookId(user_id, book_id);
        if(ci == null){
            // not exist
            return Constant.NO_SUCH_CART_ITEM;
        } else {
            // exist
            ci.setNum(num);
            cartItemRepository.save(ci);
            return ci.getNum();
        }
    }

    @Override
    public Integer clearAllByUserId(Integer user_id){
        List<CartItem> cartItemList = cartItemRepository.getByUserId(user_id);
        cartItemRepository.deleteAll(cartItemList);
        return Constant.SUCCESS;
    }

    @Override
    public List<CartResult> getAllByUserId(Integer user_id){
        List<CartItem> cartItemList = cartItemRepository.getByUserId(user_id);
        List<CartResult> res = new ArrayList<>();
        for(CartItem ci: cartItemList){
            CartResult r = new CartResult(ci.getBook(), ci.getNum());
            res.add(r);
        }
        return res;
    }
}
