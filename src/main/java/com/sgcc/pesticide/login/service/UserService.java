package com.sgcc.pesticide.login.service;


import com.sgcc.pesticide.login.model.User;
import com.sgcc.pesticide.login.model.UserToken;

public interface UserService {

	public int insertUser(User user);

	public User getUser(String id);

	public UserToken checkUser(String username, String password);
}
