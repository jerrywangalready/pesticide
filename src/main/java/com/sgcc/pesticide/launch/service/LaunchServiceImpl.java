package com.sgcc.pesticide.launch.service;

import com.sgcc.pesticide.launch.dao.LaunchDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 * @create 2017/6/14.
 */
@Service
public class LaunchServiceImpl implements LaunchService {

    @Autowired
    LaunchDao launchDao;

    /**
     * @param param
     * @return
     * @Description 获取问题列表
     * @author JerryWang
     * @date 2017/6/14 17:34
     */
    public List<Map<String, Object>> getIssueList(Map<String, String> param) {
        // 获取问题列表
        List<Map<String, String>> list = launchDao.getIssueList(param);
        List<Map<String, Object>> returnList = new ArrayList<>();
        // 获取所有版本
        List<Map<String, String>> versionList = launchDao.getVersionList(param);
        for (Map<String, String> s : versionList) {
            Map<String, Object> laneMap = new HashMap<>();
            laneMap.put("VERSION_CODE",s.get("VERSION_CODE"));
            laneMap.put("IS_COMPLETE",s.get("IS_COMPLETE"));
            returnList.add(laneMap);
        }

        List<Map<String, String>> issueList = new ArrayList<>();
        String temp = "";
        if (list.size() > 0){
            temp = list.get(0).get("VERSION_CODE");
        }
        for (Map<String, String> m : list) {
            if(!temp.equals(m.get("VERSION_CODE"))){
                for (int i = 0; i < returnList.size(); i++) {
                    if (returnList.get(i).get("VERSION_CODE").equals(temp)){
                        returnList.get(i).put("issueList", issueList);
                    }
                }
//                Map<String, Object> laneMap = new HashMap<>();
//                laneMap.put("VERSION_CODE",temp);
//                laneMap.put("issueList",issueList);
//                returnList.add(laneMap);
                temp = m.get("VERSION_CODE");
                issueList = new ArrayList<>();
            }
            issueList.add(m);
        }
        Map<String, Object> laneMap = new HashMap<>();
        laneMap.put("VERSION_CODE",temp);
        laneMap.put("issueList",issueList);
        returnList.add(laneMap);

        return returnList;

    }

    /**
     * @param param
     * @return
     * @Description 修改版本号
     * @author JerryWang
     * @date 2017/6/15 14:03
     */
    public void changeVersionCode(Map<String, String> param) {
        launchDao.changeVersionCode(param);
    }

    /**
     * @param param
     * @return
     * @Description 获取问题清单by版本号
     * @author JerryWang
     * @date 2017/6/25 15:05
     */
    public List<Map<String, String>> getIssueListByVersionCode(Map<String, String> param) throws Exception {
        return launchDao.getIssueListByVersionCode(param);
    }

    /**
     * @param param
     * @Description 修改版本状态
     * @author JerryWang
     * @date 2017/6/25 15:08
     */
    public void updateVersionCodeState(Map<String, String> param) throws Exception {
        launchDao.updateVersionCodeState(param);
    }

    /**
     * @param param
     * @Description 修改任务状态
     * @author JerryWang
     * @date 2017/6/25 15:42
     */
    public void updateIssueState(Map<String, String> param) {
        param.put("issueType", "T");
        launchDao.updateIssueState(param);
        param.put("issueType", "B");
        launchDao.updateIssueState(param);
    }

    public List<Map<String, String>> getLaunchDetail(Map<String, String> param) {
        return launchDao.getLaunchDetail(param);
    }

    public boolean checkRole(Map<String, String> param) {
        int num = launchDao.checkRole(param);
        if(num == 0){
            return false;
        }else {
            return true;
        }
    }

    /**
     * @param obj
     * @return
     * @Description 获取可上线版本
     * @author JerryWang
     * @date 2017/7/21 16:51
     */
    public String getLaunchVersion(String obj) {
        List<String> list = launchDao.getLaunchVersion(obj);
        if(list.size() > 0){
            return list.get(0);
        }else {
            return "";
        }
    }

}
