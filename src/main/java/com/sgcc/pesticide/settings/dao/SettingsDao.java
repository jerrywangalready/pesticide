package com.sgcc.pesticide.settings.dao;

import com.sgcc.pesticide.settings.model.Objects;
import com.sgcc.pesticide.settings.model.Users;

import java.util.List;
import java.util.Map;

public interface SettingsDao {

    List<Users> queryUsers(Map<String, String> param);
    List<Objects> queryObject(Map<String, String> param);
    void insertUsers(Map<String ,String> param);
    String checkIsExist(String username);
    Users queryUserByUUID(String uuid);
    void updateUsers(Map<String,String> param);

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
