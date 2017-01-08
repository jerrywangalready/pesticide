package com.sgcc.pesticide.login.dao;


import com.sgcc.pesticide.login.model.User;
import org.springframework.stereotype.Repository;

public interface UserDao {


    /**
     * 添加新用户
     * @param user
     * @return
     */
    public int insertUser(User user);

    public User getUser(String id);
	
}
