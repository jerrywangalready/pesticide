package com.sgcc.comm.interceptor;

import com.sgcc.pesticide.login.model.UserToken;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author jerrywang
 * @create 2017/1/10.
 */
public class AllInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        HttpSession session = httpServletRequest.getSession();

        //无需登录，允许访问的地址
        String[] allowUrls =new String[]{"/login/doLogin","/login/login"};

        //获取请求地址
        String url =httpServletRequest.getRequestURL().toString();

        //获得session中的用户
        for (String strUrl : allowUrls) {
            if(url.contains(strUrl))
            {
                return true;
            }
        }
        UserToken user = (UserToken) session.getAttribute("userToken");


        if(user ==null)
        {
            //重定向
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/login/login.do");

        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
