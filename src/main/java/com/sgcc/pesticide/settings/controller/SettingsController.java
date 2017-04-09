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

    @RequestMapping("/users")
    public String settingsInit() {
        return "/settings/settingsUsersInit";
    }

    @RequestMapping("/objects")
    public String settingsObjectsInit(){
        return "/settings/settingsObjectsInit";
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
     * @return
     * @Description 查询object列表
     * @author 杜成皓
     * @date 2017/1/20 9:06
     */
    @ResponseBody
    @RequestMapping(value = "/queryObject.do", method = RequestMethod.POST)
    public Query queryObject(@RequestBody Map<String, String> param) {
        return usersService.queryObjectList(param);
    }

    /**
     * @Description 保存user
     * @author 杜成皓
     * @date 2017/3/1 22:39
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save.do", method = RequestMethod.POST)
    public String save(@RequestBody Map<String, String> param) {
        String username=param.get("username");
        String todo = param.get("todo");
        String returnValue;
        if("update".equals(todo)){
            usersService.updateTask(param);
            returnValue="success";
        }else{
            if(usersService.checkIsExist(username)){
                usersService.insertTask(param);
                returnValue="success";
            }else{
                returnValue="false";
            }
        }
        return returnValue;
    }

    /**
     * @Description 保存object
     * @author 杜成皓
     * @date 2017/4/7 23:07
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/saveObject.do", method = RequestMethod.POST)
    public String saveObject(@RequestBody Map<String,String> param){
        String objectCode = param.get("object_code");
        String objectName = param.get("object_name");
        String todo = param.get("todo");
        String returnValue;
        if ("update".equals(todo)){
            usersService.updateObject(param);
            returnValue = "success";
        }else{
            if(usersService.checkObjectName(objectName)){
                usersService.insertObject(param);
                returnValue="success";
            }else{
                returnValue="false";
            }
        }
        return returnValue;
    }

    /**
     * @Description 修改页面的初始化方法
     * @author 杜成皓
     * @date 2017/3/2 23:17
     * @param uuid
     * @return
     */
    @RequestMapping(value = "/updateUserInit.do")
    public String updateUserInit(@RequestBody String uuid,@RequestBody String type) {
        return "/settings/settingsUsersUpdate";
    }

    /**
     * @Description 项目修改页面的初始化方法
     * @author 杜成皓
     * @date 2017/4/9 22:49
     * @param uuid
     * @return
     */
    @RequestMapping(value = "updateObjectInit.do")
    public String updateObjectInit(@RequestBody String uuid){
        return "settings/settingsObjectsUpdate";
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

    /**
     * @Description 通过uuid查询一个objec对象
     * @author 杜成皓
     * @date 2017/4/9 23:08
     * @param uuid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "queryObjectByUUID.do")
    public Objects queryUsersByUUID(String uuid){
        Objects objects = usersService.queryObjectByUUID(uuid);
        return objects;
    }
    /**
     * @Description 新增和修改用户页面的初始化方法
     * @author 杜成皓
     * @date 2017/3/2 23:17
     * @param
     * @return
     */
    @RequestMapping(value = "/addInit.do")
    public String addInit() {
        return "/settings/settingsUsersInsert";
    }

    /**
     * @Description 新增和修改项目页面的初始化方法
     * @author 杜成皓
     * @date 2017/4/7 22:19
     * @return
     */
    @RequestMapping(value = "/addObjectInit.do")
    public String addObjectInit(){
        return "/settings/settingsObjectsInsert";
    }

    /**
     * @Description 删除一行user数据
     * @author 杜成皓
     * @date 2017/3/15 23:05
     * @param uuid
     * @return
     */
    @RequestMapping(value = "/deleteUser.do")
    public String deleteUser(String uuid){
        return usersService.deleteUser(uuid);
    }

    /**
     * @Description 删除一条object数据
     * @author 杜成皓
     * @date 2017/4/7 22:48
     * @param uuid
     * @return
     */
    @RequestMapping(value = "/deleteObject.do")
    public String deleteObject(String uuid){
        return usersService.deleteObject(uuid);
    }
}