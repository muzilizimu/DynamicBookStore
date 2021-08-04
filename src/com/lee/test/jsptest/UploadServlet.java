package com.lee.test.jsptest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class UploadServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("upload success");

        //判断是否是分段内容
        if (ServletFileUpload.isMultipartContent(req)){
            FileItemFactory fif = new DiskFileItemFactory();
            ServletFileUpload sfu = new ServletFileUpload(fif);
            try {
                List<FileItem> list = sfu.parseRequest(req);
                for (FileItem fileItem : list) {
                    //判断是否是常规表单项
                    if (fileItem.isFormField()){
                        System.out.println(fileItem.getString("UTF-8"));
                    }else{
                        fileItem.write(new File("C:\\Users\\Administrator\\Desktop\\code_practice\\" + fileItem.getName()));
                        System.out.println("upload is sucess");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
