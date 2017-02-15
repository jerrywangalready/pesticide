package com.sgcc.pesticide.create.service.impl;

import com.sgcc.comm.util.CommUtil;
import com.sgcc.pesticide.create.dao.CreationDao;
import com.sgcc.pesticide.create.service.CreationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 * @create 2017/1/27.
 */
@Service
public class CreationServiceImpl implements CreationService {
    @Autowired
    CreationDao creationDao;

    /**
     * @Description 插入任务
     * @author JerryWang
     * @date 2017/1/27 17:44
     * @param param
     * @return
     */
    @Override
    public String insertTask(Map<String, String> param){
        String uuid = CommUtil.getUUID();
        param.put("uuid",uuid);
        if("commit".equals(param.get("todo"))){
            String taskCode = creationDao.getNewDevNum(param);
            param.put("task_code",taskCode);
        }
        creationDao.insertDevTask(param);
        return uuid;
    }

    /**
     * @Description 修改任务
     * @author JerryWang
     * @date 2017/1/27 17:45
     * @param param
     */
    @Override
    public void updateTask(Map<String, String> param) {
        if("commit".equals(param.get("todo"))){
            String taskCode = creationDao.getNewDevNum(param);
            param.put("task_code",taskCode);
        }
        creationDao.updateDevTask(param);
    }

    /**
     * @Description 获取关联信息
     * @author JerryWang
     * @date 2017/1/29 17:16
     * @param param
     * @return
     */
    public List<Map<String, String>> getLinkInfo(Map<String, String> param){
        List<Map<String, String>> rl = new ArrayList<>();
        if("2".equals(param.get("taskType"))){
            rl = creationDao.getLinkDevInfo(param);
        }else if("3".equals(param.get("taskType"))){
            rl = creationDao.getLinkTestInfo(param);
        }
        return rl;
    }


}
