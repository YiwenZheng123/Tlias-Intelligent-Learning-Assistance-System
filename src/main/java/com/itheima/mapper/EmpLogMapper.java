package com.itheima.mapper;

import com.itheima.pojo.EmpLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmpLogMapper {

    @Insert("insert into emp_log (operate_emp_id, operate_time, class_name, method_name, method_params, return_value, cost_time, operate_emp_name, info) " +
            "values (#{operateEmpId}, #{operateTime}, #{className}, #{methodName}, #{methodParams}, #{returnValue}, #{costTime}, #{operateEmpName}, #{info})")
    public void insert(EmpLog empLog);

    @Select("select id, operate_emp_id, operate_time, class_name, method_name, method_params, return_value, cost_time, operate_emp_name, info from emp_log order by operate_time desc")
    List<EmpLog> list();
}
