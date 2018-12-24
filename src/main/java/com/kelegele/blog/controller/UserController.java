package com.kelegele.blog.controller;

import com.kelegele.blog.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @program: blog-4
 * @description: 用户注册/登录入口
 * @author: FelixHuang
 * @create: 2018-11-25 18:54
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /*
     * 登录
     * @RequestParam 参数获取
     */
    @RequestMapping("/login")
    public String login(Model model,
                        HttpServletResponse httpResponse,
                        @RequestParam String username,
                        @RequestParam String password,
                        @RequestParam(value = "next", required = false) String next) {

        Map<String, String> map = userService.login(username, password);

        //map包含ticket
        if (map.containsKey("ticket")) {
            Cookie cookie = new Cookie("ticket", map.get("ticket"));
            cookie.setPath("/");
            httpResponse.addCookie(cookie);

            if (StringUtils.isNotBlank(next)) {
                return "redirect:" + next;
            }

            return "redirect:/";//重定向到主页

            //不包含ticket
        } else {
            model.addAttribute("msg", map.get("msg"));
            return "login";
        }
    }

    /*
     * 注册
     */
    @RequestMapping("/register")
    public String register(Model model,
                           HttpServletResponse httpResponse,
                           @RequestParam String username,
                           @RequestParam String password,
                           @RequestParam(value = "next", required = false) String next) {

        Map<String, String> map = userService.register(username, password);



        if (map.containsKey("ticket")) {
            Cookie cookie = new Cookie("ticket", map.get("ticket"));
            cookie.setPath("/");
            httpResponse.addCookie(cookie);

            if (StringUtils.isNotBlank(next))
                return "redirect:" + next;
            else
                return "redirect:/";
        } else {
            model.addAttribute("msg", map.get("msg"));
            return "login";
        }
    }

}
