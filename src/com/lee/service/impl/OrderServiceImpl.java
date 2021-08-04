package com.lee.service.impl;

import com.lee.dao.BookDao;
import com.lee.dao.OrderDao;
import com.lee.dao.OrderItemDao;
import com.lee.dao.impl.BookDaoImpl;
import com.lee.dao.impl.OderItemDaoImpl;
import com.lee.dao.impl.OrderDaoImpl;
import com.lee.pojo.*;
import com.lee.service.OrderService;

import java.sql.SQLException;
import java.util.Date;

public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public String createOrder(Cart cart, Integer userId) throws SQLException {
        String orderId = System.currentTimeMillis() + "" + userId;
        Order order = new Order(orderId, new Date(), cart.getPrice(), Order.TO_BE_DELIVERED,userId);
        orderDao.saveOrder(order);
        int i = 12 / 0;
        for (CartItem cartItem : cart.getItems().values()) {
            orderItemDao.saveOrderItem(new OrderItem(null, cartItem.getName(), cartItem.getCount(),
                    cartItem.getPrice(), cartItem.getTotalPrice(), order.getOrderId()));
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            bookDao.updateBook(book);
        }
        cart.clearCart();
        return order.getOrderId();
    }
}
