package org.lanqiao.service;

import org.lanqiao.pojo.Users;

public interface UsersService {
    Users longin(String name,String pass);
    void register(String name,String pass,String realname);
    Users retrieve(String realname,String userName);
    void modifyPass(String userName, String newPass);
    Manager selectManager(String account,String password);
}