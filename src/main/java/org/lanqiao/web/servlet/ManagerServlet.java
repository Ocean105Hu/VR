package org.lanqiao.web.servlet;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson2.JSONObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.lanqiao.pojo.Manager;
import org.lanqiao.pojo.PageBean;
import org.lanqiao.service.ManagerService;
import org.lanqiao.service.impl.ManagerServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/Manager/*")
public class ManagerServlet extends BaseServlet {
    /**
     * 查询所有
     */
    private ManagerService managerService = new ManagerServiceImpl();

    public void selectAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Manager> managers = managerService.selectAll();
        System.out.println(managers);
//        Message message = new Message(0, "", managers.size(), managers);
//        String jsonString = JSON.toJSONString(message);
        String jsonString = JSON.toJSONString(managers);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    //
    public void addManager(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接受数据
        BufferedReader br = req.getReader();
        String params = br.readLine();//json字符串
        System.out.println(params);
        //2.转为Brand对象
        Manager manager = JSON.parseObject(params, Manager.class);
        System.out.println(manager);
        //3.调用service
        managerService.addManager(manager);
        //响应成功的标识
        resp.getWriter().write("success");
    }

    public void deteleByManagerIds(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接受数据
        BufferedReader br = req.getReader();
        String params = br.readLine();//json字符串
        //2.转为Brand对象
        int[] ids = JSON.parseObject(params, int[].class);
        //3.调用service
        managerService.deteleManagerByIds(ids);
        resp.getWriter().write("success");
    }

    public void updateManager(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String str = req.getReader().readLine();
        System.out.println(str);
        Manager manager = JSONObject.parseObject(str, Manager.class);

//        int id = (Integer) hashMap.get("managerId");
        managerService.updateManager(manager);
        resp.getWriter().write("success");

        /*
        String userName = req.getParameter("username");
        String realname = req.getParameter("realname");
        Users retrieve = usersService.retrieve(realname, userName);
        //编码
        resp.setCharacterEncoding("utf-8");
        PrintWriter write = resp.getWriter();
        if (retrieve != null) write.write("1");
        else write.write("0");
         */
    }

    public void deteleByManagerId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String line = req.getReader().readLine().replaceAll("[^0-9]", "");
        System.out.println(line);
        Integer managerId = Integer.valueOf(line);
        //调用service
        managerService.deteleManagerById(managerId);
        resp.getWriter().write("success");
    }

    public void selectManagerTotalCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        int totalCount = managerService.selectManagerTotalCount();
//        System.out.println(totalCount);
//        String jsonString = JSON.toJSONString(totalCount);
//        resp.setContentType("text/json;charset=utf-8");
//        resp.getWriter().write(jsonString);
    }

    /**
     * 分页查询
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String _currentPage = req.getParameter("currentPage");
        String _pageSize = req.getParameter("pageSize");
        int currentPage=Integer.parseInt(_currentPage);
        int pageSize=Integer.parseInt(_pageSize);
        PageBean<Manager> managerPageBean = managerService.selectByPage(currentPage, pageSize);
        String jsonString = JSON.toJSONString(managerPageBean);
        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

}


