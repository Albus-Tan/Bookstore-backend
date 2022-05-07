package com.mybookstore.mybookstorebackend.controller;

import com.mybookstore.mybookstorebackend.constant.Constant;
import com.mybookstore.mybookstorebackend.result.CartResult;
import com.mybookstore.mybookstorebackend.entity.CartItem;
import com.mybookstore.mybookstorebackend.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/cart", method = RequestMethod.POST)
public class CartController {
    @Autowired
    private CartItemRepository cartItemRepository;

    @PostMapping(path = "/add")
    public @ResponseBody Integer AddCartItem(@RequestParam Integer user_id, @RequestParam Integer book_id, @RequestParam Integer num){

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

    @PostMapping(path = "/delete")
        public @ResponseBody Integer DeleteCartItem(@RequestParam Integer user_id, @RequestParam Integer book_id){
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

    @PostMapping(path = "/modifyNum")
    public @ResponseBody Integer modifyCartItemNum(@RequestParam Integer user_id, @RequestParam Integer book_id, @RequestParam Integer num){
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

    @PostMapping(path ="/clearAllBooksInUserCart")
    public @ResponseBody Integer clearAllBooksInUserCart(@RequestParam Integer user_id) {
        List<CartItem> cartItemList = cartItemRepository.getByUserId(user_id);
        cartItemRepository.deleteAll(cartItemList);
        return Constant.SUCCESS;
    }

    @PostMapping(path ="/getAllBooksInUserCart")
    public @ResponseBody List<CartResult> getAllBooksInUserCart(@RequestParam Integer user_id) {
        List<CartItem> cartItemList = cartItemRepository.getByUserId(user_id);
        List<CartResult> res = new ArrayList<>();
        for(CartItem ci: cartItemList){
            CartResult r = new CartResult(ci.getBook(), ci.getNum());
            res.add(r);
        }
        return res;
    }


}
