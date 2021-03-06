package com.sgcc.pesticide.issuePool.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by DCH on 2017/4/11.
 */
public interface IssuePoolDao {

    /**
     * @Description 获取问题列表
     * @author 杜成皓
     * @date 2017/4/11 22:39
     * @param param
     * @return
     */
    List<Map<String, String>> getIssueList(Map<String, String> param);

    /**
     * @Description 获取Task详细信息
     * @author 杜成皓
     * @date 2017/4/11 22:40
     * @param uuid
     * @return
     */
    Map<String, String> getTaskDetail(String uuid);

    /**
     * @Description 获取bug详细信息
     * @author 杜成皓
     * @date 2017/4/11 22:41
     * @param uuid
     * @return
     */
    Map<String, String> getBugDetail(String uuid);

    /**
     * @Description 修改任务
     * @author JerryWang
     * @date 2017/1/27 17:23
     * @param param
     */
    void updateTask(Map<String, String> param);

    /**
     * @Description update the bug info
     * @author JerryWang
     * @date 2017/1/27 17:23
     * @param param
     */
    void updateBug(Map<String, String> param);

    /**
     * @Description 获取前序任务
     * @author JerryWang
     * @date 2017/6/4 19:49
     * @param issueCode
     * @return
     */
    Map<String,String> getParentIssue(String issueCode);

    /**
     * @Description 获取附件信息
     * @author JerryWang
     * @date 2017/7/17 13:00
     * @param businessId
     * @return
     */
    List<Map<String,String>> getAttachment(String businessId);

    /**
     * @Description 查询测试人员数量
     * @author JerryWang
     * @date 2017/8/1 14:06
     * @param param
     */
    int checkTester(Map<String, String> param);
}
