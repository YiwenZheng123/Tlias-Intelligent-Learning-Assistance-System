package com.itheima.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class EmpQueryParam {
    private Integer page = 1; // Page number
    private Integer pageSize = 10; // Records per page
    private String name; // Name
    private String gender; // Gender
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin; // Entry date start
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate end; // Entry date end
}
