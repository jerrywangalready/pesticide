package com.sgcc.pesticide.settings.service;


import com.sgcc.pesticide.settings.model.Objects;
import com.sgcc.pesticide.settings.model.Users;

import java.util.List;

public interface SettingsService {
	public List<Users> queryUsersList();
	public List<Objects> queryObjectList();
}
