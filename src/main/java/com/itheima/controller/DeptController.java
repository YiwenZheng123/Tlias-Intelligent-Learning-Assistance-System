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
        //System.out.println("Query all department data");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    /**
     *
     * Delete a department. Approach 1: get the ID from HttpServletRequest.
     */
//    @DeleteMapping("/depts")
//    public Result delete(HttpServletRequest request){
//        String idStr = request.getParameter("id");
//        int id = Integer.parseInt(idStr);
//        System.out.println("Delete department by ID: " + id);
//        return Result.success();
//
//    }
    /**
     * Delete a department. Approach 2: get the ID with @RequestParam.
     * Note: once @RequestParam is declared, the request parameter is required.
     */
//    @DeleteMapping("/depts")
//    public Result delete(@RequestParam("id") Integer deptId){
//        System.out.println("Delete department by ID: " + deptId);
//        return Result.success();
//    }

    /**
     * Delete a department. Approach 3: omit @RequestParam when the request parameter name matches the method parameter name.
     */
    @DeleteMapping
    public Result delete(Integer id){
        log.info("根据ID删除部门 {}", id);
        //System.out.println("Delete department by ID: " + id);
        deptService.deleteById(id);
        return Result.success();
    }

    /**
     * Add a department.
     */
    @PostMapping
    public Result add(@RequestBody Dept dept){
        log.info("添加部门 {}", dept);
        //System.out.println("Add department: " + dept);
        deptService.add(dept);
        return Result.success();

    }
//    Approach 1
//    /**
//     * Query department data by ID.
//     */
//    @GetMapping("/depts/{id}")
//    public Result getInfo(@PathVariable("id") Integer deptId){
//        System.out.println("Query department by ID: " + deptId);
//        return Result.success();
//    }
    /**
     * Query department data by ID.
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("根据ID查询部门： {}", id);
        //System.out.println("Query department by ID: " + id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);

    }
    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("修改部门 {}" , dept);
        // System.out.println("Update department: " + dept);
        deptService.update(dept);
        return Result.success();
    }


}
