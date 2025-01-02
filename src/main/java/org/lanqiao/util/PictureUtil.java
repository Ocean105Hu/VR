package org.lanqiao.util;


import java.io.*;

import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import static java.lang.System.out;

@MultipartConfig
public class PictureUtil {


    public static void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        //获取文件索引
        File folder = new File("E:\\A-My-Code\\Code\\项目\\大二上册年级项目\\VR\\src\\main\\webapp\\MainModule\\img\\vr_img");
        // 列出所有子文件夹
        File[] subFolders = folder.listFiles(File::isDirectory);
        out.println(Arrays.toString(subFolders));
        int index = 0;
        if (subFolders != null && subFolders.length > 0) {
            // 获取最后一个子文件夹的索引
            index = subFolders.length;
            out.println(index);
        }
        String filePath = "E:\\A-My-Code\\Code\\项目\\大二上册年级项目\\VR\\src\\main\\webapp\\MainModule\\img\\vr_img\\1-" + index;
        File file = new File(filePath);
        out.println(file.mkdir());
        Collection<Part> filePart = req.getParts();
        InputStream fileContent = null;
        for (Part filepart : filePart) {
            String fileName = filepart.getSubmittedFileName();

            // 设置存储路径
            String savePath = filePath + "/" + fileName;
            out.println(savePath);
            // 获取输入流
            fileContent = filepart.getInputStream();

            // 创建输出流
            try (OutputStream out = Files.newOutputStream(new File(savePath).toPath())) {

                // 将文件内容写入输出流
                byte[] bytes = new byte[1024];
                int len;
                while ((len = fileContent.read(bytes)) != -1) {
                    out.write(bytes, 0, len);
                }
            }
        }


        // 关闭流
        fileContent.close();
        out.close();

        res.getWriter().write("文件上传成功！");
    }
}
