package com.kelegele.blog.dao;

import com.kelegele.blog.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: blog-4
 * @description: User 数据库操作接口
 * @author: FelixHuang
 * @create: 2018-11-25 18:15
 **/
@Mapper
public interface UserDao {

    String TABLE_NAEM = " user ";
    String INSERT_FIELDS = " name , email , password , salt , head_url , role , article_category ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into", TABLE_NAEM, "(", INSERT_FIELDS, ") values (#{name},#{email},#{password},#{salt},#{headUrl},#{role},#{articleCategory})"})
    public void insertUser(User user);

    @Select({"select", SELECT_FIELDS, "from", TABLE_NAEM, "where id=#{id}"})
    public User seletById(@Param("id") int id);

    @Select({"select", SELECT_FIELDS, "from", TABLE_NAEM, "where email=#{email}"})
    public User seletByEmail(@Param("email") String email);

    @Delete({"delete from", TABLE_NAEM, "where id=#{id}"})
    public void deleteById(@Param("id") int id);

    @Update({"update", TABLE_NAEM, "set article_category = #{articleCategory} where id = #{id}"})
    public void updateCategory(User user);



}
