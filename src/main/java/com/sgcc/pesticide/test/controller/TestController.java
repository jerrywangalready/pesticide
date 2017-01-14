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
        String s1 = "Y";
        String s2 = "S1";
        User r = userService.getUser("2");
        request.setAttribute("user", r.getUsername());
    }
    @RequestMapping(value = "/test2.do")
    public void test2(Model model, HttpServletRequest request){
        User r = userService.getUser("2");
        request.setAttribute("user", r.getUsername());
    }

    @ResponseBody
    @RequestMapping(value = "/a.do",method= RequestMethod.POST)
    public List<Map<String,Object>> listAsParam(@RequestBody List<Map<String,Object>> list){
        return list;
    }

    @ResponseBody
    @RequestMapping(value = "/doLogin.do",method= RequestMethod.POST)
    public String doLogin(HttpServletRequest request, HttpServletResponse httpServletResponse){
        String username = request.getParameter("username") == null?"":request.getParameter("username").toString();
        String password = request.getParameter("password") == null?"":request.getParameter("password").toString();
        // 校验账号密码是否正确
        UserToken user = userService.checkUser(username,password);
        if (user == null){
            return "false";
        }else {
            UserToken userToken = new UserToken();
            HttpSession session = request.getSession();
            session.setAttribute("userToken",userToken);
            session.setMaxInactiveInterval(1800);
            return "true";
        }

    }
}
