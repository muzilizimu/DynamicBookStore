package com.lee.test;

import com.lee.pojo.User;
import com.lee.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;
public class UserServiceTest {

    @Test
    public void register() {
        UserServiceImpl usi = new UserServiceImpl();
        usi.register(new User(null,"xiaowang","123456","xiaowang@qq.com"));
        usi.register(new User(null,"xiaolee","123456","xiaolee@qq.com"));
    }

    @Test
    public void login() {
        UserServiceImpl usi = new UserServiceImpl();
        System.out.println(usi.login(new User(null, "xiaolee", "123456", "xiaolee@qq.com")));
    }

    @Test
    public void userExist() {
        UserServiceImpl usi = new UserServiceImpl();
        System.out.println(usi.userExist("xiaolee"));
    }
}