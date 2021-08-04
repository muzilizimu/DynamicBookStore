package com.lee.service;

import com.lee.pojo.Book;
import com.lee.pojo.Page;

import java.math.BigDecimal;
import java.util.List;

public interface BookService {
    void addBook(Book book);

    void deleteBookById(Integer id);

    void updateBook(Book book);

    Book queryBookById(Integer id);

    List<Book> queryBooks();

    Page<Book> page(int pageNo, int pageSize) ;

    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}
