package com.sgcc.pesticide.issuePool.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sgcc.comm.model.Query;
import com.sgcc.comm.util.CommUtil;
import com.sgcc.pesticide.issuePool.dao.IssuePoolDao;
import com.sgcc.pesticide.workbench.dao.WorkbenchDao;
import com.sgcc.pesticide.workbench.service.WorkbenchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 * @create 2017/1/27.
 */
@Service
public class IssuePoolServiceImpl implements IssuePoolService {
    @Autowired
    IssuePoolDao issuePoolDao;

    /**
     * @Description 获取问题列表
     * @author JerryWang
     * @date 2017/4/9 09:12
     * @param param
     * @return
     */
    public Query getIssueList(Map<String, String> param) {
        int pageNum = Integer.parseInt(param.get("pageNum"));
        PageHelper.startPage(pageNum,Integer.parseInt(param.get("pageSize")));
        List<Map<String, String>> list = issuePoolDao.getIssueList(param);
        Query query = new Query();
        query.setList(list);
        query.setPageNum(pageNum);
        query.setPageSize(Integer.parseInt(param.get("pageSize")));
        query.setTotal(((Page)list).getTotal());
        return query;
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
            return issuePoolDao.getTaskDetail(uuid);
        }else {
            return issuePoolDao.getBugDetail(uuid);
        }
    }

    /**
     * @Description save a task info
     * @author JerryWang
     * @date 2017/1/27 17:44
     * @param param
     * @return
     */
    public String saveTask(Map<String, String> param){
        issuePoolDao.updateTask(param);

        return param.get("uuid");
    }

    /**
     * @Description save a bug info
     * @author JerryWang
     * @date 2017/1/27 17:45
     * @param param
     */
    public String saveBug(Map<String, String> param) {
        issuePoolDao.updateBug(param);
        return param.get("uuid");
    }
}
