package com.sgcc.pesticide.workbench.controller;

import com.sgcc.comm.model.Query;
import com.sgcc.pesticide.workbench.service.WorkbenchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
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

    /**
     * @Description 获取项目下的所有模块
     * @author JerryWang
     * @date 2017/4/18 22:32
     * @param objectId
     * @return
     */
    @RequestMapping("/getModel")
    public @ResponseBody List<String> getModel(String objectId){
        return workbenchService.getModel(objectId);
    }

    /**
     * @Description 送测
     * @author JerryWang
     * @date 2017/5/4 23:11
     * @param param
     * @return
     */
    @RequestMapping("/push")
    public @ResponseBody String push(@RequestBody Map<String, String> param){
        return workbenchService.push(param);
    }

    /**
     * @Description 选择模块页面初始化
     * @author JerryWang
     * @date 2017/5/4 23:11
     * @param state
     * @return
     */
    @RequestMapping("/choseModelInit")
    public ModelAndView choseModelInit(String state){
        return new ModelAndView("workbench/workbenchChoseModel","state",state);
    }

    /**
     * @Description 修改负责人页面初始化
     * @author JerryWang
     * @date 2017/5/4 23:12
     * @return
     */
    @RequestMapping("/changePrincipalInit")
    public String changePrincipalInit(){
        return "workbench/workbenchChangePrincipal";
    }

    /**
     * @Description 修改负责人
     * @author JerryWang
     * @date 2017/5/4 23:16
     * @param param
     * @return
     */
    @RequestMapping("/changePrincipal")
    public @ResponseBody String changePrincipal(@RequestBody Map<String, String> param){
        return workbenchService.changePrincipal(param);
    }

    /**
     * @Description 退回页面初始化
     * @author JerryWang
     * @date 2017/5/4 23:17
     * @return
     */
    @RequestMapping("/rejectInit")
    public String rejectInit(){
        return "workbench/workbenchReject";
    }

    /**
     * @Description 退回
     * @author JerryWang
     * @date 2017/5/4 23:17
     * @param param
     * @return
     */
    @RequestMapping("/reject")
    public @ResponseBody String reject(@RequestBody Map<String, String> param){
        return workbenchService.reject(param);
    }

    @RequestMapping("/getRecord")
    public @ResponseBody List<Map<String, String>> getRecord(String businessId) {
        return workbenchService.getRecord(businessId);
    }
}
