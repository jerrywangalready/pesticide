package com.sgcc.pesticide.workbench.dao;

import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 */
public interface WorkbenchDao {

    /**
     * @Description 获取问题列表
     * @author JerryWang
     * @date 2017/4/9 12:18
     * @param param
     * @return
     */
    List<Map<String, String>> getIssueList(Map<String, String> param);

}
