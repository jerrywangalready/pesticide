package com.sgcc.pesticide.settings.service.impl;

import com.sgcc.pesticide.settings.dao.SettingsDao;
import com.sgcc.pesticide.settings.model.Objects;
import com.sgcc.pesticide.settings.model.Users;
import com.sgcc.pesticide.settings.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SettingsServiceImpl implements SettingsService {

	@Autowired
	SettingsDao settingsDao;
	public List<Users> queryUsersList(){
		List<Users> list = settingsDao.queryUsers();
		return list;
	}
	public List<Objects> queryObjectList(){
		List<Objects> list = settingsDao.queryObject();
		return list;
	}

}
