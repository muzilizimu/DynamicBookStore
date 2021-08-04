package com.lee.dao.impl;

import com.lee.dao.UserDao;
import com.lee.pojo.User;

import java.sql.SQLException;

public class UserDaoImpl extends BaseDAO implements UserDao {
    @Override
    public User queryUserByUsername(String username) {
        String sql = "select `id`,`username`,`password`,`email` from t_user where username = ?";
        try {
            return queryForOne(User.class, sql, username);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public User queryUserByNameAndPassword(String username, String password) {
        String sql = "select `id`,`username`,`password`,`email` from t_user where username = ? and password = ?";
        try {
            return queryForOne(User.class, sql, username,password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into t_user (`username`,`password`,`email`) values (?,?,?)";
        try {
            return update(sql, user.getUsername(),user.getPassword(),user.getEmail());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }
}
