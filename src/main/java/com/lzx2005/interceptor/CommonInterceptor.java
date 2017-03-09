package com.lzx2005.interceptor;

import com.lzx2005.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Raven on 2016/7/23.
 * 登陆拦截器
 */
public class CommonInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String path = httpServletRequest.getContextPath();
        String baseUrl  = httpServletRequest.getScheme() + "://" + httpServletRequest.getServerName() + ":" + httpServletRequest.getServerPort() + path + "/";
        httpServletRequest.setAttribute("baseUrl",baseUrl);
        String requestURI = httpServletRequest.getRequestURI();

        String substring = requestURI.substring(1);
        System.out.println("substring="+substring);
        httpServletRequest.setAttribute("pageActionName",substring);




        Map<String, String[]> parameterMap = httpServletRequest.getParameterMap();
        StringBuffer sb = new StringBuffer();
        for(Map.Entry<String,String[]> entry : parameterMap.entrySet()){
            if(!entry.getKey().equalsIgnoreCase("page")){
                StringBuffer stringBuffer = new StringBuffer();
                for(String s : entry.getValue()){
                    stringBuffer.append(s);
                    stringBuffer.append(",");
                }
                stringBuffer.deleteCharAt(stringBuffer.length()-1);
                sb.append(entry.getKey()+"="+stringBuffer.toString()+"&");
            }
        }
        httpServletRequest.setAttribute("urlParams",sb.toString());
        return true;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
