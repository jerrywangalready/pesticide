package com.sgcc.comm.interceptor;

import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

/**
 * @author jerrywang
 * @create 2017/1/10.
 */
public class AllInterceptor implements WebRequestInterceptor {


    @Override
    public void preHandle(WebRequest webRequest) throws Exception {
        System.out.println(111);
    }

    @Override
    public void postHandle(WebRequest webRequest, ModelMap modelMap) throws Exception {
        System.out.println(222);
    }

    @Override
    public void afterCompletion(WebRequest webRequest, Exception e) throws Exception {
        System.out.println(333);
    }
}
