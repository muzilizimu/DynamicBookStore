package com.lee.dao;

import com.lee.pojo.OrderItem;

import java.sql.SQLException;

public interface OrderItemDao {
    /**
     * 保存订单项
     * @param orderItem
     * @return 返回影响的行数，-1表示保存失败
     */
    public int saveOrderItem(OrderItem orderItem) throws SQLException;
}
