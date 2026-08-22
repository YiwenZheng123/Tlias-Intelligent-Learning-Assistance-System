package com.itheima.interceptor;

import com.itheima.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
//@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        String requestURI = request.getRequestURI();
//        if(requestURI.contains("/login")){
//            log.info("Login request, allow it...");
//            return true;
//        }

        String token = request.getHeader("token");
        if(token == null || token.isEmpty() ){
            log.info("Token is empty, return 401 error message...");
            response.setStatus(401);
            return false;
        }
        try{
            JwtUtils.parseJwt(token);
            log.info("Token is not empty, allow it...");
        }catch (Exception e){
            log.error("Error occurred while processing token", e);
            response.setStatus(500);
            return false;
        }
        log.info("Token verification success, allow it...");
        return true;
    }
}
