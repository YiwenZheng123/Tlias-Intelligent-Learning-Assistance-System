package com.itheima.pojo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Emp {
    private Integer id; // ID, primary key
    private String username; // Username
    private String password; // Password
    private String name; // Name
    private Integer gender; // Gender, 1: male, 2: female
    private String phone; // Phone number
    private Integer job; // Job, 1: head teacher, 2: lecturer, 3: student affairs manager, 4: academic affairs manager, 5: consultant
    private Integer salary; // Salary
    private String image; // Avatar URL
    private LocalDate entryDate; // Entry date
    private Integer deptId; // Related department ID
    private LocalDateTime createTime; // Creation time
    private LocalDateTime updateTime; // Update time

    // Department name for response display.
    private String deptName;
    private List<EmpExpr>  ExprList;
}
