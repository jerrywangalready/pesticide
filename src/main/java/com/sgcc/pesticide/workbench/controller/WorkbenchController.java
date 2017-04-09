package com.sgcc.pesticide.workbench.controller;

import com.sgcc.comm.model.Query;
import com.sgcc.pesticide.workbench.service.WorkbenchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        return "/workbench/workbenchInit";
    }

    @RequestMapping("/getIssueList")
    public @ResponseBody Query getIssueList(@RequestBody Map<String, String> param){
        return workbenchService.getIssueList(param);
    }
}
