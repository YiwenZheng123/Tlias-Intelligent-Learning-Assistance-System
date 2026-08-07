package com.itheima.service;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import com.itheima.pojo.PageResult;

import java.util.List;

public interface EmpService {
//    PageResult<Emp> page(Integer page, Integer pageSize,String name, Integer gender, LocalDate begin, LocalDate end);

    /**
     * Paginated query.
     */
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    void save(Emp emp) throws Exception;

    void delete(List<Integer> id);

    Emp getInfo(Integer id);

    void update(Emp emp);

    List<Emp> list();
}
