package com.sgcc.pesticide.login.service.impl;

import com.sgcc.pesticide.login.dao.UserDao;
import com.sgcc.pesticide.login.model.User;
import com.sgcc.pesticide.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDAO;

	@Override
	public int insertUser(User user) {
		// TODO Auto-generated method stub
		return userDAO.insertUser(user);
	}

	public User getUser(String id){
		return  userDAO.getUser(id);
	}

}
