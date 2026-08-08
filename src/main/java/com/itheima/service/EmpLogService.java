package com.itheima.service;

import com.itheima.pojo.EmpLog;
import com.itheima.pojo.PageResult;

public interface EmpLogService {

    public void insertLog(EmpLog empLog);

    PageResult<EmpLog> page(Integer page, Integer pageSize);
}
