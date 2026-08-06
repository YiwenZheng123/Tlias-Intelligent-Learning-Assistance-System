package com.itheima.service;

import com.itheima.pojo.Dept;

import java.util.List;

public interface DeptService {
    /**
     * Query all department data.
     * @return
     */
    List<Dept> findAll();

    /**
     *
     * Delete department by ID.
     */
    void deleteById(Integer id);


    void add(Dept dept);

    Dept getById(Integer id);

    void update(Dept dept);
}
