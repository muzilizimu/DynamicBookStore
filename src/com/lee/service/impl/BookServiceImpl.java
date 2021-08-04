package com.lee.service.impl;

import com.lee.dao.BookDao;
import com.lee.dao.impl.BookDaoImpl;
import com.lee.pojo.Book;
import com.lee.pojo.Page;
import com.lee.service.BookService;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public void addBook(Book book) {
        try {
            bookDao.addBook(book);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBookById(Integer id) {
        try {
            bookDao.deleteBookById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void updateBook(Book book) {
        try {
            bookDao.updateBook(book);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Book queryBookById(Integer id) {
        try {
            return bookDao.queryBookById(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Book> queryBooks() {
        try {
            return bookDao.queryBooks();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<>();
        //设置page对象的各属性

        page.setPageSize(pageSize);
        //获取总得条目数
        Integer pageTotalCount = null;
        try {
            pageTotalCount = bookDao.queryForBookTotalCount();
        } catch (Exception e) {
            e.printStackTrace();
        }
        page.setPageTotalCount(pageTotalCount);
        //获取总页数
        Integer pageTotal = pageTotalCount % pageSize > 0 ? pageTotalCount / pageSize + 1 : pageTotalCount / pageSize;
        page.setPageTotal(pageTotal);
        //数据边界的有效检查
        if(pageNo < 1 ){
            pageNo = 1;
        }
        if (pageNo > pageTotal){
            pageNo = pageTotal;
        }
        page.setPageNo(pageNo);
        //获取当前页的items
        int begin = (pageNo - 1) * pageSize;
        List<Book> list = bookDao.queryForBookPage(begin,pageSize);
        page.setItems(list);
        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page<>();
        page.setPageNo(pageNo);
        page.setPageSize(pageSize);
        //求总价格区间的总条目数
        Integer pagePriceTotalCount = bookDao.queryPriceTotalCount(min,max);
        page.setPageTotalCount(pagePriceTotalCount);
        //获取总页数
        Integer pageTotal = pagePriceTotalCount % pageSize > 0 ? pagePriceTotalCount / pageSize + 1 : pagePriceTotalCount / pageSize;
        page.setPageTotal(pageTotal);
        //获取当前页数据
        int begin = (pageNo - 1) * pageSize;
        List<Book> list = bookDao.queryForBookByPrice(begin,pageSize,min,max);
        page.setItems(list);
        return page;
    }
}
