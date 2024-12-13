package org.lanqiao.web.servlet;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.lanqiao.pojo.Users;
import org.lanqiao.service.UsersService;
import org.lanqiao.service.impl.UsersServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;


import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/users/*")
public class UsersServlet extends BaseServlet {
    private UsersService usersService = new UsersServiceImpl();

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
//        Manager manager = usersService.selectManager(name, pass);
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
            write.write("2");
            write.write(users.getUserId());
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
}
