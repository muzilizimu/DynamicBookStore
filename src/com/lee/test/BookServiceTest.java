package com.lee.test;

import com.lee.pojo.Book;
import com.lee.pojo.Page;
import com.lee.service.BookService;
import com.lee.service.impl.BookServiceImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BookServiceTest {
    BookService bs = new BookServiceImpl();
    @Test
    public void page() {
        System.out.println(bs.page(2, 4));
    }
    @Test
    public void pageByPrice() {
        System.out.println(bs.pageByPrice(1, 4, 0, 50));
    }
}