package com.itheima.controller;

import com.itheima.pojo.Emp;
import com.itheima.pojo.LoginInfo;
import com.itheima.pojo.Result;
import com.itheima.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Login controller.
 */
@Slf4j
@RestController
public class LoginController {

    private EmpService empService;

    public LoginController(EmpService empService) {
        this.empService = empService;
    }

    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("emp: {}", emp);
        LoginInfo info = empService.login(emp);
        if (info != null) {
            return Result.success(info);
        }
        return Result.error("Username or password error!");


    }
}
