package com.sgcc.comm.util.dao;

import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 */
public interface CommDao{

    /**
     * @Description 插入问题日志记录
     * @author JerryWang
     * @date 2017/4/29 14:55
     * @param param
     */
    void insertIssueRecord(Map<String, String> param);

    /**
     * @Description 根据businessId获取操作日志
     * @author JerryWang
     * @date 2017/5/11 21:20
     * @param businessId
     * @return
     */
    List<Map<String, String>> getIssueRecord(String businessId);

    /**
     * @Description 获取配置信息
     * @author JerryWang
     * @date 2017/6/7 16:07
     * @return
     */
    List<Map<String, String>> getPropertiesByKey();
}
