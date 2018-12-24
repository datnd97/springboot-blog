package com.kelegele.blog.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: blog-4
 * @description: 访问权限拦截器
 * @author: FelixHuang
 * @create: 2018-11-26 10:46
 **/
@Component
public class LoginRequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        // TODO Auto-generated method stub
        boolean flag = true;
        //获取UID
        Cookie[] cookies = request.getCookies();
        //判断用户ID是否存在，不存在就跳转到登录界面
        if (cookies == null) {
            response.sendRedirect("/admin/in");
            flag = false;
        } else {
            for (Cookie cookie : cookies) {
                //System.out.println(cookie);
                if (cookie.getName().equals("UID")) {
                    // 取出cookie的值
                    String value = cookie.getValue();
                    //System.out.println(value);

                    flag = true;
                }
            }
        }
        return flag;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

    }
}

