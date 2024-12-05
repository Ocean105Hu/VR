package org.lanqiao.util;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;

public class PictureUtil {
    public static void respImg(String imgPath, HttpServletResponse resp) {
        try {
            //1.把图片数据读进来
            FileInputStream is = new FileInputStream(imgPath);

            ServletOutputStream os = resp.getOutputStream();

            //构建缓冲流，提高读写效率
            BufferedInputStream bis = new BufferedInputStream(is);
            BufferedOutputStream bos = new BufferedOutputStream(os);
            byte[] bytes = new byte[1000];

            //2.循环，边读边写
            while (bis.read(bytes) != -1) {
                bos.write(bytes);
            }

            //3.关闭资源
            bos.flush();
            bos.close();
            bis.close();
            is.close();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
