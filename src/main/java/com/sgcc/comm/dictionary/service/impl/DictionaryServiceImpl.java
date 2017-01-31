package com.sgcc.comm.dictionary.service.impl;

import com.sgcc.comm.dictionary.dao.DictionaryDao;
import com.sgcc.comm.dictionary.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 * @create 2017/1/19.
 */
@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    DictionaryDao dictionaryDao;

    /**
     * @Description 获取字典表数据
     * @author JerryWang
     * @date 2017/1/19 23:04
     * @param param
     * @return
     */
    public List<Map<String, String>> getDict(Map<String, String> param){
        return dictionaryDao.getDict(param);
    }
    /**
     * @Description 获取字典表数据
     * @author JerryWang
     * @date 2017/1/19 23:04
     * @param paramArray
     * @return
     */
    public Map<String, Map> getDict(Map<String, String>[] paramArray){
        Map<String, Map> resultMap = new HashMap<>();
        for (Map<String, String> map : paramArray) {
            List<Map<String, String>> l = dictionaryDao.getDict(map);
            Map<String, String> m = new HashMap<>();
            for (Map<String, String> stringMap : l) {
                m.put(stringMap.get("CODE_KEY"),stringMap.get("CODE_VALUE"));
            }
            resultMap.put(map.get("name"),m);
        }
        return resultMap;
    }
}
