package com.kelegele.blog.model;

/**
 * @program: blog-4
 * @description: User 实体类
 * @author: FelixHuang
 * @create: 2018-11-25 18:12
 **/
public class User {

    private int id;
    private String email;
    private String name;
    private String password;
    private String headUrl;
    private String role;
    private String salt;//安全
    private String articleCategory;//分类

    public User() {
    }

    public User(String name) {
        this.name = name;
        this.password = "";
        this.headUrl = "";
        this.role = "user";
        this.salt = "";
        this.email = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getArticleCategory() {
        return articleCategory;
    }

    public void setArticleCategory(String articleCategory) {
        this.articleCategory = articleCategory;
    }
}
