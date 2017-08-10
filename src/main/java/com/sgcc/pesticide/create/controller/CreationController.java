package com.sgcc.pesticide.create.controller;

import com.alibaba.fastjson.JSON;
import com.sgcc.comm.util.CommUtil;
import com.sgcc.comm.util.service.CommService;
import com.sgcc.pesticide.create.service.CreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
    @Autowired
    CommService commService;

    @RequestMapping("/init")
    public String creationInit(){
        return "/creation/creationInit";
    }

    @ResponseBody
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(@RequestBody Map<String, String> param){

        if("commit".equals(param.get("todo"))){
            param.put("state","1");
        }else {
            param.put("state","0");
        }

        if("T".equals(param.get("issueType"))){
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

    @RequestMapping("/addVersion")
    public @ResponseBody String addVersion(String versionCode, String publishDate, String objectCode){
        Map<String, String> param = new HashMap<>();
        param.put("versionCode", versionCode);
        param.put("publishDate", publishDate);
        param.put("objectCode", objectCode);
        param.put("uuid", CommUtil.getUUID());
        return creationService.addVersion(param);
    }

    @RequestMapping("/uploadFile")
    public @ResponseBody String uploadFile(@RequestParam MultipartFile[] attachment, String businessId){
        Map<String, Object> returnMap = new HashMap<>();
        try {
            for (MultipartFile file : attachment) {
                // 保存附件
                String uuid = CommUtil.getUUID();
                String path = CommUtil.getInstance().PROPERTIES.get("attachment-path");
                File uploadFile = new File(path + uuid);
                file.transferTo(uploadFile);

                // 保存数据
                Map<String, String> param = new HashMap<>();
                param.put("uuid", uuid);
                param.put("businessId", businessId);
                param.put("fileName", file.getOriginalFilename());
                param.put("fileType", file.getContentType());
                param.put("fileSize", String.valueOf(file.getSize()));
                param.put("createUser", commService.getLoginInfo().getLoginUser());

                Map<String, String> iconMap = new HashMap<>();
                iconMap.put("application/msword","doc.png");
                iconMap.put("image/png","png.png");
                iconMap.put("image/jpeg","jpg.png");
                iconMap.put("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet","xls.png");
                iconMap.put("application/zip","zip.png");
                iconMap.put("application/octet-stream","exe.png");
                iconMap.put("video/mp4","mp4.png");
                iconMap.put("text/rtf","rtf.png");
                iconMap.put("text/plain","txt.png");
                iconMap.put("application/x-rar","zip2.png");

                Map<String, String> extra = new HashMap<>();
                extra.put("uuid", uuid);
                List<Map<String, Object>> list = new ArrayList<>();
                Map<String, Object> initialPreviewConfig = new HashMap<>();
                initialPreviewConfig.put("caption",file.getOriginalFilename());
                initialPreviewConfig.put("width","20px");
                initialPreviewConfig.put("size",String.valueOf(file.getSize()));
                initialPreviewConfig.put("url","/pesticide/creation/deleteAttachment.do");
                initialPreviewConfig.put("extra",extra);
                initialPreviewConfig.put("key",1);
//                initialPreviewConfig.put("dropZoneEnabled","false");
//                initialPreviewConfig.put("dropZoneTitleClass","hide");
                initialPreviewConfig.put("state","1");
                initialPreviewConfig.put("showZoom",false);
                initialPreviewConfig.put("showDrag",false);
                list.add(initialPreviewConfig);

                List<String> l = new ArrayList<>();
                if(iconMap.containsKey(file.getContentType())){

                    l.add("/pesticide/comm/image/icon/"+iconMap.get(file.getContentType()));
                }else {
                    l.add("/pesticide/comm/image/icon/unknown.png");

                }

                returnMap.put("initialPreview",l);
                returnMap.put("initialPreviewConfig", list);
                List<Map<String, String>> li = new ArrayList<>();
                Map<String, String> par = new HashMap<>();
                par.put("{CUSTOM_TAG_NEW}","<i class=\"glyphicon glyphicon-ok-sign text-success\"></i>");
                par.put("{CUSTOM_TAG_INIT}","<i class=\"glyphicon glyphicon-ok-sign text-success\"></i>");
                li.add(par);
                returnMap.put("initialPreviewThumbTags", par);

                creationService.saveAttachment(param);
//                list.add(uuid);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(JSON.toJSONString(returnMap));
        return JSON.toJSONString(returnMap);
    }

    /**
     * @Description 删除附件
     * @author JerryWang
     * @date 2017/7/10 08:46
     * @param request
     * @return
     */
    @RequestMapping("/deleteAttachment")
    public @ResponseBody String deleteAttachment(HttpServletRequest request){
        String uuid = request.getParameter("uuid");
        // 删除服务器上的文件
        CommUtil.deleteFile(CommUtil.getInstance().PROPERTIES.get("attachment-path")+uuid);
        // 删除数据库中的数据
        creationService.deleteAttachment(uuid);

        return "true";
    }

}
