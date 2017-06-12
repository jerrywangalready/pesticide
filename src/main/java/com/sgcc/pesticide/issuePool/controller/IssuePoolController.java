package com.sgcc.pesticide.issuePool.controller;

import com.sgcc.comm.model.Query;
import com.sgcc.comm.util.CommUtil;
import com.sgcc.comm.util.service.CommService;
import com.sgcc.pesticide.create.service.CreationService;
import com.sgcc.pesticide.issuePool.service.IssuePoolService;
import com.sun.deploy.net.HttpResponse;
import com.sun.deploy.net.URLEncoder;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String initWorkbench(){
        return "issuePool/issuePoolInit";
    }

    /**
     * @Description 获取问题列表
     * @author JerryWang
     * @date 2017/4/10 23:03
     * @param param
     * @return
     */
    @RequestMapping("/getIssueList")
    public @ResponseBody Query getIssueList(@RequestBody Map<String, String> param){
        return issuePoolService.getIssueList(param);
    }

    @RequestMapping("/detail")
    public String initDetailPage(){
        return "/issuePool/issuePoolDetail";
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
        return issuePoolService.getDetail(uuid, type);
    }

    @RequestMapping("/update")
    public String updateInit(){
        return "/issuePool/issuePoolUpdate";
    }


    @RequestMapping(value = "/save")
    public @ResponseBody String save(@RequestBody Map<String, String> param){

        String description = CommUtil.saveImage(param.get("description"));

        param.put("description",description);

        if("commit".equals(param.get("todo"))){
            param.put("state","1");
        }else {
            param.put("state","0");
        }

        commService.insertIssueRecord(param.get("uuid"),"修改 了任务","");

        if("T".equals(param.get("issueType"))){
            return issuePoolService.saveTask(param);
        }else {
            return issuePoolService.saveBug(param);
        }

    }

    @RequestMapping("/getParentIssue")
    public @ResponseBody Map<String, String> getParentIssue(String issueCode){
        return issuePoolService.getParentIssue(issueCode);
    }


    /**
     * @Description 根据任务编号搜索任务
     * @author JerryWang
     * @date 2017/4/2 10:39
     * @param code
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "searchTask",method = RequestMethod.POST)
    public List<Map<String, String>> searchTask(String code, String objectCode){
        Map<String, String> param = new HashMap<>();
        param.put("code",code.toUpperCase());
        param.put("objectCode",objectCode);
        return creationService.searchTask(param);
    }

    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletResponse response){

        Map<String, String> param = new HashMap<>();
        String uuid = issuePoolService.exportExcel(param);

        String path = CommUtil.getInstance().PROPERTIES.get("file");

        try {
            //设置响应头和客户端保存文件名
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName=" + new String("任务清单".getBytes("UTF-8"),"ISO8859-1") + ".xls");
            //打开本地文件流
            InputStream inputStream = new FileInputStream( path+uuid+".xls");
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
        } finally {
            // 删除文件
            File file = new File(path+uuid+".xls");
            file.delete();
        }
    }
}
