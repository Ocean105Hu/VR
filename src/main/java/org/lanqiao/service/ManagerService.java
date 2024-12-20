package org.lanqiao.service;

import org.lanqiao.pojo.Manager;
import org.lanqiao.pojo.PageBean;

import java.util.List;

public interface ManagerService {

    List<Manager> selectAll();

    void addManager(Manager manager);

    void updateManager(Manager manager);

    void deteleManagerByIds(int[] ids);

    void deteleManagerById(Integer id);

//    int selectManagerTotalCount();

    PageBean<Manager> selectByPage(int currentPage, int pageSize);

//    PageBean<Manager> selectByPageAndCondition(int currentPage,int pageSize,Manager manager);

}
