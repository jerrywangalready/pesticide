package com.sgcc.pesticide.push.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by DCH on 2017/5/2.
 */
public interface PushDao {

    /**
     * @Description 获取推送列表
     * @author 杜成皓
     * @date 2017/5/3 22:40
     * @param param
     * @return
     */
    List<Map<String, String>> getPushList(Map<String, String> param);

    /**
     * @Description 查询二级列表
     * @author 杜成皓
     * @date 2017/5/11 23:23
     * @param param modelCode
     * @return
     */
    List<Map<String,String>> getPushDetail(Map<String, String> param);

    void updateTaskState(Map<String, Object> param);
    void updateBugState(Map<String, Object> param);

    /**
     * @Description 修改推送表信息
     * @author JerryWang
     * @date 2017/5/14 15:58
     * @param param
     */
    void updatePushInfo(Map<String, Object> param);

    /**
     * @Description 获取任务uuid
     * @author JerryWang
     * @date 2017/6/2 10:43
     * @param param modelCodes
     * @return
     */
    List<Map<String,String>> getTaskUuid(Map<String, Object> param);

    /**
     * @Description 查询权限
     * @author JerryWang
     * @date 2017/6/4 11:19
     * @param username
     * @return
     */
    String checkRole(String username);
}
