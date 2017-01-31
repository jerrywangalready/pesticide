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
    public List<Map<String, String>> getObjectsByUser(String username);

}
