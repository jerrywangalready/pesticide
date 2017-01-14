package com.sgcc.pesticide.settings.controller;

import com.sgcc.pesticide.settings.model.Users;
import com.sgcc.pesticide.settings.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/settings")
public class SettingsController {
    @Autowired
    UsersService usersService;

    @RequestMapping(value = "/queryUsers.do")
    public void queryUsers(Model model, HttpServletRequest request){
        System.out.println("aaaaa");
//        List<Users> list = usersService.queryUsersList();
//        return list;
    }
}
