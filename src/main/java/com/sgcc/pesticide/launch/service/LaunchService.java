package com.sgcc.pesticide.launch.service;

import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 * @create 2017/6/14.
 */
public interface LaunchService {

    /**
     * @Description 获取问题列表
     * @author JerryWang
     * @date 2017/6/14 17:34
     * @param param
     * @return
     */
    List<Map<String, Object>> getIssueList(Map<String, String> param);

    /**
     * @Description 修改版本号
     * @author JerryWang
     * @date 2017/6/15 14:03
     * @param param
     * @return
     */
    void changeVersionCode(Map<String, String> param);

    /**
     * @Description 获取问题清单by版本号
     * @author JerryWang
     * @date 2017/6/25 15:05
     * @param param
     * @return
     */
    List<Map<String, String>> getIssueListByVersionCode(Map<String, String> param) throws Exception;

    /**
     * @Description 修改版本状态
     * @author JerryWang
     * @date 2017/6/25 15:08
     * @param param
     */
    void updateVersionCodeState(Map<String, String> param) throws Exception;

    /**
     * @Description 修改任务状态
     * @author JerryWang
     * @date 2017/6/25 15:42
     * @param param
     */
    void updateIssueState(Map<String, String> param) throws Exception;

    List<Map<String,String>> getLaunchDetail(Map<String, String> param);

    boolean checkRole(Map<String, String> param);
}
