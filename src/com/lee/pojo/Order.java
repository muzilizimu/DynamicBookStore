package com.lee.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class Order {
    // 0 未发货 1 已发货 2 已完成
    public static final Integer TO_BE_DELIVERED = 0;
    public static final Integer DELIVERED = 1;
    public static final Integer COMPLETED = 2;
    private String order_id;
    private Date creatTime;
    private BigDecimal price;
    private Integer status=TO_BE_DELIVERED;
    private Integer user_id;

    public Order() {
    }

    public Order(String order_id, Date creatTime, BigDecimal price, Integer status, Integer user_id) {
        this.order_id = order_id;
        this.creatTime = creatTime;
        this.price = price;
        this.status = status;
        this.user_id = user_id;
    }

    public String getOrderId() {
        return order_id;
    }

    public void setOrderId(String orderId) {
        this.order_id = orderId;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "order_id='" + order_id + '\'' +
                ", creatTime=" + creatTime +
                ", price=" + price +
                ", status=" + status +
                ", user_id=" + user_id +
                '}';
    }
}
