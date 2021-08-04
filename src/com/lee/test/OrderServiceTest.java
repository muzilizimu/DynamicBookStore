package com.lee.test;

import com.lee.pojo.Cart;
import com.lee.pojo.CartItem;
import com.lee.service.OrderService;
import com.lee.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class OrderServiceTest {
    OrderService orderService = new OrderServiceImpl();
    @Test
    public void createOrder() throws SQLException {
        Cart cart = new Cart();
        CartItem cartItem = new CartItem(1, "pig", 1, new BigDecimal(100), new BigDecimal(100));
        CartItem cartItem1 = new CartItem(1, "pig", 1, new BigDecimal(100), new BigDecimal(100));
        CartItem cartItem2 = new CartItem(2, "Bigpig", 2, new BigDecimal(100), new BigDecimal(200));
        cart.addItem(cartItem);
        cart.addItem(cartItem1);
        cart.addItem(cartItem2);
        orderService.createOrder(cart, 1);
    }
}