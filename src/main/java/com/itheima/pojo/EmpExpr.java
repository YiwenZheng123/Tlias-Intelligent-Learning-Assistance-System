package com.itheima.pojo;

import lombok.Data;

import java.time.LocalDate;

/**
 * Work experience.
 */
@Data
public class EmpExpr {
    private Integer id; // ID
    private Integer empId; // Employee ID
    private LocalDate begin; // Start date
    private LocalDate end; // End date
    private String company; // Company name
    private String job; // Job title
}
