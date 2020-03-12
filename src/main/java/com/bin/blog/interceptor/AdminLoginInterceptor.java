package com.bin.blog.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 后台系统身份验证拦截器
 * 要实现 Spring 的 HandlerInterceptor 接口
 */
//@Component 注解使其注册到 IOC 容器中
@Component
public class AdminLoginInterceptor implements HandlerInterceptor {
    @Override
    //在业务处理器处理请求之前被调用。预处理，可以进行编码、安全控制、权限校验等处理；
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
        	//如果已经登录，则清除掉异常信息
            request.getSession().removeAttribute("errorMsg");
            return true;
        }
    }
    @Override
    //在业务处理器处理请求执行完成后，生成视图之前执行
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }
    @Override
    //在 DispatcherServlet 完全处理完请求后被调用，可用于清理资源等，返回处理（已经渲染了页面）；
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
