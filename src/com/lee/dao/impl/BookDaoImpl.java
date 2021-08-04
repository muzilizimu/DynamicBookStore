package com.lee.dao.impl;

import com.lee.dao.BookDao;
import com.lee.pojo.Book;

import java.sql.SQLException;
import java.util.List;

public class BookDaoImpl extends BaseDAO implements BookDao {
    @Override
    public int addBook(Book book) throws SQLException {
        String sql = "insert into t_book(`name`,`author`,`price`,`sales`,`stock`,`img_path`) values(?,?,?,?,?,?)";
        return update(sql, book.getName(),book.getAuthor(),book.getPrice(),
                book.getSales(),book.getStock(),book.getImg_Path());
    }

    @Override
    public int deleteBookById(Integer id) throws SQLException {
       String sql = "delete from t_book where id = ?";
       return update(sql, id);
    }

    @Override
    public int updateBook(Book book) throws SQLException {
        String sql = "update t_book set `name` = ?,`author` = ?,`price` = ?,`sales` = ?,`stock` = ?,`img_path` = ? where id = ?";
        return update(sql,book.getName(),book.getAuthor(),book.getPrice(),
                book.getSales(),book.getStock(),book.getImg_Path(),book.getId());
    }

    @Override
    public Book queryBookById(Integer id) throws SQLException {
        String sql = "select * from t_book where id = ?";
        return queryForOne(Book.class,sql,id);
    }

    @Override
    public List<Book> queryBooks() throws SQLException {
        String sql = "select * from t_book";
        return queryForList(Book.class, sql);
    }

    @Override
    public Integer queryForBookTotalCount() throws Exception {
        String sql = "select count(*) from t_book";
        Number num = (Number) queryForSingle(sql);
        return num.intValue();
    }

    @Override
    public List<Book> queryForBookPage(int begin, int pageSize) {
        String sql = "select * from t_book limit ?,?";
        try {
            return queryForList(Book.class, sql, begin, pageSize);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer queryPriceTotalCount(int min, int max) {
        String sql = "select count(*) from t_book where price between ? and ?";
        try {
            Number number = (Number) queryForSingle(sql, min,max);
            return number.intValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Book> queryForBookByPrice(int begin, int pageSize, int min, int max) {
        String sql = "select * from t_book where price between ? and ? order by price limit ?,?";
        try {
            return queryForList(Book.class , sql, min,max,begin,pageSize);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
