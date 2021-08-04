package com.lee.test;

import com.lee.dao.OrderItemDao;
import com.lee.dao.impl.OderItemDaoImpl;
import com.lee.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class OrderItemDaoTest {

    @Test
    public void saveOrderItem() throws SQLException {
        OrderItemDao orderItemDao = new OderItemDaoImpl();
        orderItemDao.saveOrderItem(new OrderItem(1, "FireAndBlood", 1, new BigDecimal(100), new BigDecimal(100), "707560637"));
    }
}