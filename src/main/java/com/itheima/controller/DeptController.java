package com.itheima.controller;

import com.itheima.pojo.Dept;
import com.itheima.pojo.Result;
import com.itheima.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping
    public Result list(){
        log.info("查询全部的部门数据");
        //System.out.println("查询全部的部门数据");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    /**
     *
     * 删除部门 方式一： 用HttpServeletRequest来获取id
     */
//    @DeleteMapping("/depts")
//    public Result delete(HttpServletRequest request){
//        String idStr = request.getParameter("id");
//        int id = Integer.parseInt(idStr);
//        System.out.println("根据ID删除部门" + id);
//        return Result.success();
//
//    }
    /**
     * 删除部门 方式二： 用@RequestParam来获取id
     * 注意事项： 一旦声明了@RequestParam，该参数在请求时参数必须传递，如果不传递将会报错
     */
//    @DeleteMapping("/depts")
//    public Result delete(@RequestParam("id") Integer deptId){
//        System.out.println("根据ID删除部门" + deptId);
//        return Result.success();
//    }

    /**
     * 删除部门 方式三： 省略@RequestParam来获取id(前端传递的请求参数与服务器端方法形参名一致)
     */
    @DeleteMapping
    public Result delete(Integer id){
        log.info("根据ID删除部门 {}", id);
        //System.out.println("根据ID删除部门" + id);
        deptService.deleteById(id);
        return Result.success();
    }

    /**
     *新增部门
     */
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("添加部门 {}", dept);
        //System.out.println("添加部门" + dept);
        deptService.add(dept);
        return Result.success();

    }
//    方法一
//    /**
//     *根据ID查询部门数据
//     */
//    @GetMapping("/depts/{id}")
//    public Result getInfo(@PathVariable("id") Integer deptId){
//        System.out.println("根据ID查询部门： " + deptId);
//        return Result.success();
//    }
    /**
     *根据ID查询部门数据
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("根据ID查询部门： {}", id);
        //System.out.println("根据ID查询部门： " + id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);

    }
    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("修改部门 {}" , dept);
        // System.out.println("修改部门" + dept);
        deptService.update(dept);
        return Result.success();
    }


}
