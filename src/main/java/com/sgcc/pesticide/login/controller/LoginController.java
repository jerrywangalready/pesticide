package com.sgcc.pesticide.login.controller;

import com.sgcc.comm.util.CommUtil;
import com.sgcc.pesticide.login.model.UserToken;
import com.sgcc.pesticide.login.service.LoginService;
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
@RequestMapping("/login")
public class LoginController  {

    @Autowired
    LoginService loginService;

    @RequestMapping(value = "/login.do")
    public void index(Model model, HttpServletRequest request){
    }

    @ResponseBody
    @RequestMapping(value = "/doLogin.do",method= RequestMethod.POST)
    public String doLogin(HttpServletRequest request, HttpServletResponse httpServletResponse){
        String username = request.getParameter("username") == null?"":request.getParameter("username").toString();
        String password = request.getParameter("password") == null?"":request.getParameter("password").toString();
        // 校验账号密码是否正确
        UserToken userToken = loginService.checkUser(username,password);
        if (userToken == null){
            return "false";
        }else {
            HttpSession session = request.getSession();
            session.setAttribute("userToken",userToken);
            session.setMaxInactiveInterval(Integer.parseInt(CommUtil.getResourceProperty("session-time").toString()));

//            CommUtil.getLoginInfo().setLoginUser(userToken.getUsername());
//            CommUtil.getLoginInfo().setLoginNickname(userToken.getNickname());

            return "true";
        }

    }

}
