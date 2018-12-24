package com.kelegele.blog.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @program: blog-4
 * @description: 测试DAO
 * @author: FelixHuang
 * @create: 2018-11-26 12:01
 **/
@Mapper
public interface TestDAO {

    String TABLE_NAEM = " article ";
    String INSERT_FIELDS = " title, describes, content, created_Date, comment_Count, category ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    //数量
    @Select({"select count(id) from", TABLE_NAEM})
    int getArticleCount();
}
