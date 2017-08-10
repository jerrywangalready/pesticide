package com.sgcc.pesticide.index.service;

import com.sgcc.pesticide.index.dao.IndexDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 * @create 2017/1/22.
 */
@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    IndexDao indexDao;

    public List<Map<String, String>> getObjectsByUser(String username){
        return indexDao.getObjectsByUser(username);
    }

    /**
     * @param param
     * @return
     * @Description 获取最新数据数量
     * @author JerryWang
     * @date 2017/8/3 18:22
     */
    public String countNew(Map<String, String> param) {
        return indexDao.countNew(param);
    }

    public List<Map<String, String>> getNew(Map<String, String> param) {
        return indexDao.getNew(param);
    }
}
