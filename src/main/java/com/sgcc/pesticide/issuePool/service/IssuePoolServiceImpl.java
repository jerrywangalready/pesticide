package com.sgcc.pesticide.issuePool.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sgcc.comm.util.CommUtil;
import com.sgcc.pesticide.issuePool.dao.IssuePoolDao;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.util.HSSFColor;
//import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Color;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.util.HashMap;
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
     * @param param
     * @return
     * @Description 获取问题列表
     * @author JerryWang
     * @date 2017/4/9 09:12
     */
    @Override
    public PageInfo getIssueList(Map<String, String> param) {
        int pageNum = Integer.parseInt(param.get("pageNum"));
        PageHelper.startPage(pageNum, Integer.parseInt(param.get("pageSize")));
        List<Map<String, String>> list = issuePoolDao.getIssueList(param);
        return new PageInfo(list);
    }

    /**
     * @param uuid
     * @param type
     * @return
     * @Description 获取详细信息
     * @author JerryWang
     * @date 2017/4/10 18:33
     * @author JerryWang
     */
    @Override
    public Map<String, String> getDetail(String uuid, String type) {
        if ("T".equals(type)) {
            return issuePoolDao.getTaskDetail(uuid);
        } else {
            return issuePoolDao.getBugDetail(uuid);
        }
    }

    /**
     * @param param
     * @return
     * @Description save a task info
     * @author JerryWang
     * @date 2017/1/27 17:44
     */
    @Override
    public String saveTask(Map<String, String> param) {
        issuePoolDao.updateTask(param);

        return param.get("uuid");
    }

    /**
     * @param param
     * @Description save a bug info
     * @author JerryWang
     * @date 2017/1/27 17:45
     */
    @Override
    public String saveBug(Map<String, String> param) {
        issuePoolDao.updateBug(param);
        return param.get("uuid");
    }

    /**
     * @param issueCode
     * @return
     * @Description 获取前序任务
     * @author JerryWang
     * @date 2017/6/4 19:48
     */
    @Override
    public Map<String, String> getParentIssue(String issueCode) {
        return issuePoolDao.getParentIssue(issueCode);
    }

    @Override
    public List<Map<String, String>> getAllIssueList(Map<String, String> param) {
        return issuePoolDao.getIssueList(param);
    }

    /**
     * @param businessId
     * @return
     * @Description 获取附件信息
     * @author JerryWang
     * @date 2017/7/17 13:00
     */
    @Override
    public List<Map<String, String>> getAttachment(String businessId) {
        return issuePoolDao.getAttachment(businessId);
    }

    /**
     * @param objectCode
     * @return
     * @Description 校验是否为测试人员
     * @author JerryWang
     * @date 2017/8/1 14:04
     */
    @Override
    public String checkTester(String objectCode, String username) {
        Map<String, String> param = new HashMap<>();
        param.put("objectCode", objectCode);
        param.put("username", username);
        int i = issuePoolDao.checkTester(param);
        if (i > 0) {
            return "true";
        } else {
            return "false";
        }
    }

}
