package com.itheima.filter;

import com.itheima.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
@Slf4j
//@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest ServletRequest, ServletResponse ServletResponse, FilterChain FilterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) ServletRequest;
        HttpServletResponse response = (HttpServletResponse) ServletResponse;
        // 1. get the requested path
        String requestURI = request.getRequestURI(); // /emp/login

        // 2. Determine whether it is a login request, If the path contains "/login", it indicates a login operation and should be allowed.
        if(requestURI.contains("/login")){
            log.info("Login request, allow it...");
            FilterChain.doFilter(request, response);
            return;
        }

        // 3. Obtain the token from the request header
        String token = request.getHeader("token");


        // 4. Check if the token exists. If it doesn't exist, it indicates that the user has not logged in. Return an error message.
        if(token == null ||token.isEmpty()){
            log.info("Token is empty, return 401 error message...");
            response.setStatus(401);
            return;
        }

        // 5. If the token exists, Verify the token. If the verification fails -> Return the error message (with the corresponding 401 status code)
        try {
            JwtUtils.parseJwt(token);
        }catch (Exception e){
            log.info("Token verification failed, return 401 error message...");
            response.setStatus(401);
            return;
        }
        // 6. Verification success, allow the request to proceed
        log.info("Token verification success, allow it...");
        FilterChain.doFilter(request, response);

    }


}
