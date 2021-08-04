package com.lee.dao;

import com.lee.pojo.Order;

import java.sql.SQLException;

public interface OrderDao {
    /**
     * 保存订单
     * @param order
     * @return 返回影响的行数，-1表示保存失败
     */
    public int saveOrder(Order order) throws SQLException;
}
