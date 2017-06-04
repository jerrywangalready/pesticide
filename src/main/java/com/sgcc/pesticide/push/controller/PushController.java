package com.sgcc.pesticide.push.controller;

import com.sgcc.comm.model.Query;
import com.sgcc.comm.util.CommUtil;
import com.sgcc.comm.util.controller.BaseController;
import com.sgcc.comm.util.service.CommService;
import com.sgcc.pesticide.login.model.UserToken;
import com.sgcc.pesticide.push.service.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.util.*;

/**
 * Created by DCH on 2017/5/2.
 */
@Controller
@RequestMapping("/push")
public class PushController extends BaseController{

    @Autowired
    PushService pushService;
    @Autowired
    CommService commService;

    @RequestMapping("/init")
    public String initPush(){
        return "push/pushInit";
    }

    /**
     * @Description 查询推送列表
     * @author 杜成皓
     * @date 2017/5/3 23:19
     * @param param
     * @return
     */
    @RequestMapping("/getPushList")
    public @ResponseBody List<Map<String, String>> getPushList(@RequestBody Map<String, String> param){
        param.put("principal", commService.getLoginInfo().getLoginUser());
        return pushService.getPushList(param);
    }

    /**
     * @Description 查询二级列表
     * @author 杜成皓
     * @date 2017/5/11 23:23
     * @param param
     * @return
     */
    @RequestMapping("/getPushDetail")
    public @ResponseBody List<Map<String,String>> getPushDetail(@RequestBody Map<String, String> param){
        return pushService.getPushDetail(param);
    }

    /**
     * @Description 发布
     * @author JerryWang
     * @date 2017/6/1 17:15
     * @param param
     * @return
     */
    @RequestMapping("/publish")
    public @ResponseBody String publish(@RequestBody Map<String, Object> param){
        String[] modelCodesArr = param.get("modelCodes").toString().split(",");
        List<String> l = Arrays.asList(modelCodesArr);
        param.put("l",l);
        // 获取模块下的任务uuid
        List<Map<String, String>> list = pushService.getTaskUuid(param);
        boolean result = pushService.publish(param);
        if (result) {
            for (Map<String, String> map : list) {
                // 写操作日志
                commService.insertIssueRecord(map.get("uuid"),"发布 了任务","");
            }
        }
        return String.valueOf(result);
    }

    @RequestMapping("/checkRole")
    public @ResponseBody String checkRole(){
        System.out.println(commService.getLoginInfo().getLoginUser());
        return pushService.checkRole(commService.getLoginInfo().getLoginUser());
    }


}
