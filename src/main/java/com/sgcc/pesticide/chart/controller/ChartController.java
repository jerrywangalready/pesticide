package com.sgcc.pesticide.chart.controller;

import com.sgcc.pesticide.chart.service.ChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 * @create 2017/5/17.
 */
@Controller
@RequestMapping("/chart")
public class ChartController {

    @Autowired
    ChartService chartService;

    @RequestMapping("/init")
    public String init(){
        return "/chart/chartInit";
    }

    @RequestMapping("/getVersion")
    public @ResponseBody List<Map<String, String>> getVersion(String objectCode, String startDate, String finishDate){
        return chartService.getVersion(objectCode, startDate, finishDate);
    }

    @RequestMapping("/getDetail")
    public @ResponseBody List<Map<String, String>> getDetail(String objectCode, String version){
        return chartService.getDetail(objectCode, version);
    }
}
