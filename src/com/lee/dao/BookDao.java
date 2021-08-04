package com.lee.dao;

import com.lee.pojo.Book;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public interface BookDao {
    /**
     * 添加图书
     * @param book
     * @return -1表示添加失败，否则表示影响的行数
     */
    int addBook(Book book) throws SQLException;

    /**
     * 根据id删除图书
     * @param id
     * @return
     */
    int deleteBookById(Integer id) throws SQLException;

    /**
     * 修改图书信息
     * @param book
     * @return -1表示失败，否则表示影响的行数
     */
    int updateBook(Book book) throws SQLException;

    /**
     * 根据id查询图书
     * @param id
     * @return 查询到的图书对象
     */
    Book queryBookById(Integer id) throws SQLException;

    /**
     * 查询多个图书
     * @return 返回Book对象list集合
     */
    List<Book> queryBooks() throws SQLException;

    /**
     * 查询总条目数
     * @return
     */
    Integer queryForBookTotalCount() throws Exception;

    /**
     * 查询当前页的数据
     * @param begin
     * @param pageSize
     * @return
     */
    List<Book> queryForBookPage(int begin, int pageSize) ;

    /**
     *
     * @param min
     * @param max
     * @return 返回min和max价格区间的书籍数量，null表示该区间没有数据
     */
    Integer queryPriceTotalCount(int min, int max);

    /**
     *
     * @param begin
     * @param pageSize
     * @param min
     * @param max
     * @return 返回第pageNo页价格位于min和max之间的书籍list集合，返回null表示未查询到
     */
    List<Book> queryForBookByPrice(int begin, int pageSize, int min, int max);
}
