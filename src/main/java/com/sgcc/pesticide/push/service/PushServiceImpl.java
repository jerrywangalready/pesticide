package com.sgcc.pesticide.push.service;

import com.sgcc.comm.util.service.BaseServiceImpl;
import com.sgcc.comm.util.service.CommService;
import com.sgcc.pesticide.push.dao.PushDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by DCH on 2017/5/2.
 */

@Service
public class PushServiceImpl extends BaseServiceImpl implements PushService {
    @Autowired
    PushDao pushDao;
    @Autowired
    CommService commService;

    /**
     * @param param
     * @return
     * @Description 查询推送列表
     * @author 杜成皓
     * @date 2017/5/3 23:21
     */
    public List<Map<String, String>> getPushList(Map<String, String> param) {
        List<Map<String, String>> list = pushDao.getPushList(param);
        return list;
    }

    /**
     * @param param
     * @return
     * @Description 二级列表
     * @author 杜成皓
     * @date 2017/5/11 23:17
     */
    public List<Map<String,String>> getPushDetail(Map<String, String> param) {
        return  pushDao.getPushDetail(param);
    }

    /**
     * @param param
     * @return
     * @Description 发布
     * @author JerryWang
     * @date 2017/5/14 15:22
     */
    public boolean publish(Map<String, Object> param) {

        try {
            // 修改送测的任务的状态
            pushDao.updateTaskState(param);
            pushDao.updateBugState(param);
            // 发布
            param.put("operator", commService.getLoginInfo().getLoginUser());
            pushDao.updatePushInfo(param);

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * @param param
     * @return
     * @Description 获取任务uuid
     * @author JerryWang
     * @date 2017/6/1 17:24
     */
    public List<Map<String, String>> getTaskUuid(Map<String, Object> param) {
        return pushDao.getTaskUuid(param);
    }

    /**
     * @return
     * @Description 查询是否有操作发布按钮的权限
     * @author JerryWang
     * @date 2017/6/4 11:17
     */
    public String checkRole(String username) {
        return pushDao.checkRole(username);
    }


}
