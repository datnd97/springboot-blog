package com.kelegele.blog.controller;

import com.kelegele.blog.model.Article;
import com.kelegele.blog.model.ViewObject;
import com.kelegele.blog.service.ArticleService;
import com.kelegele.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: blog-4
 * @description: 主页
 * @author: FelixHuang
 * @create: 2018-11-25 19:20
 **/
@Controller
public class IndexController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @RequestMapping(path = {"/", "/index"})
    public String index(Model model) {

        //文章标题
        List<ViewObject> newh = new ArrayList<>();
        ViewObject newArticle = new ViewObject();
        Article newAr = articleService.getTopArticles();
        newArticle.set("newArticle", newAr);
        newh.add(newArticle);
        model.addAttribute("newh", newh);

        List<ViewObject> vos = new ArrayList<>();
        List<Article> articles = articleService.getLatestArticles(0, 5);
        for (Article article : articles) {
            ViewObject vo = new ViewObject();
            vo.set("article", article);
            vos.add(vo);
        }
        model.addAttribute("vos", vos);

        /*//分页
        ViewObject pagination = new ViewObject();
        int count = articleService.getArticleCount();
        User user = hostHolder.getUser();

        if (user == null || "admin".equals(user.getRole())) {
            model.addAttribute("create", 1);
        } else {
            model.addAttribute("create", 0);
        }

        pagination.set("current", 1);
        pagination.set("nextPage", 2);
        pagination.set("lastPage", count / 4 + 1);
        model.addAttribute("pagination", pagination);*/

        //分类
        List<ViewObject> cvs = new ArrayList<>();
        List<String> categorys = userService.getCategoryList(1);
        for (String c : categorys) {
            ViewObject cv = new ViewObject();
            cv.set("category", c);
            cvs.add(cv);
        }
        model.addAttribute("category", cvs);

        String ava = userService.getAvatar(1);
        ViewObject avatar = new ViewObject();
        avatar.set("avatar", ava);
        model.addAttribute("avatar", avatar);

        return "index";
    }
}
