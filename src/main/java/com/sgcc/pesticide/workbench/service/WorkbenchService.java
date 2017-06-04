package com.sgcc.pesticide.workbench.service;

import com.sgcc.comm.model.Query;
import com.sgcc.comm.util.service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 */
public interface WorkbenchService extends BaseService{

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
    Map<String, String> getDetail(String uuid,String type);

    /**
     * @Description 获取项目下的模块
     * @author JerryWang
     * @date 2017/4/18 22:33
     * @param objectId
     * @return
     */
    List<String> getModel(String objectId);


    /**
     * @Description 送测
     * @author JerryWang
     * @date 2017/4/18 23:14
     * @param param
     * @return
     */
    String push(Map<String, String> param);

    /**
     * @Description 修改负责人
     * @author JerryWang
     * @date 2017/5/1 23:27
     * @param param
     * @return
     */
    String changePrincipal(Map<String, String> param);

    /**
     * @Description 获取操作记录
     * @author JerryWang
     * @date 2017/5/9 22:58
     * @param businessId
     * @return
     */
    List<Map<String, String>> getRecord(String businessId);

    /**
     * @Description 修改状态
     * @author JerryWang
     * @date 2017/5/31 11:32
     * @param param
     * @return
     */
    public boolean updateState(Map<String, String> param);
}
