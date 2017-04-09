package com.sgcc.pesticide.create.service;

import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 */
public interface CreationService {

    /**
     * @Description 插入任务
     * @author JerryWang
     * @date 2017/1/27 17:42
     * @param param
     * @return
     */
    String saveTask(Map<String, String> param);

    /**
     * @Description 修改任务
     * @author JerryWang
     * @date 2017/1/27 17:51
     * @param param
     */
    String saveBug(Map<String, String> param);

    /**
     * @Description 根据任务编号获取任务列表
     * @author JerryWang
     * @date 2017/3/26 00:00
     * @param code
     * @return
     */
    List<Map<String, String>> searchTask(Map<String, String> param);
}
