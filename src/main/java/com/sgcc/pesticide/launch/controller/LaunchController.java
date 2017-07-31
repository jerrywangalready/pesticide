package com.sgcc.pesticide.launch.controller;

import com.sgcc.comm.model.Query;
import com.sgcc.comm.util.CommUtil;
import com.sgcc.comm.util.service.CommService;
import com.sgcc.pesticide.create.service.CreationService;
import com.sgcc.pesticide.issuePool.service.IssuePoolService;
import com.sgcc.pesticide.launch.service.LaunchService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 * @create 2017/1/15.
 */
@Controller
@RequestMapping("/launch")
public class LaunchController {

    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    LaunchService launchService;
    @Autowired
    CommService commService;

    @RequestMapping("/init")
    public String initWorkbench(){
        return "launch/launchInit";
    }

    @RequestMapping("/getIssueList")
    public @ResponseBody List<Map<String, Object>> getIssueList(@RequestBody Map<String, String> param){
        return launchService.getIssueList(param);
    }

    /**
     * @Description 修改版本号
     * @author JerryWang
     * @date 2017/6/15 14:11
     * @param param
     * @return
     */
    @RequestMapping("/changeVersionCode")
    public @ResponseBody String changeVersionCode(@RequestBody Map<String, String> param){
        launchService.changeVersionCode(param);
        return "true";
    }

    @RequestMapping("/forLaunch")
    public @ResponseBody String forLaunch(String objectCode, String versionCode){
        try {
            Map<String, String> param = new HashMap<>();
            param.put("objectCode", objectCode);
            param.put("versionCode", versionCode);
            // 获取该版本下所有任务
            List<Map<String, String>> issueList = launchService.getIssueListByVersionCode(param);
            // 修改该版本的状态
            launchService.updateVersionCodeState(param);
            // 修改所有任务的状态
            launchService.updateIssueState(param);
            // 增加上线日志
            for (Map<String, String> map : issueList) {
                commService.insertIssueRecord(map.get("UUID"),"上线","");
            }
            return "true";
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            return "false";
        }
    }

    @RequestMapping("/launchInfo")
    public String launchInfo(){
        return "/launch/launchInfo";
    }

    @RequestMapping("/getLaunchDetail")
    public @ResponseBody List<Map<String, String>> getLaunchDetail(String objectCode, String versionCode){
        Map<String, String> param = new HashMap<>();
        param.put("objectCode", objectCode);
        param.put("versionCode", versionCode);
        return launchService.getLaunchDetail(param);
    }

    @RequestMapping("/checkRole")
    public @ResponseBody String checkRole(String objectCode){
        Map<String, String> param = new HashMap<>();
        param.put("objectCode", objectCode);
        param.put("username", commService.getLoginInfo().getLoginUser());
        return String.valueOf(launchService.checkRole(param));
    }

    @RequestMapping("/getLaunchVersion")
    public @ResponseBody String getLaunchVersion(String obj){
        return launchService.getLaunchVersion(obj);
    }
}
