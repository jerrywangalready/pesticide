package com.sgcc.pesticide.settings.controller;

import com.sgcc.pesticide.settings.model.Objects;
import com.sgcc.pesticide.settings.model.Users;
import com.sgcc.pesticide.settings.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    SettingsService usersService;

    @RequestMapping("/init")
    public String settingsInit(){
        return "/settings/settingsInit";
    }

    /**
     * @Description 查询users列表
     * @author 杜成皓
     * @date 2017/1/20 9:40
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryUsers.do",method = RequestMethod.POST)
    public List<Users> queryUsers(HttpServletRequest request, HttpServletResponse response){
        List<Users> list = usersService.queryUsersList();
        return list;
    }

    /**
     * @Description 查询object列表
     * @author 杜成皓
     * @date 2017/1/20 9:06
     * @param request
     * @param response
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/queryObject.do",method = RequestMethod.POST)
    public List<Objects> queryObject(HttpServletRequest request, HttpServletResponse response){
        List<Objects> list = usersService.queryObjectList();
        return list;
    }
}
