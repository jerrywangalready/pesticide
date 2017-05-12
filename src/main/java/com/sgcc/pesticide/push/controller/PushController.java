package com.sgcc.pesticide.push.controller;

import com.sgcc.comm.model.Query;
import com.sgcc.pesticide.push.service.PushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * Created by DCH on 2017/5/2.
 */
@Controller
@RequestMapping("/push")
public class PushController {

    @Autowired
    PushService pushService;

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
    public @ResponseBody Query getPushList(@RequestBody Map<String, String> param){
        return pushService.getPushList(param);
    }

    /**
     * @Description 查询二级列表
     * @author 杜成皓
     * @date 2017/5/11 23:23
     * @param modelCode
     * @return
     */
    @RequestMapping("/getPushDetail")
    public @ResponseBody
    List<Map<String,String>> getPushDetail(String modelCode){
        return pushService.getPushDetail(modelCode);
    }
}
