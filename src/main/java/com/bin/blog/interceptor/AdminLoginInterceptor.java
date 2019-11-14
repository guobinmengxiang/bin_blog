package com.bin.blog.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后台系统身份验证拦截器
 */
//@Component 注解使其注册到 IOC 容器中
@Component
public class AdminLoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
    	//获取url
        String uri = request.getRequestURI();
        //loginUser 是登录方法中吧用户名赋给了 loginUser
        if (uri.startsWith("/admin") && null == request.getSession().getAttribute("loginUser")) {
            request.getSession().setAttribute("errorMsg", "请登陆");
            //重定向到登录页面
            response.sendRedirect(request.getContextPath() + "/admin/login");
            return false;
        } else {
            request.getSession().removeAttribute("errorMsg");
            return true;
        }
    }
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
