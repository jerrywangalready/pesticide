package com.sgcc.comm.interceptor;

import com.sgcc.pesticide.login.model.UserToken;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;
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
        String[] allowUrls =new String[]{"/toLogin","/login"};

        //获取请求地址
        String url =httpServletRequest.getRequestURL().toString();

        for (String strUrl : allowUrls) {
            if(url.contains(strUrl))
            {
                return true;
            }
        }

        //获得session中的用户
        UserToken user =(UserToken) session.getAttribute("userToken");


        if(user ==null)
        {
//            throw new UnLoginException("您尚未登录！");
//            System.out.println(111);
            //重定向
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+"/login/login.do");

        }
        //        String loginState = httpServletRequest.getSession()==null?"":httpServletRequest.getSession().getAttribute("username").toString();
//        if("".equals(loginState)) {
//        }else {
//            System.out.println(222);
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
