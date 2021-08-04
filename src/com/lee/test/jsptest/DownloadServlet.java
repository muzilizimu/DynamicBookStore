package com.lee.test.jsptest;


import org.apache.commons.io.IOUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DownloadServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("conn");
        String downloadFileName = "test.jpg";
        //读取要下载的内容
        ServletContext servletContext = getServletContext();
        //获取下载的文件类型
        String type = servletContext.getMimeType("/testFile/" + downloadFileName);
        System.out.println(type);
        //回传前告知客户端文件类型
        resp.setContentType(type);
        //告知浏览器下载及文件名
        resp.setHeader("Content-Disposition","attachment;filename=" + downloadFileName);
        //  “/” 映射到当前工程的web/下
        InputStream resourceAsStream = servletContext.getResourceAsStream("/testFile/" + downloadFileName);
        OutputStream outputStream = resp.getOutputStream();
        //将输入流复制给输出流
        IOUtils.copy(resourceAsStream, outputStream);


    }
}


