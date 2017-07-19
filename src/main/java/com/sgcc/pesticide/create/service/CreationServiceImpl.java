package com.sgcc.pesticide.create.service;

import com.sgcc.comm.util.CommUtil;
import com.sgcc.comm.util.service.BaseServiceImpl;
import com.sgcc.comm.util.service.CommService;
import com.sgcc.pesticide.create.dao.CreationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 * @create 2017/1/27.
 */
@Service
public class CreationServiceImpl extends BaseServiceImpl implements CreationService{
    @Autowired
    CreationDao creationDao;

    @Autowired
    CommService commService;

    /**
     * @Description save a task info
     * @author JerryWang
     * @date 2017/1/27 17:44
     * @param param
     * @return
     */
    @Override
    public String saveTask(Map<String, String> param){
        if("i".equals(param.get("mode"))){
//            param.put("uuid",CommUtil.getUUID());
            param.put("task_code",getNewTaskCode());
            creationDao.insertTask(param);
            param.put("operator", commService.getLoginInfo().getLoginUser());
            commService.insertIssueRecord(param.get("uuid"),"创建","");
        }else{
            creationDao.updateTask(param);
            commService.insertIssueRecord(param.get("uuid"),"修改","");
        }
        return param.get("uuid");
    }

    /**
     * @Description get a new task code
     * @author JerryWang
     * @date 2017/2/15 21:27
     */
    private String getNewTaskCode(){
        return "T"+(creationDao.getTaskCode()+1);
    }

    /**
     * @Description save a bug info
     * @author JerryWang
     * @date 2017/1/27 17:45
     * @param param
     */
    @Override
    public String saveBug(Map<String, String> param) {
        if("i".equals(param.get("mode"))){
//            param.put("uuid",CommUtil.getUUID());
            param.put("bug_code",getNewBugCode());
            creationDao.insertBug(param);
            param.put("operator", commService.getLoginInfo().getLoginUser());
            commService.insertIssueRecord(param.get("uuid"),"创建","");
        }else{
            creationDao.updateBug(param);
            commService.insertIssueRecord(param.get("uuid"),"修改","");
        }
        return param.get("uuid");
    }

    /**
     * @Description get a new task code
     * @author JerryWang
     * @date 2017/2/15 21:27
     */
    private String getNewBugCode(){
        return "B"+(creationDao.getBugCode()+1);
    }

    /**
     * @Description 根据任务编号获取任务列表
     * @author JerryWang
     * @date 2017/3/25 23:59
     * @param param
     * @return
     */
    public List<Map<String, String>> searchTask(Map<String, String> param){
        return creationDao.searchTask(param);
    }

    /**
     * @param param
     * @return
     * @Description 新增版本信息
     * @author JerryWang
     * @date 2017/6/28 14:22
     */
    public String addVersion(Map<String, String> param) {
        try {
            creationDao.addVersion(param);
            return "true";
        } catch (Exception e) {
            e.printStackTrace();
            return "false";
        }
    }


    /**
     * @Description 保存附件信息
     * @author JerryWang
     * @date 2017/7/9 18:53
     * @param param
     */
    public void saveAttachment(Map<String, String> param) {
        creationDao.saveAttachment(param);
    }

    /**
     * @param uuid
     * @Description 删除附件信息
     * @author JerryWang
     * @date 2017/7/10 23:47
     */
    public void deleteAttachment(String uuid) {
        creationDao.deleteAttachment(uuid);
    }

}
