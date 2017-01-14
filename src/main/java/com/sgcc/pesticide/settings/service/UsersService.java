package com.sgcc.pesticide.settings.service;


import com.sgcc.pesticide.login.model.User;
import com.sgcc.pesticide.login.model.UserToken;
import com.sgcc.pesticide.settings.model.Users;

import java.util.List;

public interface UsersService {
	public List<Users> queryUsersList();
}
