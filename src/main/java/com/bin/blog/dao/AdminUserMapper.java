package com.bin.blog.dao;

import org.apache.ibatis.annotations.Param;

import com.bin.blog.entity.AdminUser;
/**
 * @author 管理员Dao
 *
 */
public interface AdminUserMapper {
    //获取用户信息
    AdminUser selectByPrimaryKey(Integer adminUserId);
    //修改用户信息
    int updateByPrimaryKeySelective(AdminUser record);
    //登录
    //@Param 对应xml的值 比如 #{userName}
    AdminUser login(@Param("userName")String name ,@Param("passWord")String password);
    
  
}