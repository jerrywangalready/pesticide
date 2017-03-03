package com.sgcc.pesticide.settings.service;


import com.sgcc.comm.model.Query;
import com.sgcc.pesticide.settings.model.Objects;

import java.util.List;
import java.util.Map;

public interface SettingsService {
	public Query queryUsersList(Map<String, String> param);
	public List<Objects> queryObjectList();
	public String insertTask(Map<String, String> param);
	Boolean checkIsExist(String username);
}
