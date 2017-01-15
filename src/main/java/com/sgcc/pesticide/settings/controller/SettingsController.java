package com.sgcc.pesticide.settings.controller;

import com.sgcc.pesticide.settings.model.Users;
import com.sgcc.pesticide.settings.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/settings")
public class SettingsController {
    @Autowired
    UsersService usersService;

    @RequestMapping("/init")
    public String settingsInit(){
        return "/settings/settingsInit";
    }

    @ResponseBody
    @RequestMapping(value = "/queryUsers.do",method = RequestMethod.POST)
    public List<Users> queryUsers(HttpServletRequest request, HttpServletResponse response){
        System.out.println("aaaaa");
        List<Users> list = usersService.queryUsersList();
        return list;
//        return "aaa";
    }
}
