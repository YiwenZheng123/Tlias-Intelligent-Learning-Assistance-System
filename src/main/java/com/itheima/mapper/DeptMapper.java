package com.itheima.mapper;

import com.itheima.pojo.Dept;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DeptMapper {
    /**
     * Query all department data.
     */
    // Approach 1: manual result mapping.
//    @Results({
//            @Result(column = "create_time", property = "createTime"),
//            @Result(column = "update_time", property = "updateTime")
//    })

    // Approach 2: use column aliases.
    //@Select("select id, name, create_time createTime, update_time updateTime from dept order by update_time desc ")
    @Select("select id, name, create_time, update_time from dept order by update_time desc ")
    List<Dept> findAll();


    @Delete("DELETE from dept where id = #{id}")
    void deleteById(Integer id);

    @Insert("INSERT into dept(name, create_time, update_time) values (#{name}, #{createTime}, #{updateTime})")
    void insert(Dept dept);

    @Select("select id, name, create_time, update_time from dept where id = #{id}")
    Dept getById(Integer id);

    @Update("update dept set name = #{name}, update_time = #{updateTime} where id =#{id}")
    void update(Dept dept);
}
