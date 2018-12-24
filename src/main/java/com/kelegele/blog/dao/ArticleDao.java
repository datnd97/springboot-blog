package com.kelegele.blog.dao;

import com.kelegele.blog.model.Article;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @program: blog-4
 * @description: 文章 DAO
 * @author: FelixHuang
 * @create: 2018-11-25 21:52
 **/
@Mapper
public interface ArticleDao {

    String TABLE_NAEM = " article ";
    String INSERT_FIELDS = " title, describes, content, created_Date, comment_Count, category, main_picture, pictures, author ,top";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    //插入文章
    @Insert({"insert into", TABLE_NAEM, "(", INSERT_FIELDS, ") values (#{title},#{describes},#{content}" +
            ",#{createdDate},#{commentCount},#{category})"})
    int insertArticle(Article article);

    //查找文章通过ID
    @Select({"select", SELECT_FIELDS, "from", TABLE_NAEM, "where id=#{id}"})
    Article selectById(int id);

    //查找指定文章
    @Select({"select", SELECT_FIELDS, "from", TABLE_NAEM, "order by id desc limit #{offset},#{limit}"})
    List<Article> selectLatestArticles(@Param("offset") int offset, @Param("limit") int limit);

    //按类型和数量
    @Select({"select", SELECT_FIELDS, "from", TABLE_NAEM, "where category=#{category} order by id desc limit #{offset},#{limit}"})
    List<Article> selecttArticlesByCategory(@Param("category") String category, @Param("offset") int offset, @Param("limit") int limit);

    //类型
    @Select({"select count(id) from", TABLE_NAEM, "where category=#{category}"})
    int getArticleCountByCategory(@Param("category") String category);

    //数量
    @Select({"select count(id) from", TABLE_NAEM})
    int getArticleCount();

    //更新通过ID
    @Update({"update", TABLE_NAEM, "set comment_count = #{commentCount} where id = #{questionId}"})
    void updateCommentCount(@Param("questionId") int questionId, @Param("commentCount") int commentCount);

    //删除
    @Delete({"delete from", TABLE_NAEM, "where id=#{id}"})
    void deleteById(int id);

    //查询置顶文章数据
    @Select({"select * from", TABLE_NAEM, "where top=1"})
    List<Article> getTopArticles();
}
