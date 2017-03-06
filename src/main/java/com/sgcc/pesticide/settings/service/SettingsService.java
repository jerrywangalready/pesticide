package com.sgcc.pesticide.settings.service;


import com.sgcc.comm.model.Query;
import com.sgcc.pesticide.settings.model.Objects;
import com.sgcc.pesticide.settings.model.Users;

import java.util.List;
import java.util.Map;

public interface SettingsService {
	Query queryUsersList(Map<String, String> param);
	List<Objects> queryObjectList();
	String insertTask(Map<String, String> param);
	Boolean checkIsExist(String username);
	Users queryUserByUUID(String uuid);
}
