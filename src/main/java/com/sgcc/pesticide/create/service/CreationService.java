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
    public String insertTask(Map<String, String> param);

    /**
     * @Description 修改任务
     * @author JerryWang
     * @date 2017/1/27 17:51
     * @param param
     */
    public void updateTask(Map<String, String> param);

    /**
     * @Description 获取关联信息
     * @author JerryWang
     * @date 2017/1/29 17:16
     * @param param
     * @return
     */
    public List<Map<String, String>> getLinkInfo(Map<String, String> param);
}
