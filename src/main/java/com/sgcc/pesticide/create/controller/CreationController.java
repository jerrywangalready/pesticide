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
import java.util.HashMap;
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
                desTemp.append("<img style='width: 20%;height: auto;' src='"+CommUtil.getInstance().saveImage(imgData,CommUtil.getResourceProperty("upload-path"))+"' ");
                // 返回新img
                des = des.substring(end,des.length());
            }else {
                desTemp.append(des);
                break;
            }
        }

        param.put("description",desTemp.toString());

        if("commit".equals(param.get("todo"))){
            param.put("state","1");
        }else {
            param.put("state","0");
        }

        if("1".equals(param.get("typeCode"))){
            return creationService.saveTask(param);
        }else {
            return creationService.saveBug(param);
        }

    }

    /**
     * @Description 根据任务编号搜索任务
     * @author JerryWang
     * @date 2017/4/2 10:39
     * @param code
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "searchTask",method = RequestMethod.POST)
    public List<Map<String, String>> searchTask(String code,String objectCode){
        Map<String, String> param = new HashMap<>();
        param.put("code",code.toUpperCase());
        param.put("objectCode",objectCode);
        return creationService.searchTask(param);
    }
}
