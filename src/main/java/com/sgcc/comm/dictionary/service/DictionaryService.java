package com.sgcc.comm.dictionary.service;

import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 * @create 2017/1/19.
 */
public interface DictionaryService {

    /**
     * @Description 获取字典表数据
     * @author JerryWang
     * @date 2017/1/19 23:05
     * @param param
     * @return
     */
    public List<Map<String, String>> getDict(Map<String, String> param);
    /**
     * @Description 获取字典表数据
     * @author JerryWang
     * @date 2017/1/19 23:05
     * @param paramArray
     * @return
     */
    public Map<String, Map> getDict(Map<String, String>[] paramArray);
}
