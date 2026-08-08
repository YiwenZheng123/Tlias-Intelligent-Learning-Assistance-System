package com.itheima.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
@Data
public class StudentQueryParam {
    private Integer page = 1; // Page number
    private Integer pageSize = 10; // Records per page
    private String name;
    private Integer degree;
    private Integer clazzId;
}
