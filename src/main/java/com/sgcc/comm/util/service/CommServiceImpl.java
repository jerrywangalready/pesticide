package com.sgcc.comm.util.service;

import com.sgcc.comm.model.LoginInfo;
import com.sgcc.comm.util.CommUtil;
import com.sgcc.comm.util.dao.CommDao;
import com.sgcc.pesticide.login.model.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 * @create 2017/5/10.
 */
@Service
public class CommServiceImpl extends BaseServiceImpl implements CommService{
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
        param.put("operator", getLoginInfo().getLoginUser());
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

    public LoginInfo getLoginInfo(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession context = request.getSession();
        UserToken userToken = (UserToken)context.getAttribute("userToken");
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setLoginUser(userToken.getUsername());
        loginInfo.setLoginNickname(userToken.getNickname());
        return loginInfo;
    }

    /**
     * @return
     * @Description 获取配置信息
     * @author JerryWang
     * @date 2017/6/7 16:03
     */
    public List<Map<String, String>> getProperties() {
        return commDao.getPropertiesByKey();
    }
}
