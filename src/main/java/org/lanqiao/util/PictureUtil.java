package org.lanqiao.util;


import java.io.*;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;

@MultipartConfig
public class PictureUtil {


    public static void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String filePath = "E:/A-My-Code/Code/项目/大二上册年级项目/VR/src/main/webapp/MainModule/img/vr_img/11";
        File file = new File(filePath);
        System.out.println(file.mkdir());

        //获取文件索引
        Part filePart = req.getPart("image");
        String fileName = filePart.getSubmittedFileName();

        // 设置存储路径
        String savePath = filePath + "/" + fileName;
        System.out.println(savePath);
        // 获取输入流
        InputStream fileContent = filePart.getInputStream();

        // 创建输出流
        OutputStream out = Files.newOutputStream(new File(savePath).toPath());

        // 将文件内容写入输出流
        byte[] bytes = new byte[1024];
        int len;
        while ((len = fileContent.read(bytes)) != -1) {
            out.write(bytes, 0, len);
        }

        // 关闭流
        fileContent.close();
        out.close();

        res.getWriter().write("文件上传成功！");
    }
}
