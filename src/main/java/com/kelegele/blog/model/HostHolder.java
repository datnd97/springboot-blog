package com.kelegele.blog.model;

import org.springframework.stereotype.Component;

/**
 * @program: blog-4
 * @description: 线程分布
 * @author: FelixHuang
 * @create: 2018-11-26 10:42
 **/
@Component
public class HostHolder {

    private static ThreadLocal<User> users = new ThreadLocal<>();

    public User getUser() {
        return users.get();
    }

    public void setUser(User user) {
        users.set(user);
    }

    public void clear() {
        users.remove();
    }
}
