package com.sgcc.pesticide.issuePool.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sgcc.comm.util.CommUtil;
import com.sgcc.pesticide.issuePool.dao.IssuePoolDao;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

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
    @Override
    public PageInfo getIssueList(Map<String, String> param) {
        int pageNum = Integer.parseInt(param.get("pageNum"));
        PageHelper.startPage(pageNum,Integer.parseInt(param.get("pageSize")));
        List<Map<String, String>> list = issuePoolDao.getIssueList(param);
        return new PageInfo(list);
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
    @Override
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
    @Override
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

    /**
     * @return
     * @Description 导出Excel
     * @author JerryWang
     * @date 2017/6/7 14:15
     */
    @Override
    public String exportExcel(Map<String, String> param) {
        HSSFWorkbook wb = new HSSFWorkbook();
        String uuid = CommUtil.getUUID();

        try {
            HSSFSheet sheet = wb.createSheet("new sheet");
            HSSFRow titleRow = sheet.createRow(0);
            titleRow.setHeight((short) 420);
            int[] ints = {2048,2048,20480,2048,3048,2048,2048,2048,2048};
            String[] title = {"序号","编号","标题","状态","模块","优先级","问题级别","发布者","接收者"};
            // 循环表头
            for(int i=0;i<title.length;i++){
                titleRow.createCell(i).setCellValue(title[i]);
                sheet.setColumnWidth(i,ints[i]);
            }
            // 获取数据
            List<Map<String, String>> list = issuePoolDao.getIssueList(param);

            // 循环塞入数据
            for(int j=1;j<=list.size();j++){
                Map<String, String> map = list.get(j-1);
                HSSFRow row = sheet.createRow(j);
                row.createCell(0).setCellValue(j);
                row.createCell(1).setCellValue(map.get("CODE"));
                row.createCell(2).setCellValue(map.get("TITLE"));
                row.createCell(3).setCellValue(map.get("STATE_VALUE"));
                row.createCell(4).setCellValue(map.get("MODEL_NAME"));
                row.createCell(5).setCellValue(map.get("PRIORITY_VALUE"));
                row.createCell(6).setCellValue(map.get("BUG_LEVEL"));
                row.createCell(7).setCellValue(map.get("NICKNAME"));
                row.createCell(8).setCellValue(map.get("PRINCIPAL_NAME"));
            }







            String path = CommUtil.getInstance().PROPERTIES.get("file");
            FileOutputStream fileOut = new FileOutputStream(path + uuid + ".xls");
            wb.write(fileOut);
            fileOut.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

        return uuid;
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
        if(i > 0) {
            return "true";
        } else {
            return "false";
        }
    }

}
