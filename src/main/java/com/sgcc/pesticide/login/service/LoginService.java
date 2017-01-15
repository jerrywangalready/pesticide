package com.sgcc.pesticide.login.service;


import com.sgcc.pesticide.login.model.UserToken;

public interface LoginService {

	/**
	 * @Description 校验用户是否可以登录
	 * @param username 账号
	 * @param password 密码
	 * @return 
	 * @author JerryWang
	 * @date 2017/1/15 10:30
	 */
	public UserToken checkUser(String username, String password);
}
