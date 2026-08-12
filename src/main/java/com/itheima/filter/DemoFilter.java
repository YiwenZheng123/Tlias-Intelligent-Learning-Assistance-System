package com.itheima.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
//@WebFilter(urlPatterns = "/*") // Interceptor all requests
public class DemoFilter implements Filter {
    // 初始化方法，web服务器启动的时候执行，只执行一次
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("DemoFilter init");
    }
    // 拦截到请求之后，执行，会执行多次
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse ServeletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("Request intercepted");
        // 放行
        filterChain.doFilter(servletRequest, ServeletResponse);
    }

    @Override
    public void destroy() {
        log.info("DemoFilter destroy");
    }
}
