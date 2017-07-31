package com.sgcc.pesticide.chart.service;

import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 * @create 2017/7/27.
 */
public interface ChartService {
    /**
     * @Description 获取版本号列表
     * @author JerryWang
     * @date 2017/7/27 14:55
     * @param startDate
     * @param finishDate
     * @return
     */
    List<Map<String,String>> getVersion(String objectCode, String startDate, String finishDate);

    /**
     * @Description 获取详细信息
     * @author JerryWang
     * @date 2017/7/28 11:49
     * @param objectCode
     * @param version
     * @return
     */
    List<Map<String,String>> getDetail(String objectCode, String version);
}
