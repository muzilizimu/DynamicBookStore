package com.lee.test;

import com.lee.dao.impl.BookDaoImpl;
import com.lee.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {
    BookDaoImpl bdi = new BookDaoImpl();
    Book book = new Book(null, "权力的游戏", "martin", BigDecimal.valueOf(100), 99, 200, "static/img/FireAndBlood.png");
    Book book1 = new Book(22, "权力的游戏", "martin", BigDecimal.valueOf(100), 99, 200, "static/img/FireAndBlood.png");
    @Test
    public void addBook() {
        try {
            bdi.addBook(book);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void deleteBookById() {
        try {
            bdi.deleteBookById(21);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void updateBook() {
        try {
            bdi.updateBook(book1);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void queryBookById() {
        try {
            System.out.println(bdi.queryBookById(22));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Test
    public void queryBooks() {
        try {
            List<Book> list = bdi.queryBooks();
            for (Book book : list) {
                System.out.println(book);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    @Test
    public void queryForBookTotalCount() {
        try {
            System.out.println(bdi.queryForBookTotalCount());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void queryForBookPage() {
        System.out.println(bdi.queryForBookPage(0, 5));
    }

    @Test
    public void queryPriceTotalCount() {
        System.out.println(bdi.queryPriceTotalCount(0,100));
    }

    @Test
    public void queryForBookByPrice() {
        System.out.println(bdi.queryForBookByPrice(0, 4, 0, 100));
    }

}