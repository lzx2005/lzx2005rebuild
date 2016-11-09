package com.lzx2005.interceptor;

import com.lzx2005.entity.User;
import com.lzx2005.tool.Log;
import org.springframework.asm.Handle;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Raven on 2016/7/23.
 * 登陆拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //System.out.println("进入了拦截器");
        //Log.e(this.getClass().getName(),"123");
        boolean isLogin = false;
        User user = (User) httpServletRequest.getSession().getAttribute("user");
        if(user!=null){
            isLogin = true;
        }else{
            httpServletResponse.sendRedirect("/");
        }
        return isLogin;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        if(modelAndView!=null) modelAndView.getModel().put("actionName",httpServletRequest.getRequestURI());
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
