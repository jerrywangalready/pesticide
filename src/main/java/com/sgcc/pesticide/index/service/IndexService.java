package com.sgcc.pesticide.index.service;

import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 * @create 2017/1/22.
 */
public interface IndexService {

    public List<Map<String, String>> getObjectsByUser(String username);

    /**
     * @Description 获取最新数据数量
     * @author JerryWang
     * @date 2017/8/3 18:22
     * @param param
     * @return
     */
    String countNew(Map<String, String> param);

    /**
     * @Description 获取最新数据
     * @author JerryWang
     * @date 2017/8/4 14:12
     * @param param
     * @return
     */
    List<Map<String,String>> getNew(Map<String, String> param);

}
