package com.luyan.web.interceptor;

import cn.hutool.json.JSONUtil;
import com.luyan.entity.utils.R;
import com.luyan.entity.utils.ResultCodeEnum;
import com.luyan.utils.BaseContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Integer id = BaseContext.getCurrentId();
        if (id == null) {  // token 校验失败
            R<Object> error = R.error(ResultCodeEnum.NO_LOGIN);
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(JSONUtil.toJsonStr(error));
            return false;
        }
        return true;
    }
}
