package com.sgcc.pesticide.login.dao;


import com.sgcc.pesticide.login.model.UserToken;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface LoginDao {

    /**
     * @param param
     * @return
     */
    public UserToken checkUser(Map<String, String> param);
	
}
