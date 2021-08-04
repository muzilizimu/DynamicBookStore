package com.lee.service;

import com.lee.pojo.User;

public interface UserService {
    /**
     * 注册
     * @param user
     */
     void register(User user);

    /**
     * 登录用户
     * @param user
     * @return 返回空时表示登录失败，否则返回所登录的用户
     */
     User login(User user);

    /**
     * 检查用户名是否可用
     * @return true表示用户名存在，false表示用户名不存在
     */
     boolean userExist(String username);
}
