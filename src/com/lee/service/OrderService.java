package com.lee.service;

import com.lee.pojo.Cart;

import java.sql.SQLException;

public interface OrderService {
    String createOrder(Cart cart, Integer userId) throws SQLException;
}
