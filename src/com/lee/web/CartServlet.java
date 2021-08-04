package com.lee.web;

import com.google.gson.Gson;
import com.lee.pojo.Book;
import com.lee.pojo.Cart;
import com.lee.pojo.CartItem;
import com.lee.service.BookService;
import com.lee.service.impl.BookServiceImpl;
import com.lee.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CartServlet extends BaseServlet {
    BookService bookService = new BookServiceImpl();

    /**
     * 添加图书到购物车
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Book book = bookService.queryBookById(id);
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
//        req.getSession().setAttribute("lastBook",cartItem.getName());
        Map<String,Object> map = new HashMap<>();
        Gson gson = new Gson();
        map.put("lastBook",cartItem.getName());
        map.put("cartCount", cart.getTotalCount());
        resp.getWriter().write(gson.toJson(map));

    }

    /**
     * 删除购物车中的项目
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.deleteItem(id);
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 清空购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clearCart(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.clearCart();
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 修改商品数量
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateItemCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        cart.updateItemCount(id, count);
        System.out.println(cart);
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
