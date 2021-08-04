package com.lee.test;

import com.lee.utils.JdbcUtils;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class JdbcUtilsTest {
    @Test
    public void testJdbcUtils(){
        for (int i = 0; i < 100; i++) {
            try {
                Connection conn = JdbcUtils.getConnection();
                System.out.println(conn);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
