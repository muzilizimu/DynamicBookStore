package com.lee.test;

import com.lee.pojo.Cart;
import com.lee.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class CartTest {

    Cart cart = new Cart();
    CartItem cartItem = new CartItem(1, "pig", 1, new BigDecimal(100), new BigDecimal(100));
    CartItem cartItem1 = new CartItem(1, "pig", 1, new BigDecimal(100), new BigDecimal(100));
    CartItem cartItem2 = new CartItem(2, "Bigpig", 2, new BigDecimal(100), new BigDecimal(200));
    @Test
    public void addItem() {
        cart.addItem(cartItem);
        cart.addItem(cartItem1);
        cart.addItem(cartItem2);
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        cart.addItem(cartItem);
        cart.addItem(cartItem1);
        cart.addItem(cartItem2);
        cart.deleteItem(2);
        System.out.println(cart);
    }

    @Test
    public void clearCart() {
        cart.addItem(cartItem);
        cart.addItem(cartItem1);
        cart.addItem(cartItem2);
        cart.clearCart();
        System.out.println(cart);
    }

    @Test
    public void updateItemCount() {
        cart.addItem(cartItem);
        cart.addItem(cartItem1);
        cart.addItem(cartItem2);
        cart.deleteItem(2);
        cart.updateItemCount(2, 5);
        System.out.println(cart);
    }
}