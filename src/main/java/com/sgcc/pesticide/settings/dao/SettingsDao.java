package com.sgcc.pesticide.settings.dao;

import com.sgcc.pesticide.settings.model.Objects;
import com.sgcc.pesticide.settings.model.Users;

import java.util.List;
import java.util.Map;

public interface SettingsDao {

    public List<Users> queryUsers(Map<String, String> param);
    public List<Objects> queryObject();

}
