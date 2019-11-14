package com.bin.blog.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import com.bin.blog.entity.AdminUser;
import com.bin.blog.service.AdminUserService;
@Controller
//该控制器处理的所有请求都被映射到/admin下
@RequestMapping("/admin")
public class AdminController {
	@Resource
	AdminUserService adminUserService;
	//映射一个get请求 到login.html
	  @GetMapping({"/login"})
	    public String login() {
	        return "admin/login";
	    }
	//映射一个POST请求
	@PostMapping(value = "/login")
public String login(@RequestParam("userName")String name ,@RequestParam("password")String password,@RequestParam("verifyCode")String verifyCode,HttpSession session){
	//	System.out.println("验证入参"+name+password);
	if(StringUtils.isEmpty(verifyCode)){
		session.setAttribute("errorMsg","验证码不能为空");
		return "admin/login";
	}
	if(StringUtils.isEmpty(name)&&StringUtils.isEmpty(password)){
		session.setAttribute("errorMsg", "账号或密码不能为空");
		return "admin/login";
	}
	  String kaptchaCode = session.getAttribute("verifyCode") + "";
      if (StringUtils.isEmpty(kaptchaCode) || !verifyCode.equals(kaptchaCode)) {
          session.setAttribute("errorMsg", "验证码错误");
          return "admin/login";
      }
      AdminUser userAdmin = adminUserService.login(name, password);
     // System.out.println(userAdmin);
      if (userAdmin != null) {
          session.setAttribute("loginUser", userAdmin.getNickName());
          session.setAttribute("loginUserId", userAdmin.getAdminUserId());
          //session过期时间设置为7200秒 即两小时
          session.setMaxInactiveInterval(60 * 60 * 2);
          //重定向到index
          return "redirect:/admin/index";
      } else {
          session.setAttribute("errorMsg", "登陆失败");
          return "admin/login";
      }
}
	  @GetMapping({"/profile"})
	public String profile(HttpServletRequest request){
		Integer loginUserId = (int) request.getSession().getAttribute("loginUserId");
			AdminUser adminuser=adminUserService.getUserId(loginUserId);
			if(adminuser==null){
				return "admin/login";
			}
			request.setAttribute("path", "profile");
	        request.setAttribute("loginUserName", adminuser.getLoginUserName());
	        request.setAttribute("nickName", adminuser.getNickName());
	        return "admin/profile";
		}
	  @PostMapping("/profile/name")
	  //当加上@ResponseBody注解后不会解析成跳转地址 会解析成相应的json格式的对象 集合 字符串或者xml等直接返回给前台 可以通过 ajax 的“success”：fucntion(data){} data直接获取到。
	    @ResponseBody
	  public String updateName(HttpServletRequest request,@RequestParam("loginUserName")String userName,@RequestParam("nickName")String nickName){
		  if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(nickName)) {
	            return "修改参数不能为空";
	        }
		  Integer id=(int) request.getSession().getAttribute("loginUserId");
		  if(adminUserService.updateName(id, userName, nickName)){
		   return "success";
		  }else{
			  return "修改失败";
		  }
		
	  }
	  @PostMapping("/profile/password")
	    @ResponseBody
	public String updatePassword(HttpServletRequest request,@RequestParam("originalPassword")String passWord,@RequestParam("newPassword")String NewPassword){
		 if (StringUtils.isEmpty(passWord) || StringUtils.isEmpty(NewPassword)) {
	            return "修改参数不能为空";
	        }
		  Integer id=(int) request.getSession().getAttribute("loginUserId");
		  if(adminUserService.updatePassWord(id, passWord, NewPassword)){
			  request.getSession().removeAttribute("loginUserId");
	            request.getSession().removeAttribute("loginUser");
	            request.getSession().removeAttribute("errorMsg");
	            return "success";
	        } else {
	            return "修改失败";
	        }
	    }
	  @PostMapping("/admin/logout")
	  public String logout (HttpServletRequest request){
		  request.getSession().removeAttribute("loginUserId");
          request.getSession().removeAttribute("loginUser");
          request.getSession().removeAttribute("errorMsg");
		  return "admin/login";
	  }
	  @GetMapping({"", "/", "/index", "/index.html"})
	    public String index() {
	        return "admin/index";
	    }
	  
}
	  
