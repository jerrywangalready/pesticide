package com.sgcc.pesticide.index.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author jerrywang
 * @create 2017/1/10.
 */
@Controller
@RequestMapping("/index")
public class IndexController {

    /**
     * @Description 首页初始化
     * @author JerryWang
     * @date 2017/1/16 11:18
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/index.do")
    public String index(HttpServletRequest request, HttpServletResponse response){
        return "/index/index";
    }

    /**
     * @Description 退出功能
     * @author JerryWang
     * @date 2017/1/16 11:17
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/logout.do")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        request.getSession().invalidate();
        return "true";
    }

}
