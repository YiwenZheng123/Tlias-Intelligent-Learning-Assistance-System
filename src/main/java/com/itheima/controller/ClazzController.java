package com.itheima.controller;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.ClazzService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClazzController {

    @Autowired
    private ClazzService clazzService;
    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam) {
        log.info("clazzQueryParam: {}", clazzQueryParam);
        PageResult<Clazz> pageResult = clazzService.page(clazzQueryParam);
        return Result.success(pageResult);

    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        log.info("Delete class: {}", id);
        clazzService.delete(id);
        return Result.success();
    }

    @PostMapping
    public Result save(@RequestBody Clazz clazz) throws Exception{
        log.info("Save class: {}", clazz);
        clazzService.save(clazz);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("Get class by id: {}", id);
        Clazz clazz = clazzService.getById(id);
        return Result.success(clazz);
    }

    @PutMapping
    public Result update(@RequestBody Clazz clazz) {
        log.info("Update class: {}", clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    @GetMapping("/list")
    public Result list() {
        log.info("Get all classes");
        List<Clazz> list = clazzService.list();
        return Result.success(list);
    }

}
