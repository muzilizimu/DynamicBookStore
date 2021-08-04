package com.lee.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    private static DruidDataSource dataSource;
    private static ThreadLocal<Connection> threadConn = new ThreadLocal<Connection>();
    static {
        InputStream resourceAsStream = JdbcUtils.class.getClassLoader().getResourceAsStream("druid.properties");
        Properties pro = new Properties();
        try {
            pro.load(resourceAsStream);
            dataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(pro);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //  System.out.println(dataSource);
    }
    public static void main(String[] arg){
        try {
            System.out.println(getConnection());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        Connection conn = threadConn.get();
        if (conn == null){
            conn = dataSource.getConnection();
            threadConn.set(conn);
            conn.setAutoCommit(false);
        }

        return conn;
    }

    //提交并关闭连接
    public static void commitAndClose(){
        Connection conn = threadConn.get();
        if (conn != null){
            try {
                conn.commit();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        threadConn.remove();
    }
    //回滚并关闭连接
    public static void rollbackAndClose(){
        Connection conn = threadConn.get();
        if (conn != null){
            try {
                conn.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        threadConn.remove();
    }

}
