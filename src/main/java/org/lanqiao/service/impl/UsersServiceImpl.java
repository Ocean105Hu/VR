package org.lanqiao.service.impl;


import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.lanqiao.mapper.UsersMapper;
import org.lanqiao.pojo.Users;
import org.lanqiao.service.UsersService;
import org.lanqiao.util.SqlSessionFactoryUtil;

public class UsersServiceImpl implements UsersService {
    @Override
    //登录
    public Users longin(String name, String pass) {
        //获取对象
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UsersMapper usersMapper = sqlSession.getMapper(UsersMapper.class);
        //方法调用
        Users users = usersMapper.longin(name, pass);
        sqlSession.close();
        return users;
    }

    //注册
    @Override
    public void register(String name, String pass, String realname) {
        //获取对象
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UsersMapper usersMapper = sqlSession.getMapper(UsersMapper.class);

        //方法调用
        usersMapper.register(name,pass,realname);
        //提交事务
        sqlSession.commit();
        sqlSession.close();
    }

    //找回1
    @Override
    public Users retrieve(String realname, String userName) {
        //获取对象
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UsersMapper usersMapper = sqlSession.getMapper(UsersMapper.class);
        //方法调用
        Users retrieve = usersMapper.retrieve(realname, userName);
        sqlSession.close();
        return retrieve;
    }

    //找回2
    @Override
    public void modifyPass(String userName, String newPass) {
        //获取对象
        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UsersMapper usersMapper = sqlSession.getMapper(UsersMapper.class);
        //方法调用
        usersMapper.modifyPass(userName,newPass);
        sqlSession.commit();
        sqlSession.close();
    }

//    @Override
//    public Manager selectManager(String account, String password) {
//        //获取对象
//        System.out.println(account);
//        SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtil.getSqlSessionFactory();
//        System.out.println(1);
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//        ManagerMapper managerMapper = sqlSession.getMapper(ManagerMapper.class);
//        Manager manager = managerMapper.selectManager(account, password);
//        System.out.println(manager);
//        sqlSession.close();
//        return manager;
//    }

}
