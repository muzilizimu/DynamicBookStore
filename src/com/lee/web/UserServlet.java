package com.lee.web;

import com.google.gson.Gson;
import com.lee.pojo.User;
import com.lee.service.UserService;
import com.lee.service.impl.UserServiceImpl;
import com.lee.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;

public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();


    /**
     * 登录用户
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //login
        User user = userService.login(WebUtils.InsertToBean(req.getParameterMap(), new User()));
        if (user != null) {
            //登录成功
            System.out.println(req.getSession().getMaxInactiveInterval());
            req.getSession().setAttribute("user", user);
            System.out.println(req.getSession().getId());
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        } else {
            //登录失败
            req.setAttribute("msg", "登录失败，用户名或密码错误");
            req.setAttribute("username", user.getUsername());
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
        }
    }

    /**
     * 注销用户
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath());
    }

    /**
     * 注册用户
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        //获取验证码并删除session中的验证码
        String token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);
        User user = WebUtils.InsertToBean(req.getParameterMap(), new User());
        if (token != null && token.equalsIgnoreCase(code)) {
            if (userService.userExist(user.getUsername())) {
                //用户名不可用
                req.setAttribute("msg", "用户名已存在");
                req.setAttribute("username", user.getUsername());
                req.setAttribute("email", user.getEmail());
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            } else {
                //用户名可用
                userService.register(user);
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("msg", "验证码错误");
            req.setAttribute("username", user.getUsername());
            req.setAttribute("email", user.getEmail());
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }

    protected void userExist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        boolean b = userService.userExist(username);
        Map<String,Object> map = new HashMap<>();
        map.put("b",b);
        Gson gson = new Gson();
        String s = gson.toJson(map);
        resp.getWriter().write(s);
    }
}


