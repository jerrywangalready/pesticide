package com.sgcc.pesticide.settings.controller;

import com.sgcc.comm.model.Query;
import com.sgcc.pesticide.settings.model.Objects;
import com.sgcc.pesticide.settings.model.Users;
import com.sgcc.pesticide.settings.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.registry.infomodel.User;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/settings")
public class SettingsController {
    @Autowired
    SettingsService usersService;

    @RequestMapping("/init")
    public String settingsInit() {
        return "/settings/settingsInit";
    }

    /**
     * @return
     * @Description 查询users列表
     * @author 杜成皓
     * @date 2017/1/20 9:40
     */
    @ResponseBody
    @RequestMapping(value = "/queryUsers.do", method = RequestMethod.POST)
    public Query queryUsers(@RequestBody Map<String, String> param) {
        return usersService.queryUsersList(param);
    }


    /**
     * @param request
     * @param response
     * @return
     * @Description 查询object列表
     * @author 杜成皓
     * @date 2017/1/20 9:06
     */
    @ResponseBody
    @RequestMapping(value = "/queryObject.do", method = RequestMethod.POST)
    public List<Objects> queryObject(HttpServletRequest request, HttpServletResponse response) {
        List<Objects> list = usersService.queryObjectList();
        return list;
    }

    /**
     * @Description 保存
     * @author 杜成皓
     * @date 2017/3/1 22:39
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save.do", method = RequestMethod.POST)
    public String save(@RequestBody Map<String, String> param) {
        String username=param.get("username");
        String returnValue;
        if (usersService.checkIsExist(username)){
            usersService.insertTask(param);
            returnValue="success";
        }else{
            returnValue="fail";
        }
        return returnValue;
    }

    /**
     * @Description 新增和修改页面的初始化方法
     * @author 杜成皓
     * @date 2017/3/2 23:17
     * @param uuid
     * @return
     */
    @RequestMapping(value = "/addOrUpdateInit.do")
    public String addOrUpdateInit(@RequestBody String uuid,@RequestBody String type) {
        return "/settings/settingsUpdate";
    }

    /**
     * @Description 根据uuid查询一条user对象
     * @author 杜成皓
     * @date 2017/3/6 22:10
     * @param uuid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryUserByUUID.do")
    public Users queryUserByUUID(String uuid){
        Users users = usersService.queryUserByUUID(uuid);
        return users;
    }
}