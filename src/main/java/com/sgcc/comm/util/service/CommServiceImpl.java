package com.sgcc.comm.util.service;

import com.sgcc.comm.util.CommUtil;
import com.sgcc.comm.util.dao.CommDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 * @create 2017/5/10.
 */
@Service
public class CommServiceImpl implements CommService {
    @Autowired
    CommDao commDao;

    /**
     * @Description 插入操作日志
     * @author JerryWang
     * @date 2017/5/11 21:20
     * @param businessId
     * @param operateDetail
     * @param remark
     */
    public void insertIssueRecord(String businessId, String operateDetail, String remark) {
        Map<String, String> param = new HashMap<>();
        param.put("businessId", businessId);
        param.put("operateDetail", operateDetail);
        param.put("remark", remark);
        param.put("uuid", CommUtil.getUUID());
        param.put("operator", CommUtil.getLoginInfo().getLoginUser());
        commDao.insertIssueRecord(param);
    }

    /**
     * @Description 根据businessId获取操作日志
     * @author JerryWang
     * @date 2017/5/11 21:20
     * @param businessId
     * @return
     */
    public List<Map<String, String>> getIssueRecord(String businessId) {
        return commDao.getIssueRecord(businessId);
    }
}
