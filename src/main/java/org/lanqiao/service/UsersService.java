package org.lanqiao.service;

import org.lanqiao.pojo.Users;

import java.util.List;

public interface UsersService {
    Users longin(String name, String pass);

    void register(String name, String pass, String realname);

    Users retrieve(String realname, String userName);

    void modifyPass(String userName, String newPass);


    /**
     * 查询所有
     * @return
     */
    List<Users> selectAll();

    void addUser(Users user);

    void updateUser(Users user);

    void deteleUserById(Integer userId);


    List<Users>selectById(Integer userId);

}
