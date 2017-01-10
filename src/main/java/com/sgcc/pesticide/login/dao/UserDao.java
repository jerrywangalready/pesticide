package com.sgcc.pesticide.login.dao;


import com.sgcc.pesticide.login.model.User;
import com.sgcc.pesticide.login.model.UserToken;
import org.springframework.stereotype.Repository;

import java.util.Map;

public interface UserDao {


    /**
     * 添加新用户
     * @param user
     * @return
     */
    public int insertUser(User user);

    public User getUser(String id);

    public UserToken checkUser(Map<String, String> param);
	
}
