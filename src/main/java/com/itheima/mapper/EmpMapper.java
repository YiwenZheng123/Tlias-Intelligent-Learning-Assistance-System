package com.itheima.mapper;

import com.itheima.pojo.Emp;
import com.itheima.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
Employee information mapper.
 */
@Mapper
public interface EmpMapper {
//    /**
//     * Query the total record count.
//     */
//   @Select("SELECT count(*) from emp e left join dept d on e.dept_id = d.id")
//   public Long count();
//
//    /**
//     * Paginated query.
//     */
//    @Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id " +
//            "order by e.update_time desc")
//    public List<Emp> list(Integer start, Integer pageSize);

    //@Select("select e.*, d.name deptName from emp e left join dept d on e.dept_id = d.id order by e.update_time desc")

    /**
     * Query employee information by conditions.
     */
    public List<Emp> list(EmpQueryParam empQueryParam);


    /**
     * Add basic employee information.
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time)" +
    "values (#{username}, #{name}, #{gender}, #{phone}, #{job}, #{salary}, #{image}," +
    "                #{entryDate}, #{deptId}, #{createTime}, #{updateTime})")
    void insert(Emp emp);


    /**
     * Batch delete basic employee information by ID.
     */
    void deleteByIds(List<Integer> ids);

    Emp getById(Integer id);

    void updateById(Emp emp);

    /**
     * Query employee count.
     */
    @MapKey("pos")
    List<Map<String, Object>> countEmpJobData();

    @MapKey("name")
    List<Map<String, Object>> countEmpGenderData();

    List<Emp> listOfAll();
}
