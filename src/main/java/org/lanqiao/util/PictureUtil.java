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
import java.util.Comparator;

@MultipartConfig
public class PictureUtil {


    public static void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        // 获取上传的文件
        Part filePart = req.getPart("image");
        String fileName = filePart.getSubmittedFileName();
        // 设置存储路径
        String newFolderPath = req.getServletContext().getRealPath("/MainModule/img/vr_img/");
        File newFolder = new File(newFolderPath);
        if (!newFolder.exists()) {
            newFolder.mkdirs(); // 创建文件夹及其父目录
        }
        String savePath = newFolderPath + File.separator + fileName;
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
        res.setContentType("text/html;charset=utf-8");
        res.getWriter().write("文件上传成功！");
    }
}
