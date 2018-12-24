package com.kelegele.blog;

import com.kelegele.blog.model.Article;
import com.kelegele.blog.model.ViewObject;
import com.kelegele.blog.service.ArticleService;
import com.kelegele.blog.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @Test
    public void contextLoads() {

        Map<String, String> map1 = userService.register("kelegele@qq.com", "201014");
        System.out.println(map1);

    }

    @Test
    public void cat(){

        List<Article> articles = articleService.getLatestArticles(0, 5);
        System.out.println(articles);

        for (Article article : articles) {
            ViewObject vo = new ViewObject();
            vo.set("article", article);
            System.out.println(article.getAuthor());
        }
    }

    @Test
    public void ticket(){
        List<String> categorys = userService.getCategoryList(1);
        System.out.println(categorys);
    }

    @Test
    public void categorys(){
        int i = 0;
        List<String> categorys = userService.getCategoryList(1);
        for (String c : categorys) {
            ViewObject cv = new ViewObject();
            cv.set("category", c);
            System.out.println(i++);
            System.out.println(c);
        }
    }

}
