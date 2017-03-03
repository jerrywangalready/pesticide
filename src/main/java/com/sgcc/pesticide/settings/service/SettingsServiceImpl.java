package com.sgcc.pesticide.settings.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sgcc.comm.model.Query;
import com.sgcc.comm.util.CommUtil;
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
		int pageSize=10;
		PageHelper.startPage(Integer.parseInt(param.get("pageNum")),pageSize);
		List<Users> list = settingsDao.queryUsers(param);
		Query query = new Query();
		query.setList(list);
		query.setPageNum(Integer.parseInt(param.get("pageNum")));
		query.setPageSize(pageSize);
		query.setTotal(((Page)list).getTotal());
		return query;
	}
	public List<Objects> queryObjectList(){
		List<Objects> list = settingsDao.queryObject();
		return list;
	}


	/**
	 * @Description 保存users方法
	 * @author 杜成皓
	 * @date 2017/3/1 22:18
	 * @param param
	 * @return
	 */
	@Override
	public String insertTask(Map<String, String> param){
		String uuid = CommUtil.getUUID();
		param.put("uuid",uuid);
		settingsDao.insertUsers(param);
		return uuid;
	}

	/**
	 * @Description 检查用户名是否存在
	 * @author 杜成皓
	 * @date 2017/3/1 22:25
	 * @param username
	 * @return
	 */
	@Override
	public Boolean checkIsExist(String username) {
		String count = settingsDao.checkIsExist(username);
		Boolean flag;
		if("0".equals(count)){
			flag=true;
		}else{
			flag=false;
		}
		return flag;
	}

}
