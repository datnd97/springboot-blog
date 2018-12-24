package com.kelegele.blog.model;

import java.util.Date;

/**
 * @program: blog-4
 * @description: 登录验证login_ticket 实体类
 * @author: FelixHuang
 * @create: 2018-11-25 18:14
 **/
public class Ticket {

    private int id;//列表id
    private int userId;//用户id
    private Date expired;//期满
    private int status;//状态1退出 0 登陆
    private String ticket;//证书

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }


}
