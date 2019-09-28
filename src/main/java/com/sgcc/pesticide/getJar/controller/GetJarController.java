package com.sgcc.pesticide.getJar.controller;

import com.sgcc.pesticide.getJar.service.GetJarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 * @create 2019-01-23.
 */
@Controller
@RequestMapping("/getJar")
public class GetJarController {

    @Autowired
    GetJarService getJarService;

    /**
     * @Description 抽包页初始化
     * @return
     * @author JerryWang
     * @date 2019-01-23 10:48
     */
    @RequestMapping("/init")
    public String getJarPageInit() {
        return "getJar/getJarPage";
    }

    /**
     * @Description 查询jar包list
     * @return
     * @author JerryWang
     * @date 2019-01-23 11:51
     */
    // 读取208环境jar,按照时间倒叙排列
    @RequestMapping("/getJarList")
    public @ResponseBody List<Map<String, String>> getJarList(String scope) {

        return getJarService.getJarList();
    }

    // 根据选择的jar,从208和217读取,并分别放置到相应路径下,并修改配置信息
}
