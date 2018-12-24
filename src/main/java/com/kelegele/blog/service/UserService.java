package com.kelegele.blog.service;

import com.kelegele.blog.dao.LoginTicketDao;
import com.kelegele.blog.dao.UserDao;
import com.kelegele.blog.model.Ticket;
import com.kelegele.blog.model.User;
import com.kelegele.blog.util.blogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Email;
import java.util.*;

/**
 * @program: blog-4
 * @description: 用户service
 * @author: FelixHuang
 * @create: 2018-11-25 18:25
 **/
@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private LoginTicketDao loginTicketDao;

    /*
     * 注册
     * 输入用户名和密码
     * 判空
     * 生成用户数据
     * 插入数据库
     * 更新tichet（包含tichet插入数据库）
     */
    public Map<String, String> register(String email, String password) {
        Map<String, String> map = new HashMap<>();
        Random random = new Random();
        if (StringUtils.isBlank(email)) {
            map.put("msg", "用户名不能为空");
            return map;
        }

        if (StringUtils.isBlank(password)) {
            map.put("msg", "密码不能为空");
            return map;
        }

        User u = userDao.seletByEmail(email);
        if (u != null) {
            map.put("msg", "邮箱已经注册");
            return map;
        }

        User user = new User();
        user.setName("USER");
        user.setSalt(UUID.randomUUID().toString().substring(0, 5));
        user.setHeadUrl(String.format("https://images.nowcoder.com/head/%dm.png", random.nextInt(1000)));
        user.setPassword(blogUtil.MD5(password + user.getSalt()));
        user.setRole("user");
        user.setEmail(email);
        user.setArticleCategory("null");
        userDao.insertUser(user);

        String ticket = addLoginTicket(user.getId());
        map.put("ticket", ticket);

        return map;
    }

    /*
     * 更新ticket
     * 生成
     * 存到数据库
     * （controller层 再将ticket凭证存到cookie
     */
    public String addLoginTicket(int userId) {
        Ticket ticket = new Ticket();
        ticket.setUserId(userId);
        Date date = new Date();
        date.setTime(date.getTime() + 1000 * 3600 * 30);
        ticket.setExpired(date);
        ticket.setStatus(0);
        ticket.setTicket(UUID.randomUUID().toString().replaceAll("-", ""));

        loginTicketDao.insertTicket(ticket);

        return ticket.getTicket();
    }

    /*
     * 用户登录
     * 判空
     * 判断存在
     * 验证密码
     * 更新ticket
     */
    public Map<String, String> login(String email, String password) {

        Map<String, String> map = new HashMap<>();
        Random random = new Random();

        if (StringUtils.isBlank(email)) {
            map.put("msg", "邮箱不能为空");
            return map;
        }

        if (StringUtils.isBlank(password)) {
            map.put("msg", "密码不能为空");
            return map;
        }

        User u = userDao.seletByEmail(email);//从数据库取到用户
        if (u == null) {
            map.put("msg", "用户不存在");
            return map;
        }

        if (!blogUtil.MD5(password + u.getSalt()).equals(u.getPassword())) {
            map.put("msg", "密码错误");
            return map;
        }

        String ticket = addLoginTicket(u.getId());
        map.put("ticket", ticket);

        return map;
    }

    /*
     * 获取分类列表
     *
     */
    public List<String> getCategoryList(int userId) {
        User u = userDao.seletById(userId);
        return blogUtil.toStrList(u.getArticleCategory());
    }

    /*
     * 生成分类列表
     */
    public Map<String, String> creatCategoryList(List<String> list, int userId) {

        Map<String, String> map = new HashMap<>();
        Random random = new Random();

        if (list.isEmpty()) {
            map.put("msg", "列表为空，无法生成");
            return map;
        }

        User u = userDao.seletById(userId);
        if (u == null) {
            map.put("msg", "用户不存在");
            return map;
        }

        u.setArticleCategory(blogUtil.toStr(list));
        userDao.updateCategory(u);
        map.put("msg", "生成成功");

        return map;
    }

    /*
     * 获取头像
     */
    public String getAvatar(int userId) {
        User u = userDao.seletById(userId);
        return u.getHeadUrl();
    }

    /*
     * 退出
     */
    public void logout(String ticket, HttpServletRequest request, HttpServletResponse response){
        loginTicketDao.updateStatus(ticket,1);


    }


}



