package com.kelegele.blog.configuration;

import com.kelegele.blog.interceptor.LoginRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @program: blog-4
 * @description: 将拦截器添加到系统
 * @author: FelixHuang
 * @create: 2018-11-26 10:49
 **/
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Autowired
    private LoginRequestInterceptor loginRequestInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //拦截规则：除了login，其他都拦截判断
        registry.addInterceptor(loginRequestInterceptor).addPathPatterns("/admin/**").excludePathPatterns("/admin/in","/admin/login");
        super.addInterceptors(registry);
    }

}