package com.sgcc.pesticide.issuePool.service;

import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 */
public interface IssuePoolService {

    /**
     * @Description 获取问题列表
     * @author JerryWang
     * @date 2017/4/9 09:12
     * @param param
     * @return
     */
    PageInfo getIssueList(Map<String, String> param);

    /**
     * @Description 获取详细信息
     * @author JerryWang
     * @date 2017/4/10 18:33
     * @author JerryWang
     * @param uuid
     * @param type
     * @return
     */
    Map<String, String> getDetail(String uuid, String type);


    /**
     * @Description save a task info
     * @author JerryWang
     * @date 2017/1/27 17:44
     * @param param
     * @return
     */
    public String saveTask(Map<String, String> param);


    /**
     * @Description save a task info
     * @author JerryWang
     * @date 2017/1/27 17:44
     * @param param
     * @return
     */
    public String saveBug(Map<String, String> param);

    /**
     * @Description 获取前序任务
     * @author JerryWang
     * @date 2017/6/4 19:48
     * @param issueCode
     * @return
     */
    Map<String,String> getParentIssue(String issueCode);

    /**
     * @Description 导出Excel
     * @author JerryWang
     * @date 2017/6/7 14:17
     * @return
     */
    String exportExcel(Map<String, String> param);

    /**
     * @Description 获取附件信息
     * @author JerryWang
     * @date 2017/7/17 13:00
     * @param businessId
     * @return
     */
    List<Map<String,String>> getAttachment(String businessId);

    /**
     * @Description 校验是否为测试人员
     * @author JerryWang
     * @date 2017/8/1 14:04
     * @param objectCode
     * @return
     */
    String checkTester(String objectCode, String username);
}
