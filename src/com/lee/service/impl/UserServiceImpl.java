package com.lee.service.impl;

import com.lee.dao.UserDao;
import com.lee.dao.impl.UserDaoImpl;
import com.lee.pojo.User;
import com.lee.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public void register(User user) {
        userDao.saveUser(user);
    }

    /**
     *
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        return userDao.queryUserByNameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean userExist(String username) {
        if (userDao.queryUserByUsername(username) != null ){
            return true;
        }else {
            return false;
        }
    }
}
