package com.sgcc.pesticide.workbench.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sgcc.comm.model.Query;
import com.sgcc.comm.util.CommUtil;
import com.sgcc.comm.util.service.CommService;
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
    @Autowired
    CommService commService;

    /**
     * @Description 获取问题列表
     * @author JerryWang
     * @date 2017/4/9 09:12
     * @param param
     * @return
     */
    public Query getIssueList(Map<String, String> param) {

        PageHelper.startPage(Integer.parseInt(param.get("pageNum")),Integer.parseInt(param.get("pageSize")));
        List<Map<String, String>> list = workbenchDao.getIssueList(param);
        Query query = new Query();
        query.setList(list);
        query.setPageNum(Integer.parseInt(param.get("pageNum")));
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
            return workbenchDao.getTaskDetail(uuid);
        }else {
            return workbenchDao.getBugDetail(uuid);
        }
    }


    /**
     * @Description 获取项目下的模块
     * @author JerryWang
     * @date 2017/4/18 22:33
     * @param objectId
     * @return
     */
    public List<String> getModel(String objectId){
        return workbenchDao.getModel(objectId);
    }

    /**
     * @Description 送测
     * @author JerryWang
     * @date 2017/4/18 23:14
     * @param param
     * @return
     */
    public String push(Map<String, String> param){

        try {
            param.put("create_user",CommUtil.getLoginInfo().getLoginUser());
//            param.put("state","2");// 待发布

            // 修改任务状态
            if("T".equals(param.get("issueType"))){
                workbenchDao.updateTask(param);
            }else{
                workbenchDao.updateBug(param);
            }
            // 插入送测信息到送测表中
            String[] modelCode = param.get("modelCodes").split(",");
            for(int i=0;i<modelCode.length;i++){
                param.put("uuid", CommUtil.getUUID());
                param.put("model_code", modelCode[i]);
                workbenchDao.insertPushInfo(param);
            }
            // 保存操作记录
            commService.insertIssueRecord(param.get("businessId"),"送测","");

            return "true";
        } catch (Exception e) {
            return "false";
        }
    }

    /**
     * @Description 修改负责人
     * @author JerryWang
     * @date 2017/5/1 23:25
     * @param param
     * @return
     */
    public String changePrincipal(Map<String, String> param){
        try {
            if ("T".equals(param.get("issueType"))) {
                workbenchDao.updateTask(param);
            } else if ("B".equals(param.get("issueType"))) {
                workbenchDao.updateBug(param);
            }
            commService.insertIssueRecord(param.get("businessId"),"指派给 "+param.get("principalName"),param.get("remark"));
            return "true";
        } catch (Exception e) {
            return "false";
        }
    }

    /**
     * @Description 获取操作记录
     * @author JerryWang
     * @date 2017/5/9 22:58
     * @param businessId
     * @return
     */
    public List<Map<String, String>> getRecord(String businessId) {
        return commService.getIssueRecord(businessId);
    }

    /**
     * @Description 修改状态
     * @author JerryWang
     * @date 2017/5/31 11:32
     * @param param
     * @return
     */
    public boolean updateState(Map<String, String> param){
        try {
            if("T".equals(param.get("issueType"))){
                workbenchDao.updateTask(param);
            }else if ("B".equals(param.get("issueType"))){
                workbenchDao.updateBug(param);
            }
            return true;
        }catch (Exception e){
            return false;
        }
    }


}
