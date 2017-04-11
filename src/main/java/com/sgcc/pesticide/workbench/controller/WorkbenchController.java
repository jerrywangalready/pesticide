package com.sgcc.pesticide.workbench.controller;

import com.sgcc.comm.model.Query;
import com.sgcc.pesticide.workbench.service.WorkbenchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @author jerrywang
 * @create 2017/1/15.
 */
@Controller
@RequestMapping("/workbench")
public class WorkbenchController {

    @Autowired
    WorkbenchService workbenchService;

    @RequestMapping("/init")
    public String initWorkbench(){
        return "workbench/workbenchList";
    }

    /**
     * @Description 获取问题列表
     * @author JerryWang
     * @date 2017/4/10 23:03
     * @param param
     * @return
     */
    @RequestMapping("/getIssueList")
    public @ResponseBody Query getIssueList(@RequestBody Map<String, String> param){
        return workbenchService.getIssueList(param);
    }

    @RequestMapping("/detail")
    public String initDetailPage(){
        return "/workbench/workbenchDetail";
    }

    /**
     * @Description 获取详细信息
     * @author JerryWang
     * @date 2017/4/10 21:46
     * @param uuid
     * @param type
     * @return
     */
    @RequestMapping("/getDetail")
    public @ResponseBody Map<String, String> detail(String uuid, String type){
        return workbenchService.getDetail(uuid, type);
    }
}
