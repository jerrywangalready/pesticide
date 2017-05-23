package com.sgcc.pesticide.push.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sgcc.comm.model.Query;
import com.sgcc.comm.util.CommUtil;
import com.sgcc.pesticide.push.dao.PushDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by DCH on 2017/5/2.
 */

@Service
public class PushServiceImpl implements PushService {
    @Autowired
    PushDao pushDao;

    /**
     * @param param
     * @return
     * @Description 查询推送列表
     * @author 杜成皓
     * @date 2017/5/3 23:21
     */
    @Override
    public Query getPushList(Map<String, String> param) {
        param.put("pageNum","1");
        PageHelper.startPage(Integer.parseInt(param.get("pageNum")),10);
        List<Map<String, String>> list = pushDao.getPushList(param);
        Query query = new Query();
        query.setList(list);
        query.setPageNum(Integer.parseInt(param.get("pageNum")));
        query.setPageSize(10);
        query.setTotal(((Page)list).getTotal());
        return query;
    }

    /**
     * @param modelCode
     * @return
     * @Description 二级列表
     * @author 杜成皓
     * @date 2017/5/11 23:17
     */
    @Override
    public List<Map<String,String>> getPushDetail(String modelCode) {
        return  pushDao.getPushDetail(modelCode);
    }

    /**
     * @param modelCodes
     * @return
     * @Description 发布
     * @author JerryWang
     * @date 2017/5/14 15:22
     */
    @Override
    public String publish(String modelCodes) {
//        modelCodes = modelCodes.replace(",", "','");

        try {
            String[] modelCodesArr = modelCodes.split(",");
            List<String> l = Arrays.asList(modelCodesArr);
            // 修改送测的任务的状态
            Map<String, Object> param = new HashMap<>();
            param.put("l", l);
            pushDao.updateTaskState(param);
            pushDao.updateBugState(param);
            // 发布
            param.put("operator", CommUtil.getLoginInfo().getLoginUser());
            pushDao.updatePushInfo(param);
        } catch (Exception e) {
            return "false";
        }
        return "true";
    }
}
