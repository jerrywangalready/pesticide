package com.sgcc.pesticide.create.dao;

import javax.crypto.MacSpi;
import java.util.List;
import java.util.Map;

/**
 * @author jerrywang
 */
public interface CreationDao {

    /**
     * @Description 插入任务
     * @author JerryWang
     * @date 2017/1/27 17:22
     * @param param
     */
    void insertTask(Map<String, String> param);

    /**
     * @Description 修改任务
     * @author JerryWang
     * @date 2017/1/27 17:23
     * @param param
     */
    void updateTask(Map<String, String> param);

    /**
     * @Description 获取任务编号
     * @author JerryWang
     * @date 2017/1/27 21:47
     */
    int getTaskCode();

    /**
     * @Description insert a new bug info
     * @author JerryWang
     * @date 2017/1/27 17:22
     * @param param
     */
    void insertBug(Map<String, String> param);

    /**
     * @Description update the bug info
     * @author JerryWang
     * @date 2017/1/27 17:23
     * @param param
     */
    void updateBug(Map<String, String> param);

    /**
     * @Description get the max code of bug
     * @author JerryWang
     * @date 2017/4/8 09:22
     * @return
     */
    int getBugCode();

    /**
     * @Description 根据任务编号获取任务列表
     * @author JerryWang
     * @date 2017/3/25 23:44
     * @param param
     * @return
     */
    List<Map<String, String>> searchTask(Map<String, String> param);


    /**
     * @Description 新增版本信息
     * @author JerryWang
     * @date 2017/6/28 14:23
     * @param param
     * @return
     */
    void addVersion(Map<String, String> param);

    /**
     * @Description 保存附件欣欣
     * @author JerryWang
     * @date 2017/7/9 18:54
     * @param param
     */
    void saveAttachment(Map<String, String> param);

    /**
     * @Description 删除附件信息
     * @author JerryWang
     * @date 2017/7/10 23:48
     * @param uuid
     */
    void deleteAttachment(String uuid);
}
