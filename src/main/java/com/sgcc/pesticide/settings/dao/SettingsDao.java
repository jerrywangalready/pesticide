package com.sgcc.pesticide.settings.dao;

import com.sgcc.pesticide.settings.model.Objects;
import com.sgcc.pesticide.settings.model.Users;

import java.util.List;

public interface SettingsDao {

    public List<Users> queryUsers();
    public List<Objects> queryObject();

}
