package com.lee.dao.impl;

import com.lee.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import sun.plugin2.main.server.ResultHandler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDAO {
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * update 用于insert、update、delete语句
     *
     * @return 返回-1则表示失败，否则表示影响的行数
     */
    public int update(String sql, Object... args) throws SQLException{
        Connection conn = JdbcUtils.getConnection();
        int t = queryRunner.update(conn, sql, args);
        return t;
    }

    /**
     *
     * @param type
     * @param sql
     * @param args
     * @param <T> 类型
     * @return 返回null则表示未查询到，否则返回T对象
     */
    public <T> T queryForOne(Class<T> type, String sql, Object... args) throws SQLException{
        Connection conn = JdbcUtils.getConnection();
        T t = null;
        t = queryRunner.query(conn,sql, new BeanHandler<T>(type), args);
        if (t == null){
            return null;
        }else {
            return t;
        }
    }

    /**
     *
     * @param type
     * @param sql
     * @param args
     * @param <T>
     * @return  为查询到则返回null，否则返回list<T>
     * @throws SQLException
     */
    public <T> List<T> queryForList(Class<T> type, String sql, Object... args) throws SQLException{
            Connection conn = JdbcUtils.getConnection();
            List<T> list = new ArrayList<T>();
            list =  queryRunner.query(conn,sql, new BeanListHandler<T>(type), args);
            if (list != null){
                return list;
            }else {
                return null;
            }
    }

    /**
     *
     * @param sql
     * @param args
     * @return  返回单个值的查询结果
     * @throws Exception
     */
    public Object queryForSingle(String sql,Object... args) throws Exception {
            Connection conn = JdbcUtils.getConnection();
            Object object = null;
            object =  queryRunner.query(conn, sql, new ScalarHandler(),args);
            return object;
    }
}
