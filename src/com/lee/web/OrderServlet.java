package com.lee.web;

import com.lee.pojo.Cart;
import com.lee.pojo.User;
import com.lee.service.OrderService;
import com.lee.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class OrderServlet extends BaseServlet{
    private OrderService orderService = new OrderServiceImpl();
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        User user = (User)req.getSession().getAttribute("user");
        if (user == null) {
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        Cart cart = (Cart)req.getSession().getAttribute("cart");
        String orderId = orderService.createOrder(cart, user.getId());
        req.getSession().setAttribute("orderId", orderId);
        resp.sendRedirect(basePath + "/pages/cart/checkout.jsp");
    }
}
