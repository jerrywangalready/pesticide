package com.sgcc.pesticide.index.controller;

import com.sgcc.comm.util.service.CommService;
import com.sgcc.pesticide.index.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author jerrywang
 * @create 2017/1/10.
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    @Autowired
    IndexService indexService;
    @Autowired
    CommService commService;

    /**
     * @Description 首页初始化
     * @author JerryWang
     * @date 2017/1/16 11:18
     * @return
     */
    @RequestMapping(value = "/index.do")
    public String index(){
        return "/index/index";
    }

    /**
     * @Description 退出功能
     * @author JerryWang
     * @date 2017/1/16 11:17
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/logout.do")
    public String logout(HttpServletRequest request){
        request.getSession().invalidate();
        return "true";
    }

    /**
     * @Description 根据用户名获取所在项目信息
     * @author JerryWang
     * @date 2017/1/22 20:19
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getObjects", method = RequestMethod.POST)
    public List<Map<String, String>> getObjectsByUser(HttpServletRequest request){
        String username = request.getParameter("username");
        return indexService.getObjectsByUser(username);
    }

    @RequestMapping("/countNew")
    public @ResponseBody String countNew(String obj, String dt){
        Map<String, String> param = new HashMap<>();
        param.put("obj", obj);
        param.put("dt", dt);
        param.put("username", commService.getLoginInfo().getLoginUser());
        return indexService.countNew(param);
    }

    @RequestMapping("/getNew")
    public @ResponseBody Map<String, Object> getNew(@RequestBody Map<String, String> param){
        param.put("username", commService.getLoginInfo().getLoginUser());
        Map<String, Object> map = new HashMap<>();
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("dt", sdf.format(now));
        map.put("query", indexService.getNew(param));
        return map;
    }

}
