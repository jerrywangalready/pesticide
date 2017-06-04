package com.sgcc.pesticide.issuePool.service;

import com.sgcc.comm.model.Query;

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
    Query getIssueList(Map<String, String> param);

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

}
