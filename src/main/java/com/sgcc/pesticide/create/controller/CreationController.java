package com.sgcc.pesticide.create.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jerrywang
 * @create 2017/1/15.
 */
@Controller
@RequestMapping("/creation")
public class CreationController {

    @RequestMapping("/creation")
    public String creationInit(){
        return "/creation/creation";
    }

}
