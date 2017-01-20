package com.sgcc.comm.dictionary.service.impl;

import com.sgcc.comm.dictionary.dao.DictionaryDao;
import com.sgcc.comm.dictionary.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
