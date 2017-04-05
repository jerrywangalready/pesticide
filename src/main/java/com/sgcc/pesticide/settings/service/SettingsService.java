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
	void updateTask(Map<String,String> param);

	/**
	 * @Description 删除一个user对象
	 * @author 杜成皓
	 * @date 2017/3/15 23:11
	 * @param uuid
	 * @return
	 */
	String deleteUser(String uuid);
}
