package com.sgcc.pesticide.create.controller;

import com.sgcc.comm.util.CommUtil;
import com.sgcc.pesticide.create.service.CreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 * @create 2017/1/15.
 */
@Controller
@RequestMapping("/creation")
public class CreationController {
    @Autowired
    CreationService creationService;

    @RequestMapping("/init")
    public String creationInit(){
        return "/creation/creationInit";
    }

    @ResponseBody
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(@RequestBody Map<String, String> param){

        String des = param.get("description");
        StringBuffer desTemp = new StringBuffer();

        int end;

        while (des.length() > 0){
            end = des.indexOf("<img");
            if(end >= 0){
                // 将img前的代码保存
                desTemp.append(des.substring(0,end));
                // 处理img
                des = des.substring(end,des.length());

                end = des.indexOf("/>");
                String imgData = des.substring(0,end).replace("<img src=\"data:image/png;base64,","").replace("\" />","");
                desTemp.append("<img src='"+CommUtil.getInstance().saveImage(imgData,CommUtil.getResourceProperty("upload-path"))+"' />");
                // 返回新img
                des = des.substring(end,des.length());
            }else {
                desTemp.append(des);
                break;
            }
        }

        param.put("description",desTemp.toString());

        if(!param.containsKey("uuid") || param.get("uuid").isEmpty()){
            return creationService.insertTask(param);
        }else {
            creationService.updateTask(param);
            return "";
        }

    }

    /**
     * @Description 获取关联信息
     * @author JerryWang
     * @date 2017/1/29 17:22
     * @param param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getLinkInfo",method = RequestMethod.POST)
    public List<Map<String, String>> getLinkInfo(@RequestBody Map<String, String> param){
        String username = CommUtil.getLoginInfo().getLogin_user();
        param.put("login_user",username);
        return creationService.getLinkInfo(param);
    }

}
