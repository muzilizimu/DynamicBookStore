package com.lee.dao.impl;

import com.lee.dao.OrderDao;
import com.lee.pojo.Order;

import java.sql.SQLException;


public class OrderDaoImpl extends BaseDAO implements OrderDao {

    @Override
    public int saveOrder(Order order) throws SQLException {
        String sql = "insert into t_order (`order_id`,`create_time`,`price`," +
                "`status`,`user_id`) values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreatTime(),order.getPrice(),order.getStatus(),order.getUser_id());

    }
}
