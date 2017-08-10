package com.sgcc.pesticide.workbench.controller;

import com.sgcc.comm.model.Query;
import com.sgcc.comm.util.CommUtil;
import com.sgcc.comm.util.service.CommService;
import com.sgcc.pesticide.workbench.service.WorkbenchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

/**
 * @author jerrywang
 * @create 2017/1/15.
 */
@Controller
@RequestMapping("/workbench")
public class WorkbenchController {

    @Autowired
    WorkbenchService workbenchService;
    @Autowired
    CommService commService;

    @RequestMapping("/init")
    public String initWorkbench(){
        return "workbench/workbenchList";
    }

    /**
     * @Description 获取问题列表
     * @author JerryWang
     * @date 2017/4/10 23:03
     * @param param
     * @return
     */
    @RequestMapping("/getIssueList")
    public @ResponseBody Map<String, Object> getIssueList(@RequestBody Map<String, String> param){
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Map<String, Object> map = new HashMap<>();
        map.put("dt", sdf.format(now));
        map.put("query", workbenchService.getIssueList(param));
        return map;
    }

    @RequestMapping("/detail")
    public String initDetailPage(){
        return "/workbench/workbenchDetail";
    }

    /**
     * @Description 获取详细信息
     * @author JerryWang
     * @date 2017/4/10 21:46
     * @param uuid
     * @param type
     * @return
     */
    @RequestMapping("/getDetail")
    public @ResponseBody Map<String, String> detail(String uuid, String type){
        return workbenchService.getDetail(uuid, type);
    }

    /**
     * @Description 获取项目下的所有模块
     * @author JerryWang
     * @date 2017/4/18 22:32
     * @param objectId
     * @return
     */
    @RequestMapping("/getModel")
    public @ResponseBody List<String> getModel(String objectId){
        return workbenchService.getModel(objectId);
    }

    /**
     * @Description 送测
     * @author JerryWang
     * @date 2017/5/4 23:11
     * @param param
     * @return
     */
    @RequestMapping("/push")
    public @ResponseBody String push(@RequestBody Map<String, String> param){
        return workbenchService.push(param);
    }

    /**
     * @Description 选择模块页面初始化
     * @author JerryWang
     * @date 2017/5/4 23:11
     * @param state
     * @return
     */
    @RequestMapping("/choseModelInit")
    public ModelAndView choseModelInit(String state){
        return new ModelAndView("workbench/workbenchChoseModel","state",state);
    }

    /**
     * @Description 修改负责人页面初始化
     * @author JerryWang
     * @date 2017/5/4 23:12
     * @return
     */
    @RequestMapping("/changePrincipalInit")
    public String changePrincipalInit(){
        return "workbench/workbenchChangePrincipal";
    }

    /**
     * @Description 修改负责人
     * @author JerryWang
     * @date 2017/5/4 23:16
     * @param param
     * @return
     */
    @RequestMapping("/changePrincipal")
    public @ResponseBody String changePrincipal(@RequestBody Map<String, String> param){
        return workbenchService.changePrincipal(param);
    }

    /**
     * @Description 退回页面初始化
     * @author JerryWang
     * @date 2017/5/4 23:17
     * @return
     */
    @RequestMapping("/rejectInit")
    public String rejectInit(){
        return "workbench/workbenchReject";
    }

    /**
     * @Description 退回
     * @author JerryWang
     * @date 2017/5/4 23:17
     * @param param
     * @return
     */
    @RequestMapping("/reject")
    public @ResponseBody String reject(@RequestBody Map<String, String> param){
        boolean result =  workbenchService.updateState(param);
        if(result)
            commService.insertIssueRecord(param.get("businessId"),"退回",param.get("remark"));
        return String.valueOf(result);
    }

    @RequestMapping("/getRecord")
    public @ResponseBody List<Map<String, String>> getRecord(String businessId) {
        return workbenchService.getRecord(businessId);
    }

    @RequestMapping("/inputReason")
    public String inputReason(){
        return "workbench/workbenchInputReason";
    }

