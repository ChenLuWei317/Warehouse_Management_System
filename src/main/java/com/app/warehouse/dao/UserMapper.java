package com.app.warehouse.dao;

import com.app.warehouse.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Select("SELECT u.*, a.人员档案管理, a.物料档案管理, a.进出仓管理, a.管理权限, a.统计打印 " +
            "FROM 软工2202_09_05_29人员表 u " +
            "LEFT JOIN 软工2202_09_05_29权限管理 a ON u.人员代码 = a.人员代码 " +
            "WHERE u.人员代码 = #{userName}")
    User selectUserWithAuthority(@Param("userName") String userName);

    User login(@Param("code") String code, @Param("password") String password);

    @Insert("INSERT INTO 软工2202_09_05_29人员表 (人员代码, 密码) VALUES (#{user.人员代码}, #{user.密码})")
    void register(@Param("user") User user);
}