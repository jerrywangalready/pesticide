package com.sgcc.pesticide.create.dao;

import javax.crypto.MacSpi;
import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 */
public interface CreationDao {

    /**
     * @Description 插入开发任务
     * @author JerryWang
     * @date 2017/1/27 17:22
     * @param param
     */
    void insertDevTask(Map<String, String> param);

    /**
     * @Description 修改开发任务
     * @author JerryWang
     * @date 2017/1/27 17:23
     * @param param
     */
    void updateDevTask(Map<String, String> param);

    /**
     * @Description 插入测试任务
     * @author JerryWang
     * @date 2017/1/27 17:22
     * @param param
     */
    void insertTestTask(Map<String, String> param);

    /**
     * @Description 修改测试任务
     * @author JerryWang
     * @date 2017/1/27 17:23
     * @param param
     */
    void updateTestTask(Map<String, String> param);

    /**
     * @Description 插入修改任务
     * @author JerryWang
     * @date 2017/1/27 17:22
     * @param param
     */
    void insertModTask(Map<String, String> param);

    /**
     * @Description 修改修改任务
     * @author JerryWang
     * @date 2017/1/27 17:23
     * @param param
     */
    void updateModTask(Map<String, String> param);

    /**
     * @Description 获取任务编号
     * @author JerryWang
     * @date 2017/1/27 21:47
     * @param param 项目类型及编号
     */
    String getTaskCode(Map<String, String> param);

    /**
     * @Description 获取可以链接的开发任务
     * @author JerryWang
     * @date 2017/1/29 14:58
     * @param param
     * @return
     */
    List<Map<String, String>> getLinkDevInfo(Map<String, String> param);

    /**
     * @Description 获取可以链接的测试任务
     * @author JerryWang
     * @date 2017/1/29 17:15
     * @param param
     * @return
     */
    List<Map<String, String>> getLinkTestInfo(Map<String, String> param);


    /**
     * @Description 获取新开发任务编号
     * @author JerryWang
     * @date 2017/2/1 23:33
     * @param param
     * @return
     */
    String getNewDevNum(Map<String, String> param);

    /**
     * @Description 获取新测试任务编号
     * @author JerryWang
     * @date 2017/2/1 23:35
     * @param param
     * @return
     */
    String getNewTestNum(Map<String, String> param);

    /**
     * @Description 获取新修改任务编号
     * @author JerryWang
     * @date 2017/2/1 23:36
     * @param param
     * @return
     */
    String getNewModNum(Map<String, String> param);


//    String test();

}
