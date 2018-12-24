package com.kelegele.blog.controller;

import com.kelegele.blog.model.ViewObject;
import com.kelegele.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: blog
 * @description: 关于页面
 * @author: FelixHuang
 * @create: 2018-12-20 21:19
 **/
@Controller
public class AboutController {

    @Autowired
    private UserService userService;

    @RequestMapping("/about")
    public String about(Model model) {
        //导航分类
        List<ViewObject> cvs = new ArrayList<>();
        List<String> categorys = userService.getCategoryList(1);
        for (String c : categorys) {
            ViewObject cv = new ViewObject();
            cv.set("category", c);
            cvs.add(cv);
        }
        model.addAttribute("category", cvs);

        //用户
        String ava = userService.getAvatar(1);
        ViewObject avatar = new ViewObject();
        avatar.set("avatar", ava);
        model.addAttribute("avatar", avatar);


        return "about";
    }

}
