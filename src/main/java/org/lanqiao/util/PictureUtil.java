package org.lanqiao.util;


import java.io.*;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@MultipartConfig
public class PictureUtil {


    public static void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取上传的文件
        Part filePart = request.getPart("image");
        String fileName = filePart.getSubmittedFileName();

        // 设置存储路径
        String savePath = "D:/1/" + fileName;

        // 获取输入流
        InputStream fileContent = filePart.getInputStream();

        // 创建输出流
        OutputStream out = Files.newOutputStream(new File(savePath).toPath());

        // 将文件内容写入输出流
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = fileContent.read(buffer)) != -1) {
            out.write(buffer, 0, bytesRead);
        }

        // 关闭流
        fileContent.close();
        out.close();

        response.getWriter().write("文件上传成功！");
    }
}
