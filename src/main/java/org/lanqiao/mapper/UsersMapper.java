package org.lanqiao.mapper;

import org.apache.ibatis.annotations.*;
import org.lanqiao.pojo.Users;



public interface UsersMapper {
    //登录
    @Select("select * from users where user_name = #{name} and user_pass = #{pass}")
    @ResultMap("usersResultMap")
    Users longin(@Param("name") String name, @Param("pass") String pass);
    //注册
    @Insert("insert into users(user_name,user_pass,user_realname) values (#{name},#{pass},#{realname})")
    void register(@Param("name") String name,@Param("pass") String pass,@Param("realname") String realname);
    //找回1
    @Select("select * from users where user_realname = #{realname} and user_name = #{userName}")
    @ResultMap("usersResultMap")
    Users retrieve(@Param("realname") String realname,@Param("userName") String userName);
    //找回2
    @Update("update users set user_pass = #{newPass} where user_name = #{userName} ")
    void modifyPass(@Param("userName") String userName,@Param("newPass") String newPass);

}
