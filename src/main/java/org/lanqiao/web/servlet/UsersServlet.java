package org.lanqiao.web.servlet;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.lanqiao.pojo.Users;
import org.lanqiao.service.UsersService;
import org.lanqiao.service.impl.UsersServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import org.lanqiao.util.PictureUtil;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@MultipartConfig
@WebServlet("/users/*")
public class UsersServlet extends BaseServlet {
    private final UsersService usersService = new UsersServiceImpl();

    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Users> users = usersService.selectAll();
        String jsonString = JSON.toJSONString(users);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    public void selectById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String line = req.getReader().readLine().replaceAll("[^0-9]", "");
        System.out.println(line);
        Integer userId = Integer.valueOf(line);
        //调用service
        List<Users> users = usersService.selectById(userId);
        String jsonString = JSON.toJSONString(users);
        System.out.println(jsonString);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(jsonString);  // 将 JSON 响应发送到前端
        out.flush();
    }

    public void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接受数据
        BufferedReader br = req.getReader();
        String params = br.readLine();//json字符串
        System.out.println(params);
        //2.转为User对象
        Users user = JSON.parseObject(params, Users.class);
        System.out.println(user);
        //3.调用service
        usersService.addUser(user);
        //响应成功的标识
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.print("1");  // 将 JSON 响应发送到前端
        out.flush();
    }

    public void deteleByUserId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String line = req.getReader().readLine().replaceAll("[^0-9]", "");
        System.out.println(line);
        Integer userId = Integer.valueOf(line);
        //调用service
        usersService.deteleUserById(userId);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.print("1");  // 将 JSON 响应发送到前端
        out.flush();
    }

    public void deleteByIds(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接受数据
        BufferedReader br = req.getReader();
        System.out.println();
        String params = br.readLine();//json字符串
        String regex = "\\[(.*?)]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(params);
        while (matcher.find()) params = matcher.group(1);
        String[] arrs = params.split(",");
        System.out.println(Arrays.toString(arrs));
        int[] ids = new int[arrs.length];
        for (int i = 0; i < ids.length; i++) ids[i] = Integer.parseInt(arrs[i]);

        System.out.println("1");
        System.out.println(Arrays.toString(ids));

        //3.调用service
        usersService.deleteByIds(ids);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.print("1");  // 将 JSON 响应发送到前端
        out.flush();
    }

    public void updateUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接受数据
        BufferedReader br = req.getReader();
        String params = br.readLine();//json字符串

        System.out.println(params);
        //2.转为User对象
        Users user = JSON.parseObject(params, Users.class);
        System.out.println(user);
        //3.调用service
        usersService.updateUser(user);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.print("1");  // 将 JSON 响应发送到前端
        out.flush();
    }

    //登录
    public void longin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求
        String name = req.getParameter("username");
        String pass = req.getParameter("password");

//        String s = req.getReader().readLine();
//        System.out.println(s);
//        HashMap hashMap = JSONObject.parseObject(s, HashMap.class);
//        System.out.println(hashMap);
//        String name = (String) hashMap.get("username");
//        String pass = (String) hashMap.get("password");
//        System.out.println(name);
//        System.out.println(pass);

        String loginCheckLabel = req.getParameter("login__check-label");
        //调用方法
        Users users = usersService.longin(name, pass);

        //编码
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter write = resp.getWriter();
        //写回数据
//        if (manager != null) {
//            if ("1".equals(loginCheckLabel)) {
//                //cookie存储
//                Cookie c_name = new Cookie("username", name);
//                Cookie c_pass = new Cookie("password", pass);
//                //存活时间
//                c_name.setMaxAge(60 * 60 * 24);
//                c_pass.setMaxAge(60 * 60 * 24);
//                //发送
//                resp.addCookie(c_name);
//                resp.addCookie(c_pass);
//            }
//            //管理员登录
//            write.write("1");
//            //用户登录
//        } else
        if (users != null) {
            if ("1".equals(loginCheckLabel)) {
                //cookie存储
                Cookie c_name = new Cookie("username", name);
                Cookie c_pass = new Cookie("password", pass);
                //存活时间
                c_name.setMaxAge(60 * 60 * 24);
                c_pass.setMaxAge(60 * 60 * 24);
                //发送
                resp.addCookie(c_name);
                resp.addCookie(c_pass);
            }
            //用户登录
            String id = String.valueOf(users.getUserId());
            write.write(id);
        } else write.write("0");
        //登录失败
    }

    //注册
    public void register(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String name = req.getParameter("username");
        String pass = req.getParameter("password");
        String realname = req.getParameter("realname");
        usersService.register(name, pass, realname);
        PrintWriter write = res.getWriter();
        write.write("1");
    }

    //找回密码1
    public void retrieve(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        String realname = req.getParameter("realname");
        Users retrieve = usersService.retrieve(realname, userName);
        //编码
        resp.setCharacterEncoding("utf-8");
        PrintWriter write = resp.getWriter();
        if (retrieve != null) write.write("1");
        else write.write("0");
    }

    //找回密码2
    public void modifyPass(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        String newPass = req.getParameter("password");
        usersService.modifyPass(userName, newPass);
        resp.setCharacterEncoding("utf-8");
        PrintWriter write = resp.getWriter();
        write.write("1");
    }

    //上传图片
    public void picture(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取上传的文件
        PictureUtil.doPost(request, response);
    }
}
