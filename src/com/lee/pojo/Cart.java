package com.lee.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 购物车对象
 */
public class Cart {
//    private Integer totalCount;
//    private BigDecimal price;
    //key 是商品id,value是商品对象
    private Map<Integer,CartItem> items = new LinkedHashMap<>();

    public Cart() {
    }



    public Integer getTotalCount() {
        Integer totalCount = 0;
        for (CartItem value : items.values()) {
            totalCount += value.getCount();
        }
        return totalCount;
    }

    public BigDecimal getPrice() {
        BigDecimal price = new BigDecimal(0);
        for (CartItem value : items.values()) {
            price = price.add(value.getTotalPrice());
        }
        return price;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", price=" + getPrice() +
                ", items=" + items +
                '}';
    }

    /**
     * 添加商品项
     *
     * @param cartItem
     */
    public void addItem(CartItem cartItem) {
        CartItem item = items.get(cartItem.getId());
        if( item == null) {
            items.put(cartItem.getId(),cartItem);
        } else {
            //增加商品数量，更新金额
            item.setCount(item.getCount() + 1);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    /**
     * 根据id删除商品项
     *
     * @param id
     */
    public void deleteItem(Integer id) {
        items.remove(id);
    }

    /**
     * 清空购物车
     */
    public void clearCart() {
        items.clear();
    }

    /**
     * 根据id变更商品数量
     *
     * @param id
     * @param count
     */
    public void updateItemCount(Integer id, Integer count) {
        CartItem item = items.get(id);
        if( item != null) {
            item.setCount(count);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }
}
