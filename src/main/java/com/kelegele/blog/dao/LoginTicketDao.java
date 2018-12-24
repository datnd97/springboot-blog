package com.kelegele.blog.dao;

import com.kelegele.blog.model.Ticket;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.*;

/**
 * @program: blog-4
 * @description: login ticket DAO
 * @author: FelixHuang
 * @create: 2018-11-25 18:20
 **/
@Mapper
public interface LoginTicketDao {
    String TABLE_NAEM = " ticket ";
    String INSERT_FIELDS = " ticket ,user_id, expired, status ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;

    @Insert({"insert into", TABLE_NAEM, "(", INSERT_FIELDS, ") values (#{ticket}, #{userId},#{expired},#{status})"})
    void insertTicket(Ticket ticket);

    @Select({"select", SELECT_FIELDS, "from", TABLE_NAEM, "where id=#{id}"})
    Ticket seletById(@Param("id") int id);

    @Select({"select", SELECT_FIELDS, "from", TABLE_NAEM, "where ticket=#{ticket}"})
    Ticket seletByTicket(String ticket);

    @Update({"update", TABLE_NAEM, "set status = #{status} where ticket = #{ticket}"})
    void updateStatus(@Param("ticket") String ticket, @Param("status") int status);

    @Delete({"delete from", TABLE_NAEM, "where id=#{id}"})
    void deleteById(int id);
}
