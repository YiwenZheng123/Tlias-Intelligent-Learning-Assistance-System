package com.itheima.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
@Data
public class ClazzQueryParam {
    private Integer page = 1; // Page number
    private Integer pageSize = 10; // Records per page
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String begin;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String end;
}
