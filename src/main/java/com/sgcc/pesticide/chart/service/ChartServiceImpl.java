package com.sgcc.pesticide.chart.service;

import com.sgcc.pesticide.chart.dao.ChartDao;
import com.sgcc.pesticide.chart.model.StaffIssueNum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 * @create 2017/7/27.
 */
@Service
public class ChartServiceImpl implements ChartService {

    @Autowired
    ChartDao chartDao;

    /**
     * @param startDate
     * @param finishDate
     * @return
     * @Description 获取版本号列表
     * @author JerryWang
     * @date 2017/7/27 14:55
     */
    public List<Map<String, String>> getVersion(String objectCode, String startDate, String finishDate) {
        Map<String, String> param = new HashMap<>();
        param.put("objectCode", objectCode);
        param.put("startDate", startDate);
        param.put("finishDate", finishDate);
        List<Map<String, String>> returnList = chartDao.getVersion(param);
        for (Map<String, String> map : returnList) {
            if("0".equals(map.get("a_num"))){
                map.put("percent","0");
            }else {

                int i = Integer.parseInt(map.get("f_num"))*100/Integer.parseInt(map.get("a_num"));
                map.put("percent",String.valueOf(i));
            }
        }
        return returnList;
    }

    /**
     * @param objectCode
     * @param version
     * @return
     * @Description 获取详细信息
     * @author JerryWang
     * @date 2017/7/28 11:48
     */
    public List<Map<String, String>> getDetail(String objectCode, String version) {
        Map<String, String> param = new HashMap<>();
        param.put("objectCode", objectCode);
        param.put("version", version);
        // 获取项目组所有人作为主循环
        List<Map<String, String>> list = chartDao.getStaffByObjCode(param);

        // 计算开发类任务:以负责人作为分组依据,拿到每个人的总任务数和完成任务数
        List<StaffIssueNum> totalIssueNumList = chartDao.getTotalIssueNumGroupByPrincipal(param);
        Map<String, Long> tinMap = new HashMap<>();
        for (StaffIssueNum bean : totalIssueNumList) {
            tinMap.put(bean.getUsername(), bean.getNum());
        }
        List<StaffIssueNum> finishedIssueNumList = chartDao.getFinishedIssueNumGroupByPrincipal(param);
        Map<String, Long> finMap = new HashMap<>();
        for (StaffIssueNum bean : finishedIssueNumList) {
            finMap.put(bean.getUsername(), bean.getNum());
        }
        // 计算测试类任务:
        /* 每个测试人员的任务总量 = 开发任务数 + 自己创建的bug数
         * 每个测试人员的任务完成量 = 测试完成的开发任务数 + 测试完成的自己创建的bug数
         */
        // step1:获取版本下所有task数量
        int totalTaskNum = chartDao.getTotalTaskNum(param);
        int finishedTaskNum = chartDao.getFinishedTaskNum(param);
        long unTesterBugTotalNum = 0;
        long unTesterBugFinishedNum = 0;
        // step2:获取版本下自己提出的bug数量
        List<StaffIssueNum> totalBugNumList = chartDao.getTotalBugNumByCreateUser(param);
        Map<String, Long> tbnMap = new HashMap<>();
        for (StaffIssueNum bean : totalBugNumList) {
            if("tester".equals(bean.getRoleId())){
                tbnMap.put(bean.getUsername(), bean.getNum());
            }else {
                unTesterBugTotalNum += bean.getNum();
            }
        }
        List<StaffIssueNum> finishedBugNumList = chartDao.getFinishedBugNumByCreateUser(param);
        Map<String, Long> fbnMap = new HashMap<>();
        for (StaffIssueNum bean: finishedBugNumList) {
            if("tester".equals(bean.getRoleId())) {
                fbnMap.put(bean.getUsername(), bean.getNum());
            }else {
                unTesterBugFinishedNum += bean.getNum();
            }
        }

        //数据组装
        for (Map<String, String> map : list) {
            String username = map.get("username");
            long total = 0;
            long finished = 0;
            // 开发总任务量
            if (tinMap.containsKey(username)){
                total += tinMap.get(username);
            }
            // 开发完成任务量
            if (finMap.containsKey(username)){
                finished += finMap.get(username);
            }
            // 测试类任务总量
            if (tbnMap.containsKey(username)){
                total += tbnMap.get(username);
            }
            // 测试类完成的任务量
            if (fbnMap.containsKey(username)){
                finished += fbnMap.get(username);
            }
            // 如果是测试人员,每个人加上开发类任务数
            if ("tester".equals(map.get("role_id"))){
                total += totalTaskNum + unTesterBugTotalNum;
                finished += finishedTaskNum + unTesterBugFinishedNum;
            }
            map.put("total", String.valueOf(total));
            map.put("finished", String.valueOf(finished));
            if(total != 0){
                map.put("percent", String.valueOf((int) (finished*100/total)));
            }else {
                map.put("percent", "100");
            }

        }
        return list;
    }
}
