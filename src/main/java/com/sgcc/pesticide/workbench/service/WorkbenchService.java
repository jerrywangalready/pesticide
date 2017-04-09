package com.sgcc.pesticide.workbench.service;

import com.sgcc.comm.model.Query;

import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 */
public interface WorkbenchService {

    /**
     * @Description 获取问题列表
     * @author JerryWang
     * @date 2017/4/9 09:12
     * @param param
     * @return
     */
    Query getIssueList(Map<String, String> param);

}
