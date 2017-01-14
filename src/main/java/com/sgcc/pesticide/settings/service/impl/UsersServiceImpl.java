package com.sgcc.pesticide.settings.service.impl;

import com.sgcc.pesticide.settings.dao.SettingsDao;
import com.sgcc.pesticide.settings.model.Users;
import com.sgcc.pesticide.settings.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	SettingsDao settingsDao;
	public List<Users> queryUsersList(){
		List<Users> list = settingsDao.queryUsers();
		return list;
	}

}
