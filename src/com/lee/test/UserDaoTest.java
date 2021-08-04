package com.lee.test;

import com.lee.dao.UserDao;
import com.lee.dao.impl.UserDaoImpl;
import com.lee.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {

    @Test
    public void queryUserByUsername() {
        UserDao userDao = new UserDaoImpl();
        System.out.println(userDao.queryUserByUsername("lee"));
    }

    @Test
    public void queryUserByNameAndPassword() {
        UserDao userDao = new UserDaoImpl();
        System.out.println(userDao.queryUserByNameAndPassword("admin","admin"));
    }

    @Test
    public void saveUser() {
        UserDao userDao = new UserDaoImpl();
        System.out.println(userDao.saveUser(new User(2,"leejingwei","lee5413100","lee@qq.com")));
    }
}