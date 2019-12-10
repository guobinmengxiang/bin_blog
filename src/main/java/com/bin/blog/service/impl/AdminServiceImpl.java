package com.bin.blog.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bin.blog.dao.AdminUserMapper;
import com.bin.blog.entity.AdminUser;
import com.bin.blog.service.AdminUserService;
import com.bin.blog.util.MD5Util;
/**
 * @author Admin 接口实现类
 *
 */
@Service
public class AdminServiceImpl implements AdminUserService {
	//把一个bean注入到当前的类中,此类中可以使用 adminUserMapper;
	 @Resource
      private AdminUserMapper adminUserMapper;
	@Override
	public AdminUser login(String name, String password) {
		//MD5 加密
		String passWord=MD5Util.MD5Encode(password, "UTF-8");
		return adminUserMapper.login(name, passWord);
	}
	@Override
	public AdminUser getUserId(Integer id) {
		// TODO Auto-generated method stub
		return adminUserMapper.selectByPrimaryKey(id);
	}
	@Override
	public Boolean updateName(Integer id, String name, String NickName) {
		AdminUser userName=adminUserMapper.selectByPrimaryKey(id);
	
		if(userName!=null){
			userName.setLoginUserName(name);
			userName.setNickName(NickName);
			if(adminUserMapper.updateByPrimaryKeySelective(userName)>0)
				return true;
			
		}
	return false;
	}
	@Override
	public Boolean updatePassWord(Integer id, String password, String newPassword) {
		AdminUser userName=adminUserMapper.selectByPrimaryKey(id);
		if(userName!=null){
			String passWord=MD5Util.MD5Encode(password, "UTF-8");
			String NewpassWord=MD5Util.MD5Encode(newPassword, "UTF-8");
			if(passWord.equals(userName.getLoginPassword())){
				userName.setLoginPassword(NewpassWord);
				if(adminUserMapper.updateByPrimaryKeySelective(userName)>0){
					return true;
				}
				
			}

		}
		return false;
	}

	

}
