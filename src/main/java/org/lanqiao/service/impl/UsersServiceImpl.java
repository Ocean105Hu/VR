package org.lanqiao.service.impl;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.lanqiao.mapper.UsersMapper;
import org.lanqiao.pojo.Users;
import org.lanqiao.service.UsersService;
import org.lanqiao.util.SqlSessionFactoryUtil;

import java.util.List;

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
        usersMapper.register(name, pass, realname);
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
        usersMapper.modifyPass(userName, newPass);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public List<Users> selectAll() {
        SqlSessionFactory factory = SqlSessionFactoryUtil.getSqlSessionFactory();
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取mapper
        UsersMapper usersMapper = sqlSession.getMapper(UsersMapper.class);
        //调用方法
        List<Users> users = usersMapper.sellect();
        sqlSession.close();
        return users;
    }


    @Override
    public void addUser(Users user) {
        SqlSessionFactory factory = SqlSessionFactoryUtil.getSqlSessionFactory();
        SqlSession sqlSession = factory.openSession();
        UsersMapper userMapper = sqlSession.getMapper(UsersMapper.class);
        userMapper.addUser(user);
        sqlSession.commit();
        sqlSession.close();
    }

    @Override
    public void deteleUserById(Integer userId) {
        SqlSessionFactory factory = SqlSessionFactoryUtil.getSqlSessionFactory();
        SqlSession sqlSession = factory.openSession();
        UsersMapper usersMapper = sqlSession.getMapper(UsersMapper.class);
        usersMapper.deteleUser(userId);
        sqlSession.commit();
        sqlSession.close();




    }

    @Override
    public void updateUser(Users user) {
        SqlSessionFactory factory = SqlSessionFactoryUtil.getSqlSessionFactory();
        SqlSession sqlSession = factory.openSession();
        UsersMapper userMapper = sqlSession.getMapper(UsersMapper.class);
        userMapper.updateUser(user);
        sqlSession.commit();
        sqlSession.close();
    }


}