    @RequestMapping("/changeStateWithReason")
    public @ResponseBody String changeStateWithReason(@RequestBody Map<String, String> param){
        boolean result =  workbenchService.updateState(param);
        if(result) {
            String operateDetail = "";
            switch (param.get("state")) {
                case "1" : operateDetail = "提交";break;
                case "7" : operateDetail = "拒绝";break;
                case "9" : operateDetail = "废弃";break;
            }
            commService.insertIssueRecord(param.get("businessId"), operateDetail + " 了任务", param.get("remark"));
        }
        return String.valueOf(result);
    }

    /**
     * @Description 修改任务状态
     * @author JerryWang
     * @date 2017/5/31 13:49
     * @param businessId
     * @param issueType
     * @param state
     * @return
     */
    @RequestMapping("/changeState")
    public @ResponseBody String changeState(String businessId, String issueType, String state){
        Map<String, String> param = new HashMap<>();
        param.put("businessId", businessId);
        param.put("issueType", issueType);
        param.put("state", state);
        boolean result =  workbenchService.updateState(param);
        if(result){
            String operateDetail = "";
            switch (state){
                case "1" : operateDetail = "提交";
                    break;
                case "3" : operateDetail = "挂起";
                    break;
                case "4" : operateDetail = "发布";
                    break;
                case "5" : operateDetail = "通过";
                    break;
                case "6" : operateDetail = "结束";
                    break;
            }
            commService.insertIssueRecord(businessId,operateDetail + " 了任务","");
        }
        return String.valueOf(result);
    }


    /**
     * @Description 获取附件信息
     * @author JerryWang
     * @date 2017/7/13 12:56
     * @param businessId
     * @return
     */
    @RequestMapping("/getAttachment")
    public @ResponseBody List<Map<String, String>> getAttachment(String businessId){
        Map<String, String> iconMap = new HashMap<>();
        iconMap.put("application/msword","doc");
        iconMap.put("image/png","png");
        iconMap.put("image/jpeg","jpg");
        iconMap.put("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet","xls");
        iconMap.put("application/zip","zip");
        iconMap.put("application/octet-stream","exe");
        iconMap.put("video/mp4","mp4");
        iconMap.put("text/rtf","rtf");
        iconMap.put("text/plain","txt");
        iconMap.put("application/x-rar","zip2");

        List<Map<String, String>> list = workbenchService.getAttachment(businessId);
        for (int i = 0; i < list.size(); i++) {
            if(iconMap.containsKey(list.get(i).get("file_type"))){
                list.get(i).put("typeClass", iconMap.get(list.get(i).get("file_type")));
            }else {
                list.get(i).put("typeClass", "unknown");
            }
        }

        return list;
    }

    @RequestMapping("/downloadAttachment")
    public void downloadAttachment(HttpServletRequest request, HttpServletResponse response){
        String path = CommUtil.getInstance().PROPERTIES.get("attachment-path");
        String uuid = request.getParameter("uuid");
        try {
            // 获取原始文件名
            String originName = workbenchService.getAttachmentNameByUuid(uuid);
            String[] originNameArray = originName.split("\\.");
            StringBuffer name = new StringBuffer();
            String type = "";
            if(originNameArray.length > 1) {
                for (int i = 0; i < originNameArray.length - 1; i++) {
                    name.append(originNameArray[i] + ".");
                }
                name.delete(name.length() - 1, name.length());
                type = "."+originNameArray[originNameArray.length - 1];
            }else {
                name.append(originName);
            }
            //设置响应头和客户端保存文件名
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName=" + new String(name.toString().getBytes("UTF-8"),"ISO8859-1") + type);
            //打开本地文件流
            InputStream inputStream = new FileInputStream( path+uuid);
            //激活下载操作
            OutputStream os = response.getOutputStream();

            //循环写入输出流
            byte[] b = new byte[2048];
            int length;
            while ((length = inputStream.read(b)) > 0) {
                os.write(b, 0, length);
            }

            // 这里主要关闭。
            os.close();
            inputStream.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @RequestMapping("/submitRemark")
    public @ResponseBody String submitRemark(String uuid, String remark){
        try {
            commService.insertIssueRecord(uuid, "添加了备注",remark);
            return "true";
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }

    @RequestMapping("/update")
    public String updateInit(){
        return "/workbench/workbenchUpdate";
    }

}
