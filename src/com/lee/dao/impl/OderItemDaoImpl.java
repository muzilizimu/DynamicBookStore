package com.lee.dao.impl;

import com.lee.dao.OrderItemDao;
import com.lee.pojo.OrderItem;

import java.sql.SQLException;

public class OderItemDaoImpl extends BaseDAO implements OrderItemDao {

    @Override
    public int saveOrderItem(OrderItem orderItem) throws SQLException {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        return update(sql, orderItem.getName(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotalPrice(), orderItem.getOrder_id());
    }
}
