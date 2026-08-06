package com.itheima.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * Unified backend response.
 */
@Data
public class Result {

    private Integer code; // Code: 1 for success, 0 for failure
    private String msg; // Error message
    private Object data; // Response data

    public static Result success() {
        Result result = new Result();
        result.code = 1;
        result.msg = "success";
        return result;
    }

    public static Result success(Object object) {
        Result result = new Result();
        result.data = object;
        result.code = 1;
        result.msg = "success";
        return result;
    }

    public static Result error(String msg) {
        Result result = new Result();
        result.msg = msg;
        result.code = 0;
        return result;
    }

}
