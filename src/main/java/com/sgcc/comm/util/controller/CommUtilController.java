package com.sgcc.comm.util.controller;

import com.sgcc.comm.util.CommUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author jerrywang
 * @create 2017/7/9.
 */
@Controller
@RequestMapping("/CommUtil")
public class CommUtilController {

    @RequestMapping("/getUUID")
    public @ResponseBody String getUUID() {
        return CommUtil.getUUID();
    }
}
