package com.practice.intercepter;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor  implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("LoginUser");
        if (user == null) {
            request.setAttribute("msg", "没有权限，请先登录");
            request.getRequestDispatcher("/tologin").forward(request, response);
        } else {
            //登录放行
            return true;
        }
        return false;
    }
}
