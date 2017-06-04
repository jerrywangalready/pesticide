package com.sgcc.pesticide.push.service;

import com.sgcc.comm.model.Query;

import java.util.List;
import java.util.Map;

/**
 * Created by DCH on 2017/5/2.
 */
public interface PushService {

    /**
     * @Description 查询推送列表
     * @author 杜成皓
     * @date 2017/5/3 23:21
     * @param param
     * @return
     */
    List<Map<String, String>> getPushList(Map<String,String> param);

    /**
     * @Description 二级列表
     * @author 杜成皓
     * @date 2017/5/11 23:20
     * @param param
     * @return
     */
    List<Map<String,String>> getPushDetail(Map<String, String> param);

    /**
     * @Description 发布
     * @author JerryWang
     * @date 2017/5/14 15:41
     * @param param
     * @return
     */
    boolean publish(Map<String, Object> param);

    /**
     * @Description 获取任务uuid
     * @author JerryWang
     * @date 2017/6/1 17:24
     * @param param
     * @return
     */
    List<Map<String,String>> getTaskUuid(Map<String, Object> param);
}
