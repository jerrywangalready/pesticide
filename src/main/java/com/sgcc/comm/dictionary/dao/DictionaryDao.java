package com.sgcc.comm.dictionary.dao;

import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 * @create 2017/1/19.
 */
public interface DictionaryDao {


    /**
     * @Description 获取字典数据
     * @author JerryWang
     * @date 2017/1/19 22:47
     * @param param
     * @return
     */
    public List<Map<String, String>> getDict(Map<String, String> param);
}
