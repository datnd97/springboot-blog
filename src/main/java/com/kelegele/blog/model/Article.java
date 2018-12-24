package com.kelegele.blog.model;

import java.util.Date;

import static com.kelegele.blog.util.blogUtil.dateParser;

/**
 * @program: blog-4
 * @description: 文章实体类
 * @author: FelixHuang
 * @create: 2018-11-25 21:53
 **/
public class Article {
    private int id;
    private String title;//标题
    private String describes;//描述
    private String content;//内容
    private Date createdDate;//创建日期
    private int commentCount;//评论数
    private String category;//类别
    private String mainPicture;//主图
    private String picture;//配图
    private String author;//作者

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreatedDate() {
        return dateParser(createdDate, "yyyy-MM-dd HH:mm:ss");
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public int getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMainPicture() {
        return mainPicture;
    }

    public void setMainPicture(String main_picture) {
        this.mainPicture = main_picture;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
