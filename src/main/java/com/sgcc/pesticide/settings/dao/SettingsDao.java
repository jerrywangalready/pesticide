package com.sgcc.pesticide.settings.dao;

import com.sgcc.pesticide.settings.model.Objects;
import com.sgcc.pesticide.settings.model.Users;

import java.util.List;
import java.util.Map;

public interface SettingsDao {

    List<Users> queryUsers(Map<String, String> param);
    List<Objects> queryObject(Map<String, String> param);
    void insertUsers(Map<String ,String> param);

    /**
     * @Description 保存object
     * @author 杜成皓
     * @date 2017/4/7 23:16
     * @param param
     */
    void insertObject(Map<String,String> param);
    String checkIsExist(String username);
    String checkObjectName(String objectName);

    /**
     * @Description 通过uuid查询user对象
     * @author 杜成皓
     * @date 2017/4/9 23:03
     * @param uuid
     * @return
     */
    Users queryUserByUUID(String uuid);

    /**
     * @Description 通过uuid查询object对象
     * @author 杜成皓
     * @date 2017/4/9 23:05
     * @param uuid
     * @return
     */
    Objects queryObjectByUUID(String uuid);
    /**
     * @Description 修改一个user对象
     * @author 杜成皓
     * @date 2017/4/9 22:52
     * @param param
     */
    void updateUsers(Map<String,String> param);

    /**
     * @Description 修改一个object对象
     * @author 杜成皓
     * @date 2017/4/9 22:53
     * @param param
     */
    void updateObject(Map<String,String> param);

    /**
     * @Description 删除一个user对象
     * @author 杜成皓
     * @date 2017/3/15 23:14
     * @param uuid
     * @return
     */
    void deleteUser(String uuid);

    /**
     * @Description 删除一个object对象
     * @author 杜成皓
     * @date 2017/4/6 23:24
     * @param uuid
     */
    void deleteObject(String uuid);
}
