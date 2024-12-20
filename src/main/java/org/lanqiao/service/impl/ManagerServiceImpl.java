package org.lanqiao.service.impl;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.lanqiao.mapper.ManagerMapper;
import org.lanqiao.pojo.Manager;
import org.lanqiao.pojo.PageBean;
import org.lanqiao.service.ManagerService;
import org.lanqiao.util.SqlSessionFactoryUtil;

import java.util.List;

public class ManagerServiceImpl implements ManagerService {
    //获取对象
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();

    @Override
    public List<Manager> selectAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ManagerMapper managerMapper = sqlSession.getMapper(ManagerMapper.class);
        List<Manager> managers = managerMapper.selectAll();
        return managers;
    }

    @Override
    public void addManager(Manager manager) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ManagerMapper managerMapper = sqlSession.getMapper(ManagerMapper.class);
        managerMapper.addManager(manager);
        sqlSession.commit();
        sqlSession.close();
///
    }

    @Override
    public void updateManager(Manager manager) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ManagerMapper managerMapper = sqlSession.getMapper(ManagerMapper.class);
        managerMapper.updateManager(manager);
        sqlSession.commit();
        sqlSession.close();
    }

/*    @Override
    public void updateManager(String managerName, String managerPassword, int managerId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ManagerMapper managerMapper = sqlSession.getMapper(ManagerMapper.class);
//        managerMapper.updateManager(managerName,managerPassword,managerId);
        sqlSession.commit();
        sqlSession.close();
    }*/

    @Override
    public void deteleManagerByIds(int[] ids) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ManagerMapper managerMapper = sqlSession.getMapper(ManagerMapper.class);
        managerMapper.deteleManagerByIds(ids);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deteleManagerById(Integer managerId) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ManagerMapper managerMapper = sqlSession.getMapper(ManagerMapper.class);
        managerMapper.deteleManagerById(managerId);
        sqlSession.commit();
        sqlSession.close();
    }

  /*  @Override
    public int selectManagerTotalCount() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ManagerMapper managerMapper = sqlSession.getMapper(ManagerMapper.class);
        int count = managerMapper.selectManagerTotalCount();
        return count;
    }*/


    public PageBean<Manager> selectByPage(int currentPage, int pageSize) {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        ManagerMapper managerMapper = sqlSession.getMapper(ManagerMapper.class);
        int begin = (currentPage - 1) * pageSize;
        int size = pageSize;
        System.out.println(begin + "," + size);
        List<Manager> rows = managerMapper.selectByPage(begin, size); //分页的数据
        System.out.println(rows);
        int totalCount = managerMapper.selectTotalCount();//总记录数
        PageBean<Manager> PageBean = new PageBean<>();//分页的条数
        //把这两个值封装给pageBean
        PageBean.setRows(rows);
        PageBean.setTotalCount(totalCount);
        sqlSession.close();
        return PageBean;
    }


//    public PageBean<Manager> selectByPageAndCondition(int currentPage, int pageSize, Manager manager) {
//        //2.获取SqlSession对象
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        //3.获取BrandMapper
//        ManagerMapper mapper = sqlSession.getMapper(ManagerMapper.class);
//
//        //4.定义开始索引
//        int begin = (currentPage - 1) * pageSize;   //currentPages数据-> 由页面提供     pageSize数据-> 由页面提供
//        //计算查询条目数
//        int size = pageSize;
//
//        //处理brand的条件，模糊表达式
//        String managerName = manager.getManagerName();
//        if (managerName != null && managerName.length() > 0) {
//            manager.setManagerName("%" + managerName + "%");
//        }
//
//        String managerAccout = manager.getManagerAccout();
//        if (managerAccout != null && managerAccout.length() > 0) {
//            manager.setManagerAccout("%" + managerAccout + "%");
//        }
//
////        //当前页数数据
////        List<Manager> rows = mapper.selectByPageAndCondition(begin, size, manager); //用页面传过来的数据查询对应的数据
////
////        //6.查询总记录数
////        int totalCount = mapper.selectTotalCountByCondition(manager);   //查询总共的条数，用到聚合函数
//
////        System.out.println(totalCount);
////
////        //封装PageBean对象
////        PageBean<Manager> pageBean = new PageBean<>();  //最后将数据封装到一个PageBean中 ，在返回给页面
////        pageBean.setRows(rows);
////        pageBean.setTotalCount(totalCount);
//        //8.释放资源
//        sqlSession.close();
//        return pageBean;
//    }

}
