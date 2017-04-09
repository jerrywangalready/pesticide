package com.sgcc.pesticide.settings.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sgcc.comm.model.Query;
import com.sgcc.comm.util.CommUtil;
import com.sgcc.pesticide.settings.dao.SettingsDao;
import com.sgcc.pesticide.settings.model.Objects;
import com.sgcc.pesticide.settings.model.Users;
import org.omg.CORBA.OBJ_ADAPTER;
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

	/**
	 * * @Description 查询object
	 * @author 杜成皓
	 * @date 2017/4/6 23:06
	 * @param param
	 * @return
	 */
	public Query queryObjectList(Map<String, String> param){
		int pageSize=10;
		PageHelper.startPage(Integer.parseInt(param.get("pageNum")),pageSize);
		List<Objects> list = settingsDao.queryObject(param);
		Query query = new Query();
		query.setList(list);
		query.setPageNum(Integer.parseInt(param.get("pageNum")));
		query.setPageSize(pageSize);
		query.setTotal(((Page)list).getTotal());
		return query;
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
	 * @param param
	 * @return
	 * @Description 插入object
	 * @author 杜成皓
	 * @date 2017/4/7 23:11
	 */
	@Override
	public String insertObject(Map<String, String> param) {
		String uuid = CommUtil.getUUID();
		param.put("uuid",uuid);
		settingsDao.insertObject(param);
		return uuid;
	}


	/**
	 * @Description 校验项目名称是否重复
	 * @author 杜成皓
	 * @date 2017/4/7 23:12
	 * @param objectName
	 * @return
	 */
	@Override
	public Boolean checkObjectName(String objectName) {
		String count = settingsDao.checkObjectName(objectName);
		Boolean flag;
		if ("0".equals(count)){
			flag=true;
		}else{
			flag=false;
		}
		return flag;
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

	/**
	 * @Description 根据uuid查询user对象
	 * @author 杜成皓
	 * @date 2017/3/6 22:15
	 * @param uuid
	 * @return
	 */
	@Override
	public Users queryUserByUUID(String uuid) {
		Users users=settingsDao.queryUserByUUID(uuid);
		return users;
	}

	/**
	 * @param uuid
	 * @return
	 * @Description 通过uuid查询object对象
	 * @author 杜成皓
	 * @date 2017/4/9 23:02
	 */
	@Override
	public Objects queryObjectByUUID(String uuid) {
		Objects objects=settingsDao.queryObjectByUUID(uuid);
		return objects;
	}

	/**
	 * @Description 修改一个user对象
	 * @author 杜成皓
	 * @date 2017/3/15 23:11
	 * @param param
	 */
	@Override
	public void updateTask(Map<String, String> param) {
		settingsDao.updateUsers(param);
	}

	/**
	 * @param param
	 * @Description 修改项目信息
	 * @author 杜成皓
	 * @date 2017/4/9 22:51
	 */
	@Override
	public void updateObject(Map<String, String> param) {
		settingsDao.updateObject(param);
	}

	/**
	 * @Description 删除一个user对象
	 * @author 杜成皓
	 * @date 2017/3/15 23:13
	 * @param uuid
	 * @return
	 */
	@Override
	public String deleteUser(String uuid) {
		try {
			settingsDao.deleteUser(uuid);
		} catch (Exception e) {
			e.printStackTrace();
			return "false";
		}
		return "true";
	}

	/**
	 * @Description 删除一个object对象
	 * @author 杜成皓
	 * @date 2017/4/6 23:26
	 * @param uuid
	 * @return
	 */
	@Override
	public String deleteObject(String uuid){
		try{
			settingsDao.deleteObject(uuid);
		}catch (Exception e){
			e.printStackTrace();
			return "false";
		}
		return "true";
	}
}
