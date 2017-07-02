package com.sgcc.pesticide.create.service;

import com.sgcc.comm.util.service.BaseService;
import com.sgcc.comm.util.service.CommService;

import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 */
public interface CreationService extends BaseService {

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
     * @param param
     * @return
     */
    List<Map<String, String>> searchTask(Map<String, String> param);

    /**
     * @Description 新增版本信息
     * @author JerryWang
     * @date 2017/6/28 14:22
     * @param param
     * @return
     */
    String addVersion(Map<String, String> param);
}
