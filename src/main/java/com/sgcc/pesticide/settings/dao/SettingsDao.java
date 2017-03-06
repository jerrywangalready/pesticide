package com.sgcc.pesticide.settings.dao;

import com.sgcc.pesticide.settings.model.Objects;
import com.sgcc.pesticide.settings.model.Users;

import javax.xml.registry.infomodel.User;
import java.util.List;
import java.util.Map;

public interface SettingsDao {

    List<Users> queryUsers(Map<String, String> param);
    List<Objects> queryObject();
    void insertUsers(Map<String ,String> param);
    String checkIsExist(String username);
    Users queryUserByUUID(String uuid);
    String  checkUuid(String uuid);
}
