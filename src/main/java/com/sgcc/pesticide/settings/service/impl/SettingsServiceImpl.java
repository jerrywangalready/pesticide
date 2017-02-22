package com.sgcc.pesticide.settings.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sgcc.comm.model.Query;
import com.sgcc.pesticide.settings.dao.SettingsDao;
import com.sgcc.pesticide.settings.model.Objects;
import com.sgcc.pesticide.settings.model.Users;
import com.sgcc.pesticide.settings.service.SettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class SettingsServiceImpl implements SettingsService {

	@Autowired
	SettingsDao settingsDao;
	public Query queryUsersList(Map<String, String> param){
		PageHelper.startPage(Integer.parseInt(param.get("pageNum")),3);
		List<Users> list = settingsDao.queryUsers(param);
		Query query = new Query();
		query.setList(list);
		query.setPageNum(Integer.parseInt(param.get("pageNum")));
		query.setPageSize(3);
		query.setTotal(((Page)list).getTotal());
		return query;
	}
	public List<Objects> queryObjectList(){
		List<Objects> list = settingsDao.queryObject();
		return list;
	}

}
