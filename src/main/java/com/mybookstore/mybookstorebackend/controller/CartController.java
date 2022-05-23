package com.mybookstore.mybookstorebackend.controller;

import com.mybookstore.mybookstorebackend.result.CartResult;
import com.mybookstore.mybookstorebackend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/cart", method = RequestMethod.POST)
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping(path = "/add")
    public @ResponseBody Integer AddCartItem(@RequestParam Integer user_id, @RequestParam Integer book_id, @RequestParam Integer num){
        return cartService.addCartItem(user_id, book_id, num);
    }

    @PostMapping(path = "/delete")
    public @ResponseBody Integer DeleteCartItem(@RequestParam Integer user_id, @RequestParam Integer book_id){
        return cartService.deleteCartItem(user_id, book_id);
    }

    @PostMapping(path = "/modifyNum")
    public @ResponseBody Integer modifyCartItemNum(@RequestParam Integer user_id, @RequestParam Integer book_id, @RequestParam Integer num){
        return cartService.modifyCartItemNum(user_id, book_id, num);
    }

    @PostMapping(path ="/clearAllBooksInUserCart")
    public @ResponseBody Integer clearAllBooksInUserCart(@RequestParam Integer user_id) {
        return cartService.clearAllInCartByUserId(user_id);
    }

    @PostMapping(path ="/getAllBooksInUserCart")
    public @ResponseBody List<CartResult> getAllBooksInUserCart(@RequestParam Integer user_id) {
        return cartService.getAllInCartByUserId(user_id);
    }


}
