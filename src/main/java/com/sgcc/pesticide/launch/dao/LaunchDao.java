package com.sgcc.pesticide.launch.dao;

import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 * @create 2017/6/14.
 */
public interface LaunchDao {


    /**
     * @Description 获取问题列表
     * @author JerryWang
     * @date 2017/6/14 17:35
     * @param param
     * @return
     */
    List<Map<String,String>> getIssueList(Map<String, String> param);

    /**
     * @Description 修改版本号
     * @author JerryWang
     * @date 2017/6/15 14:13
     * @param param
     * @return
     */
    void changeVersionCode(Map<String, String> param);

    List<String> getVersionList(Map<String, String> param);

    /**
     * @Description 获取问题清单by版本号
     * @author JerryWang
     * @date 2017/6/25 15:06
     * @param param
     * @return
     */
    List<Map<String, String>> getIssueListByVersionCode(Map<String, String> param);

    /**
     * @Description 
     * @author JerryWang
     * @date 2017/6/25 16:33
     * @param param
     */
    void updateVersionCodeState(Map<String, String> param);

    /**
     * @Description 
     * @author JerryWang
     * @date 2017/6/25 16:33
     * @param param
     */
    void updateIssueState(Map<String, String> param);

    List<Map<String,String>> getLaunchDetail(Map<String, String> param);

    int checkRole(Map<String, String> param);
}
