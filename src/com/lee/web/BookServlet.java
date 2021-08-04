package com.lee.web;


import com.lee.pojo.Book;
import com.lee.pojo.Page;
import com.lee.service.BookService;
import com.lee.service.impl.BookServiceImpl;
import com.lee.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet {
    int i = 0;
    private BookService bookService = new BookServiceImpl();

    //添加图书
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"),0);
        pageNo++;
        Book book = WebUtils.InsertToBean(req.getParameterMap(), new Book());
        bookService.addBook(book);
        resp.sendRedirect( basePath + "/manager/bookServlet?action=page&pageNo=" + pageNo);
    }
    //修改图书信息
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = WebUtils.InsertToBean(req.getParameterMap(),new Book());
        bookService.updateBook(book);
        resp.sendRedirect(basePath + "/manager/bookServlet?action=page&pageNo=" + req.getParameter("pageNo"));
    }
    //删除图书信息
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        bookService.deleteBookById(Integer.valueOf(id));
        resp.sendRedirect(basePath + "/manager/bookServlet?action=page&pageNo="+ req.getParameter("pageNo"));
    }
    //查询图书信息
    protected void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> list = bookService.queryBooks();
        req.setAttribute("books", list);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
    //根据id获取要修改的图书信息
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Book book = bookService.queryBookById(Integer.valueOf(id));
        req.setAttribute("book",book);
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }
    //获取分页信息 page
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        Page<Book> page = bookService.page(pageNo,pageSize);
        page.setUrl("manager/bookServlet?action=page");
        req.setAttribute("page",page);
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
}
