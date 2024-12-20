package org.lanqiao.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.lanqiao.pojo.Manager;

import java.util.List;

public interface ManagerMapper {
    /*
    查询所有
    * */
//    @Select("select * from manager")
    List<Manager> selectAll();

    @Insert("insert into manager values (null,#{managerName},#{managerAccount},#{managerPassword})")
    void addManager(Manager manager);

    void updateManager(Manager manager);

    void deteleManagerByIds(int[] ids);

    void deteleManagerById(Integer id);



    //    @Select("select * from manager limit #{begin} , #{size}")
    List<Manager> selectByPage(@Param("begin")int begin, @Param("size")int size);

    @Select("select count(*) from manager")
    int selectTotalCount();


    //判断是否为管理员
    @Select("select * from manager where manager_account = #{account} and manager_password = #{password}")
    @ResultMap("managerResultMap")
    Manager selectManager(@Param("account") String account, @Param("password") String password);
}
