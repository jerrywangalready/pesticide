package com.sgcc.pesticide.issuePool.controller;

import com.sgcc.comm.model.Query;
import com.sgcc.comm.util.CommUtil;
import com.sgcc.comm.util.service.CommService;
import com.sgcc.pesticide.issuePool.service.IssuePoolService;
import com.sgcc.pesticide.workbench.service.WorkbenchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * @author jerrywang
 * @create 2017/1/15.
 */
@Controller
@RequestMapping("/issuePool")
public class IssuePoolController {

    @Autowired
    IssuePoolService issuePoolService;
    @Autowired
    CommService commService;

    @RequestMapping("/init")
    public String initWorkbench(){
        return "issuePool/issuePoolInit";
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
        return issuePoolService.getIssueList(param);
    }

    @RequestMapping("/detail")
    public String initDetailPage(){
        return "/issuePool/issuePoolDetail";
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
        return issuePoolService.getDetail(uuid, type);
    }

    @RequestMapping("/update")
    public String updateInit(){
        return "/issuePool/issuePoolUpdate";
    }


    @RequestMapping(value = "/save")
    public @ResponseBody String save(@RequestBody Map<String, String> param){

        String description = CommUtil.saveImage(param.get("description"));

        param.put("description",description);

        if("commit".equals(param.get("todo"))){
            param.put("state","1");
        }else {
            param.put("state","0");
        }

        commService.insertIssueRecord(param.get("uuid"),"修改 了任务","");

        if("T".equals(param.get("issueType"))){
            return issuePoolService.saveTask(param);
        }else {
            return issuePoolService.saveBug(param);
        }

    }

}
