package com.itheima.mapper;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClazzMapper {

    void insert(Clazz clazz);

    void delete(Integer id);

    List<Clazz> list(ClazzQueryParam clazzQueryParam);

    Clazz getById(Integer id);

    void updateById(Clazz clazz);

    List<Clazz> listAll();
}
