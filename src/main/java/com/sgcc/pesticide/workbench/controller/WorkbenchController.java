package com.sgcc.pesticide.workbench.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jerrywang
 * @create 2017/1/15.
 */
@Controller
@RequestMapping("/workbench")
public class WorkbenchController {

    @RequestMapping("/init")
    public String initWorkbench(){
        return "/workbench/workbenchInit";
    }
}
