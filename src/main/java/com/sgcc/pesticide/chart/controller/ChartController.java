package com.sgcc.pesticide.chart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jerrywang
 * @create 2017/5/17.
 */
@Controller
@RequestMapping("/chart")
public class ChartController {

    @RequestMapping("/init")
    public String init(){
        return "/chart/chartInit";
    }
}
