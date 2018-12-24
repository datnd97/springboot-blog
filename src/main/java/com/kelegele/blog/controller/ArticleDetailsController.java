package com.kelegele.blog.controller;

import com.kelegele.blog.model.Article;
import com.kelegele.blog.model.ViewObject;
import com.kelegele.blog.service.ArticleService;
import com.kelegele.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: blog
 * @description: 文章详情
 * @author: FelixHuang
 * @create: 2018-12-22 17:12
 **/
@Controller
public class ArticleDetailsController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @RequestMapping("/articleDetails")
    public String singleArticle(Model model,
                                HttpServletRequest request) {

        //导航分类
        List<ViewObject> cvs = new ArrayList<>();
        List<String> category = userService.getCategoryList(1);
        for (String c : category) {
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

        String articleCategory = request.getParameter("category");
        int articleId = Integer.parseInt(request.getParameter("id"));
        //文章分类
        ViewObject c = new ViewObject();
        c.set("category", articleCategory);
        model.addAttribute("hasCategory", c);

        //文章
        //System.out.println(articleCategory +articleId );
        Article article = articleService.getArticleById(articleId);
        ViewObject a = new ViewObject();
        a.set("article",article);
        model.addAttribute("article", a);

        //ViewObject clickCount = new ViewObject();
        //String currentPage = jedisService.get(RedisKeyUntil.getClickCountKey("/article/"+articleId));
        //String sumPage = jedisService.get(RedisKeyUntil.getClickCountKey("SUM"));
        //clickCount.set("currentPage",currentPage);
        //clickCount.set("sumPage",sumPage);
        //model.addAttribute("clickCount",clickCount);

//    if (hostHolder.getUser()==null)
//      model.addAttribute("liked",0);
//    else
//      model.addAttribute("liked",likeService.getLikeStatus(hostHolder.getUser().getId(),articleId));
//    model.addAttribute("likeCount",likeService.getLikeCount(articleId));
//    model.addAttribute("dislikeCount",likeService.getDislikeCount(articleId));

        //评论
        //List<Comment> comments = commentService.getCommentsByArticleId(articleId);
        //List<ObjectView> vos = new ArrayList<>();
        //for (Comment comment: comments){
        //ObjectView vo = new ObjectView();
        //vo.set("comment",comment);
        //vo.set("user",userService.getUser(comment.getUserId()));
        //vos.add(vo);
        //}
        //model.addAttribute("vos",vos);
        //model.addAttribute("commentsCount",comments.size());

//    String articleClickCount = jedisService.get(RedisKeyUntil.getClickCountKey("/article/"+article.getId()));
//    if (articleClickCount==null)
//      articleClickCount = "0";
//    model.addAttribute("articleClickCount",articleClickCount);

        return "articleDetails";
    }

}
