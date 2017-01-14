package com.sgcc.pesticide.test.controller;

import com.sgcc.pesticide.login.model.User;
import com.sgcc.pesticide.login.model.UserToken;
import com.sgcc.pesticide.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 * @create 2017/1/7.
 */
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    UserService userService;

//    @RequestMapping("/login")
    @RequestMapping(value = "/test1.do")
    public void test1(Model model, HttpServletRequest request){
    }
    @RequestMapping(value = "/test2.do")
    public void test2(Model model, HttpServletRequest request){
    }

}
