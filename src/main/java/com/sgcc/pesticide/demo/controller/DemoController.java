package com.sgcc.pesticide.demo.controller;

import com.sgcc.pesticide.demo.model.Demo;
import com.sgcc.pesticide.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    DemoService demoService;

    /**
     * @Description
     * @author 杜成皓
     * @date 2017/1/21 22:26
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryDemo.do",method = RequestMethod.POST)
    public List<Demo> queryUsers(HttpServletRequest request, HttpServletResponse response){
        List<Demo> list = demoService.queryDemoList();
        return list;
    }

}
