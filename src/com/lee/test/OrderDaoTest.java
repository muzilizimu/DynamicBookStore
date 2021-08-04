package com.lee.test;

import com.lee.dao.OrderDao;
import com.lee.dao.impl.OrderDaoImpl;
import com.lee.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;

import static org.junit.Assert.*;

public class OrderDaoTest {
    OrderDao orderDao = new OrderDaoImpl();
    @Test
    public void saveOrder() throws SQLException {
        Order order = new Order("707560637", new Date(), new BigDecimal(100), Order.DELIVERED,7);
        orderDao.saveOrder(order);
    }
}