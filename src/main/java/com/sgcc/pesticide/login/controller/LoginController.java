package com.sgcc.pesticide.login.controller;

import com.sgcc.pesticide.login.model.User;
import com.sgcc.pesticide.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 * @create 2017/1/7.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public String index(HttpServletRequest request){
        User r = userService.getUser("2");
        request.setAttribute("user", r.getUsername());
        return "/login/login";
    }

    @ResponseBody
    @RequestMapping(value = "/a.do",method= RequestMethod.POST)
    public List<Map<String,Object>> listAsParam(@RequestBody List<Map<String,Object>> list){
        return list;
    }
}
