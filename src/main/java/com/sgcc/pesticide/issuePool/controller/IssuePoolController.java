package com.sgcc.pesticide.issuePool.controller;

import com.github.pagehelper.PageInfo;
import com.sgcc.comm.util.CommUtil;
import com.sgcc.comm.util.ExcelHelper;
import com.sgcc.comm.util.service.CommService;
import com.sgcc.pesticide.create.service.CreationService;
import com.sgcc.pesticide.issuePool.service.IssuePoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 * @create 2017/1/15.
 */
@Controller
@RequestMapping("/issuePool")
public class IssuePoolController {

    @Autowired
    IssuePoolService issuePoolService;
    @Autowired
    CreationService creationService;
    @Autowired
    CommService commService;

    @RequestMapping("/init")
    public String initWorkbench() {
        return "issuePool/issuePoolInit";
    }

    /**
     * @param param
     * @return
     * @Description 获取问题列表
     * @author JerryWang
     * @date 2017/4/10 23:03
     */
    @RequestMapping("/getIssueList")
    public @ResponseBody
    PageInfo getIssueList(HttpServletRequest request, @RequestBody Map<String, String> param) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            System.out.println("name=" + cookie.getName() + ",value=" + cookie.getValue());
        }
        return issuePoolService.getIssueList(param);
    }

    @RequestMapping("/detail")
    public String initDetailPage() {
        return "/issuePool/issuePoolDetail";
    }

    /**
     * @param uuid
     * @param type
     * @return
     * @Description 获取详细信息
     * @author JerryWang
     * @date 2017/4/10 21:46
     */
    @RequestMapping("/getDetail")
    public @ResponseBody
    Map<String, String> detail(String uuid, String type) {
        return issuePoolService.getDetail(uuid, type);
    }

    @RequestMapping("/update")
    public String updateInit() {
        return "/issuePool/issuePoolUpdate";
    }


    @RequestMapping(value = "/save")
    public @ResponseBody
    String save(@RequestBody Map<String, String> param) {

        if ("commit".equals(param.get("todo"))) {
            param.put("state", "1");
        } else {
            param.put("state", "0");
        }

        commService.insertIssueRecord(param.get("uuid"), "修改 了任务", "");

        if ("T".equals(param.get("issueType"))) {
            return issuePoolService.saveTask(param);
        } else {
            return issuePoolService.saveBug(param);
        }

    }

    @RequestMapping("/getParentIssue")
    public @ResponseBody
    Map<String, String> getParentIssue(String issueCode) {
        return issuePoolService.getParentIssue(issueCode);
    }


    /**
     * @param code
     * @return
     * @Description 根据任务编号搜索任务
     * @author JerryWang
     * @date 2017/4/2 10:39
     */
    @ResponseBody
    @RequestMapping(value = "searchTask", method = RequestMethod.POST)
    public List<Map<String, String>> searchTask(String code, String objectCode) {
        Map<String, String> param = new HashMap<>();
        param.put("code", code.toUpperCase());
        param.put("objectCode", objectCode);
        return creationService.searchTask(param);
    }

    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response) {

        Map<String, String> param = new HashMap<>();
        param.put("object_code", request.getParameter("object_code"));
        param.put("issue_code", request.getParameter("issue_code"));
        param.put("issue_name", request.getParameter("issue_name"));
        param.put("query_issue_type", request.getParameter("query_issue_type"));
        param.put("query_state", request.getParameter("query_state"));
        param.put("query_create_user", request.getParameter("query_create_user"));
        param.put("query_principal", request.getParameter("query_principal"));

        List<Map<String, String>> list = issuePoolService.getAllIssueList(param);

        Object[][] title = {
                {"序号", "", 20, "center"},
                {"编号", "CODE", 38, "center"},
                {"标题", "TITLE", 168, "left"},
                {"状态", "STATE_VALUE", 38, "center"},
                {"模块", "MODEL_NAME", 38, "center"},
                {"优先级", "PRIORITY_VALUE", 38, "center"},
                {"问题级别", "BUG_LEVEL", 38, "right"},
                {"发布者", "NICKNAME", 38, "center"},
                {"接收者", "PRINCIPAL_NAME", 40, "center"}
        };

        ExcelHelper.export("测试一下", title, list);

        Object[][][] title1 = {{
                {"序号", "index", 2000, "center"},
                {"编号", "CODE", 3800, "center"},
                {"标题", "TITLE", 3800, "left"},
                {"状态", "STATE_VALUE", 3800, "center"},
                {"模块", "MODEL_NAME", 3800, "center"},
                {"优先级", "PRIORITY_VALUE", 3800, "center"},
                {"问题级别", "BUG_LEVEL", 3800, "right"},
                {"发布者", "NICKNAME", 3800, "center"},
                {"接收者", "PRINCIPAL_NAME", 4000, "center"}}
        };


    }

    @RequestMapping("/getAttachment")
    public @ResponseBody
    List<Map<String, String>> getAttachment(String businessId) {
        Map<String, String> iconMap = new HashMap<>();
        iconMap.put("application/msword", "doc.png");
        iconMap.put("image/png", "png.png");
        iconMap.put("image/jpeg", "jpg.png");
        iconMap.put("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", "xls.png");
        iconMap.put("application/zip", "zip.png");
        iconMap.put("application/octet-stream", "exe.png");
        iconMap.put("video/mp4", "mp4.png");
        iconMap.put("text/rtf", "rtf.png");
        iconMap.put("text/plain", "txt.png");
        iconMap.put("application/x-rar", "zip2.png");

        List<Map<String, String>> list = issuePoolService.getAttachment(businessId);
        for (int i = 0; i < list.size(); i++) {
            if (iconMap.containsKey(list.get(i).get("file_type"))) {
                list.get(i).put("image", iconMap.get(list.get(i).get("file_type")));
            } else {
                list.get(i).put("image", "unknown.png");

            }
        }
        return list;
    }

    @RequestMapping("/checkTester")
    public @ResponseBody
    String checkTester(String objectCode, String username) {
        return issuePoolService.checkTester(objectCode, username);
    }
}
