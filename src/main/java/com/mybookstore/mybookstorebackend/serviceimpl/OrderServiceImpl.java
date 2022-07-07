package com.mybookstore.mybookstorebackend.serviceimpl;

import com.mybookstore.mybookstorebackend.constant.Constant;
import com.mybookstore.mybookstorebackend.dao.BookDao;
import com.mybookstore.mybookstorebackend.dao.CartDao;
import com.mybookstore.mybookstorebackend.dao.OrderDao;
import com.mybookstore.mybookstorebackend.dao.UserDao;
import com.mybookstore.mybookstorebackend.entity.Book;
import com.mybookstore.mybookstorebackend.entity.Order;
import com.mybookstore.mybookstorebackend.entity.OrderItem;
import com.mybookstore.mybookstorebackend.entity.User;
import com.mybookstore.mybookstorebackend.result.*;
import com.mybookstore.mybookstorebackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private CartDao cartDao;

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private BookDao bookDao;

    @Override
    public Integer createOrderFromUserCart(Integer user_id){

        User user = userDao.getById(user_id);
        if(user == null) return Constant.FAIL;

        // get all cart items that will turn into order
        List<CartResult> cart = cartDao.getAllByUserId(user_id);
        if(cart == null) return Constant.FAIL;

        // change to order item
        // calculate total num and price
        Integer tot_num = 0;
        BigDecimal tot_price = BigDecimal.valueOf(0);
        List<OrderItem> orderItem = new ArrayList<>();
        for(CartResult ci: cart){
            OrderItem oi = new OrderItem();
            Integer bookId = ci.getBook().getId();
            Integer num = ci.getNum();
            // decrease inventory
            if(!Objects.equals(bookDao.modifyInventory(bookId, (ci.getBook().getInventory() - num)), Constant.SUCCESS)) return Constant.FAIL;
            oi.setBook_id(bookId);
            oi.setNum(num);
            tot_num += num;
            BigDecimal price = ci.getBook().getPrice();
            oi.setPrice(price);
            tot_price = tot_price.add(price.multiply(BigDecimal.valueOf(num)));
            orderItem.add(oi);
        }

        // store order
        Integer orderId = orderDao.addOrder(tot_num, tot_price, user);

        // store order item
        if(!Objects.equals(orderDao.addOrderItems(orderId, orderItem), Constant.SUCCESS)){
            return Constant.FAIL;
        }

        // delete cart items turned into order
        cartDao.clearAllByUserId(user_id);

        return orderId;
    }

    @Override
    public Order getById(Integer id){
        return orderDao.getById(id);
    }

    @Override
    public List<Order> getAll(){
        return orderDao.getAll();
    }


    @Override
    public Integer deleteById(Integer id){
        return orderDao.deleteById(id);
    }


    @Override
    public Integer getOrderStatusById(Integer order_id){
        return orderDao.getOrderStatusById(order_id);
    }

    @Override
    public Integer changeOrderStatusById(Integer order_id, Integer status){
        return orderDao.changeOrderStatusById(order_id, status);
    }

    @Override
    public List<Order> getOrdersByUserId(Integer user_id){
        return orderDao.getOrdersByUserId(user_id);
    }

    @Override
    public List<OrderItemWithTotalResult> getAllOrdersWithItems(){return orderDao.getAllOrdersWithItems();}

    @Override
    public List<OrderItemWithTotalResult> getByUserIdAndStatus(Integer user_id, Integer status){
        return orderDao.getByUserIdAndStatus(user_id, status);
    }

    @Override
    public OrderItemWithTotalResult getItemsAndTotalById(Integer order_id){
        return orderDao.getItemsAndTotalById(order_id);
    }

    @Override
    public BookSalesResult getBookSalesResultByBookId(Integer id, Timestamp start, Timestamp end, Integer uid){
        Integer num = 0;
        BigDecimal total = BigDecimal.valueOf(0);
        List<OrderItem> orderItemList = orderDao.getOrderItemsByBookId(id);
        boolean checkTime = (start != null && end != null);
        for(OrderItem oi: orderItemList){
            Integer order_id = oi.getOrder_id();
            Timestamp orderTime = orderDao.getOrderTimestampById(order_id);
            if(!checkTime || (orderTime.after(start) && orderTime.before(end))){
                if(Objects.equals(uid, Constant.ALL) || Objects.equals(uid, orderDao.getOrderUidById(order_id))){
                    num += oi.getNum();
                    total = total.add(oi.getPrice().multiply(BigDecimal.valueOf(oi.getNum())));
                }
            }
        }
        return new BookSalesResult(bookDao.getById(id), num, total);
    }

    @Override
    public List<BookSalesResult> analysisBookSales(Timestamp start, Timestamp end){
        List<Book> books = bookDao.getAll();
        List<BookSalesResult> bookSalesResultList = new ArrayList<>();
        for(Book b:books){
            BookSalesResult bookSalesResult = getBookSalesResultByBookId(b.getId(), start, end, Constant.ALL);
            bookSalesResultList.add(bookSalesResult);
        }
        return bookSalesResultList;
    }

    @Override
    public List<UserConsumeResult> analysisUserConsume(Timestamp start, Timestamp end){
        List<User> users = userDao.getAll();
        List<UserConsumeResult> userConsumeResults = new ArrayList<>();
        for(User u: users){
            UserConsumeResult userConsumeResult = getUserConsumeResultByUserId(u.getUser_id(),start,end);
            userConsumeResults.add(userConsumeResult);
        }
        return userConsumeResults;
    }

    @Override
    public UserConsumeResult getUserConsumeResultByUserId(Integer user_id, Timestamp start, Timestamp end){
        Integer num = 0;
        BigDecimal total = BigDecimal.valueOf(0);
        List<Order> orderList = orderDao.getOrdersByUserId(user_id);
        boolean checkTime = (start != null && end != null);
        for(Order o: orderList){
            Timestamp orderTime = o.getTime();
            if(!checkTime || orderTime.after(start) && orderTime.before(end)){
                num += o.getNum();
                total = total.add(o.getTotal_price());
            }
        }
        return new UserConsumeResult(userDao.getById(user_id), num, total);
    }

    @Override
    public List<BookSalesResult> getUserBookConsumeDetailResultByUserId(Integer user_id, Timestamp start, Timestamp end){
        List<Book> books = bookDao.getAll();
        List<BookSalesResult> bookSalesResultList = new ArrayList<>();
        for(Book b:books){
            BookSalesResult bookSalesResult = getBookSalesResultByBookId(b.getId(), start, end, user_id);
            bookSalesResultList.add(bookSalesResult);
        }
        return bookSalesResultList;
    }

}
