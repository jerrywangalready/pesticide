package com.sgcc.comm.util.service;

import com.sgcc.comm.util.CommUtil;
import com.sgcc.comm.util.dao.CommDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jerrywang
 * @create 2017/5/10.
 */
@Service
public class CommServiceImpl implements CommService {
    @Autowired
    CommDao commDao;

    public void insertIssueRecord(String businessId, String operateDetail, String remark) {
        Map<String, String> param = new HashMap<>();
        param.put("businessId", businessId);
        param.put("operateDetail", operateDetail);
        param.put("remark", remark);
        param.put("uuid", CommUtil.getUUID());
        param.put("operator", CommUtil.getLoginInfo().getLoginUser());
        commDao.insertIssueRecord(param);
    }
}
