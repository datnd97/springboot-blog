package com.kelegele.blog.controller;

import com.kelegele.blog.model.Article;
import com.kelegele.blog.model.HostHolder;
import com.kelegele.blog.model.ViewObject;
import com.kelegele.blog.service.ArticleService;
import com.kelegele.blog.service.JedisService;
import com.kelegele.blog.service.UserService;
import com.kelegele.blog.util.blogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @program: blog-4
 * @description: 文章编辑及分页显示
 * @author: FelixHuang
 * @create: 2018-11-26 10:59
 **/
@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

//  @Autowired
//  private CommentService commentService;
//
//  @Autowired
//  private LikeService likeService;
//
//  @Autowired
//  private TagService tagService;

    @Autowired
    private UserService userService;

    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private JedisService jedisService;

    @RequestMapping("/article")
    public String article(Model model, HttpServletRequest request){
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

        //文章列表
        String reqCategory = request.getParameter("category");
        //System.out.println(reqCategory);
        ViewObject c = new ViewObject();
        c.set("category", reqCategory);
        model.addAttribute("hasCategory", c);

        List<ViewObject> art = new ArrayList<>();
        List<Article> articles = articleService.getArticlesByCategory(reqCategory, 0, 10);
        for (Article article : articles) {
            ViewObject vo = new ViewObject();
            vo.set("article", article);
            art.add(vo);
        }
        model.addAttribute("articles", art);

        return "article";
    }

    @RequestMapping("/articleAdd")
    public String addArticle(@RequestParam("title") String title, @RequestParam("category") String category,
                             @RequestParam("tag") String tag, @RequestParam("describe") String describe,
                             @RequestParam("content") String content) {
        Article article = new Article();
        article.setTitle(title);
        article.setDescribes(describe);
        article.setCreatedDate(new Date());
        article.setCommentCount(0);
        article.setContent(blogUtil.tranfer(content));
        article.setCategory(category);
        int articleId = articleService.addArticle(article);

        //标签
//    String[] tags = tag.split(",");
//    for (String t : tags){
//      Tag tag1 = tagService.selectByName(t);
//      if (tag1==null){
//        Tag tag2 = new Tag();
//        tag2.setName(t);
//        tag2.setCount(1);
//        int tagId = tagService.addTag(tag2);
//
//        ArticleTag articleTag = new ArticleTag();
//        articleTag.setTagId(tagId);
//        articleTag.setArticleId(articleId);
//        tagService.addArticleTag(articleTag);
//      }else {
//        tagService.updateCount(tag1.getId(),tag1.getCount()+1);
//
//        ArticleTag articleTag = new ArticleTag();
//        articleTag.setTagId(tag1.getId());
//        articleTag.setArticleId(articleId);
//        tagService.addArticleTag(articleTag);
//      }
//    }

        //分类
        //String categoryKey = RedisKeyUntil.getCategoryKey(category);
        //jedisService.incr(categoryKey);

        return "redirect:/";
    }



}
