package com.bin.blog.service;

import com.bin.blog.entity.AdminUser;

/**
 * @author blog后台
 *
 */
public interface AdminUserService {
//登录
AdminUser login (String name ,String password);
//根据id得到用户信息
AdminUser getUserId(Integer id);
//更改用户名
Boolean updateName(Integer id,String name,String NickName);
//更改密码
Boolean updatePassWord(Integer id,String password,String newPassword);
}