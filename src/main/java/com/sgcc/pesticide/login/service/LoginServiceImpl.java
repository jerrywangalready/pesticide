package com.sgcc.pesticide.login.service;

import com.sgcc.pesticide.login.dao.LoginDao;
import com.sgcc.pesticide.login.model.UserToken;
import com.sgcc.pesticide.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao loginDao;

	/**
	 * @description 校验登录权限
	 * @param username
	 * @param password
	 * @return
	 */
	@Override
	public UserToken checkUser(String username, String password){
		Map<String, String> param = new HashMap<>();
		param.put("username",username);
		param.put("password",password);

		UserToken user = null;
		try {
			user = loginDao.checkUser(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

}
