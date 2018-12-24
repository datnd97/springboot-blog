package com.kelegele.blog.controller;

import com.kelegele.blog.model.User;
import com.kelegele.blog.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @program: blog
 * @description: 管理页面
 * @author: FelixHuang
 * @create: 2018-12-17 22:10
 **/
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @RequestMapping("/in")
    public String in(Model model) {
        //model.addAttribute("next", next);
        model.addAttribute("submitUser", new User());

        return "admin/in";
    }

    @RequestMapping(path = {"/index", "/", "", "/admin"})
    public String admin(Model model, HttpServletRequest request) {

        String page = request.getParameter("page");
        model.addAttribute("page", (page == null || page == "") ? "index" : page);
        model.addAttribute("editormdPath", "/assets/editormd");

        return "admin/index";
    }

    @PostMapping("/register")
    public String register(Model model, HttpServletResponse httpResponse,
                           @RequestParam String email, @RequestParam String password) {

        System.out.println(email);

        Map<String, String> map = userService.register(email, password);
        if (map.containsKey("ticket")) {
            Cookie cookie = new Cookie("UID", map.get("ticket"));
            cookie.setPath("/");
            httpResponse.addCookie(cookie);

            return "redirect:/admin";
        } else {
            model.addAttribute("msg", map.get("msg"));

            return "in";
        }
    }

    @PostMapping("/login")
    public String login(Model model, HttpServletResponse httpResponse,
                        @ModelAttribute User submitUser) {

        System.out.println(submitUser.getEmail());

        Map<String, String> map = userService.login(submitUser.getEmail(), submitUser.getPassword());
        if (map.containsKey("ticket")) {
            Cookie cookie = new Cookie("ticket", map.get("ticket"));
            cookie.setPath("/");
            httpResponse.addCookie(cookie);

            return "redirect:/admin/index";
        } else {
            model.addAttribute("msg", map.get("msg"));

            return "redirect:/admin/in";
        }
    }

    @RequestMapping("/logout")
    public String logout(@CookieValue("ticket") String ticket, HttpServletRequest request,HttpServletResponse response) {
        userService.logout(ticket, request,response);
        return "admin";
    }

}
