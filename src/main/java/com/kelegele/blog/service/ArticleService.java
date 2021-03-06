package com.kelegele.blog.service;

import com.kelegele.blog.dao.ArticleDao;
import com.kelegele.blog.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: blog-4
 * @description: 文章功能
 * @author: FelixHuang
 * @create: 2018-11-25 22:01
 **/
@Service
public class ArticleService {

    @Autowired
    private ArticleDao articleDao;

    //@Autowired
    //private ArticleTagDao articleTagDao;

    public int addArticle(Article article) {
        return articleDao.insertArticle(article) > 0 ? article.getId() : 0;
    }

    public Article getArticleById(int qId) {
        return articleDao.selectById(qId);
    }

//    public int getArticleCount() {
//        return articleDao.getArticleCount();
//    }
//
//    public int getArticleCountByCategory(String category) {
//        return articleDao.getArticleCountByCategory(category);
//    }

//  public int getArticleCountByTag(int tagId){
//    return articleTagDao.selectArticleCountByTagId(tagId);
//  }

    public List<Article> getLatestArticles(int offset, int limit) {
        return articleDao.selectLatestArticles(offset, limit);
    }

    public List<Article> getArticlesByCategory(String category, int offset, int limit) {
        return articleDao.selecttArticlesByCategory(category, offset, limit);
    }

//  public List<Article> getArticlesByTag(int tagId,int offset, int limit){
//    return articleTagDao.selectByTagId(tagId,offset,limit);
//  }

    public void updateCommentCount(int id, int count) {
        articleDao.updateCommentCount(id, count);
    }


    public Article getTopArticles() {
        return articleDao.getTopArticles().get(0);
    }

}
