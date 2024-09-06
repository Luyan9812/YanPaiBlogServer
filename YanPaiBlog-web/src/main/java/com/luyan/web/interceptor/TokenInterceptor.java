package com.luyan.web.interceptor;

import com.luyan.utils.BaseContext;
import com.luyan.utils.JwtHelper;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    @Resource
    private JwtHelper jwtHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        if (jwtHelper.isExpiration(token)) {  // token 校验失败
            BaseContext.setCurrentId(null);
        } else {
            BaseContext.setCurrentId(jwtHelper.getUserId(token));
        }
        return true;
    }
}
