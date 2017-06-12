package com.sgcc.comm.util.controller;

import com.sgcc.comm.util.CommUtil;
import com.sgcc.comm.util.service.CommService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 * @create 2017/6/7.
 */
@Component
public class StartInit {

    @Autowired
    CommService commService;

    @Scheduled(fixedRate = 1000*60*60*2)
    public void propertiesInit(){
        System.out.println("::PROPERTIES was updated!!");
        List<Map<String, String>> list = commService.getProperties();
        for (Map<String, String> m:list){
            CommUtil.getInstance().PROPERTIES.put(m.get("p_key"), m.get("p_value"));
        }
    }
}
