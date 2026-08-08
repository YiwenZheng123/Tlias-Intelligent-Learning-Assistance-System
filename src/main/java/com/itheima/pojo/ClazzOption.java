package com.itheima.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.security.auth.Subject;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClazzOption {
    private List clazzList;
    private List dataList;
}
