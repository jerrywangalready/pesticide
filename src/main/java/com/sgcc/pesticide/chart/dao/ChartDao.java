package com.sgcc.pesticide.chart.dao;

import com.sgcc.pesticide.chart.model.StaffIssueNum;

import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 * @create 2017/7/27.
 */
public interface ChartDao {

    /**
     * @Description 获取版本号列表
     * @author JerryWang
     * @date 2017/7/27 14:57
     * @param param
     * @return
     */
    List<Map<String,String>> getVersion(Map<String, String> param);

    /**
     * @Description 获取该项目下所有人员
     * @author JerryWang
     * @date 2017/7/30 15:04
     * @param param
     * @return
     */
    List<Map<String, String>> getStaffByObjCode(Map<String, String> param);

    /**
     * @Description 根据负责人分组获得全部任务数
     * @author JerryWang
     * @date 2017/7/30 16:48
     * @param param
     * @return
     */
    List<StaffIssueNum> getTotalIssueNumGroupByPrincipal(Map<String, String> param);

    /**
     * @Description 根据负责人分组获得全部任务数
     * @author JerryWang
     * @date 2017/7/30 16:48
     * @param param
     * @return
     */
    List<StaffIssueNum> getFinishedIssueNumGroupByPrincipal(Map<String, String> param);

    /**
     * @Description 获取task总量
     * @author JerryWang
     * @date 2017/7/30 18:10
     * @param param
     * @return
     */
    int getTotalTaskNum(Map<String, String> param);

    /**
     * @Description 获取完成的task数量
     * @author JerryWang
     * @date 2017/7/30 18:10
     * @param param
     * @return
     */
    int getFinishedTaskNum(Map<String, String> param);

    /**
     * @Description 获取自己创建的bug总量
     * @author JerryWang
     * @date 2017/7/30 18:13
     * @param param
     * @return
     */
    List<StaffIssueNum> getTotalBugNumByCreateUser(Map<String, String> param);

    /**
     * @Description 获取自己创建的bug完成数
     * @author JerryWang
     * @date 2017/7/30 18:13
     * @param param
     * @return
     */
    List<StaffIssueNum> getFinishedBugNumByCreateUser(Map<String, String> param);
}
