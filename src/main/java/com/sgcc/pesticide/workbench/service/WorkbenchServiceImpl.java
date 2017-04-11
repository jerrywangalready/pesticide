package com.sgcc.pesticide.workbench.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sgcc.comm.model.Query;
import com.sgcc.comm.util.CommUtil;
import com.sgcc.pesticide.demo.model.Demo;
import com.sgcc.pesticide.workbench.dao.WorkbenchDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 * @create 2017/1/27.
 */
@Service
public class WorkbenchServiceImpl implements WorkbenchService {
    @Autowired
    WorkbenchDao workbenchDao;

    /**
     * @Description 获取问题列表
     * @author JerryWang
     * @date 2017/4/9 09:12
     * @param param
     * @return
     */
    public Query getIssueList(Map<String, String> param) {

        PageHelper.startPage(Integer.parseInt(param.get("pageNum")),10);
        List<Map<String, String>> list = workbenchDao.getIssueList(param);
        Query query = new Query();
        query.setList(list);
        query.setPageNum(Integer.parseInt(param.get("pageNum")));
        query.setPageSize(10);
        query.setTotal(((Page)list).getTotal());

        return query;
//        return workbenchDao.getIssueList(param);
    }

    /**
     * @Description 获取详细信息
     * @author JerryWang
     * @date 2017/4/10 18:33
     * @author JerryWang
     * @param uuid
     * @param type
     * @return
     */
    public Map<String, String> getDetail(String uuid, String type){
        if("T".equals(type)){
            return workbenchDao.getTaskDetail(uuid);
        }else {
            return workbenchDao.getBugDetail(uuid);
        }
    }
}
