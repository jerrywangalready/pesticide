package com.sgcc.pesticide.login.service;


import com.sgcc.pesticide.login.model.User;

public interface UserService {

	public int insertUser(User user);

	public User getUser(String id);
}
