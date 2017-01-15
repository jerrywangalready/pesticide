package com.sgcc.pesticide.login.dao;


import com.sgcc.pesticide.login.model.UserToken;

import java.util.Map;

public interface LoginDao {

    /**
     * @param param
     * @return
     */
    public UserToken checkUser(Map<String, String> param);
	
}
