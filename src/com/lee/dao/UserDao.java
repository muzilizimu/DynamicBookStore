package com.lee.dao;

import com.lee.pojo.User;

public interface UserDao {
    /**
     * 根据用户名查询用户
     * @return 返回null表示未找到该用户
     */
    public User queryUserByUsername(String username);

    /**
     *
     * @param username
     * @param password
     * @return 返回null表示未找到该用户
     */
    public User queryUserByNameAndPassword(String username,String password);

    /**
     * 保存成功用户信息
     * @param user
     * @return
     */
    public int saveUser(User user);
}
