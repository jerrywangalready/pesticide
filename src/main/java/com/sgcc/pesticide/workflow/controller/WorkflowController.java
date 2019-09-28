package com.sgcc.pesticide.workflow.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 * @create 2018/11/25.
 */
@Controller
@RequestMapping("/workflow")
public class WorkflowController {

    @ResponseBody
    @RequestMapping("/task")
    public Map<String, Object> task(String templateId, String businessId) {
        // 判断是否存在此流程
        boolean existTask = false;
        if (!existTask) {
            // 创建工作流任务

        }


        Map<String, Object> map = new HashMap<>();
        map.put("data", new ArrayList<>());
        return map;
    }

    @RequestMapping("/initChooseApproverPage")
    public String initChooseApproverPage() {
        return "/workflow/workflow_chooseApprover_page";
    }

    @RequestMapping("/getApprover")
    public List<Map<String, String>> getApprover(String templateId, String businessId) {
        // 获取下级审批人
        return null;
    }
}
