package com.sgcc.comm.util.service;

import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 */
public interface CommService {

    /**
     * @Description 插入操作日志
     * @author JerryWang
     * @date 2017/5/11 21:22
     * @param businessId
     * @param operateDetail
     * @param remark
     */
    void insertIssueRecord(String businessId, String operateDetail, String remark);

    /**
     * @Description 根据businessId获取操作日志
     * @author JerryWang
     * @date 2017/5/11 21:20
     * @param businessId
     * @return
     */
    List<Map<String, String>> getIssueRecord(String businessId);
}
