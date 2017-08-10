package com.sgcc.pesticide.index.dao;

import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 * @create 2017/1/22.
 */
public interface IndexDao {

    /**
     * @Description 根据用户名获取所在项目列表
     * @author JerryWang
     * @date 2017/1/22 19:56
     * @param username
     * @return
     */
    List<Map<String, String>> getObjectsByUser(String username);

    /**
     * @Description 获取最新数据数量
     * @author JerryWang
     * @date 2017/8/3 18:26
     * @param param
     * @return
     */
    String countNew(Map<String, String> param);

    /**
     * @Description 获取最新数据
     * @author JerryWang
     * @date 2017/8/4 14:13
     * @param param
     * @return
     */
    List<Map<String,String>> getNew(Map<String, String> param);

    /**
     * @Description 根据人员编号修改显示状态字段
     * @author JerryWang
     * @date 2017/8/5 14:15
     * @param param
     */
    void updateDisplayStateByUser(Map<String, String> param);
}
