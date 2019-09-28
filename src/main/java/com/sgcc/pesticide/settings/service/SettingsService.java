package com.sgcc.pesticide.settings.service;


import com.github.pagehelper.PageInfo;
import com.sgcc.pesticide.settings.model.Objects;
import com.sgcc.pesticide.settings.model.Users;

import java.util.List;
import java.util.Map;

/**
 *
 */
public interface SettingsService {
	PageInfo queryUsersList(Map<String, String> param);
	/**
	 * * @Description 查询object
	 * @author 杜成皓
	 * @date 2017/4/6 23:06
	 * @param param
	 * @return
	 */
	PageInfo queryObjectList(Map<String, String> param);

	/**
	 * @Description 插入user
	 * @author 杜成皓
	 * @date 2017/4/7 23:09
	 * @param param
	 * @return
	 */
	String insertTask(Map<String, String> param);

	/**
	 * @Description 插入object
	 * @author 杜成皓
	 * @date 2017/4/7 23:13
	 * @param param
	 * @return
	 */
	String insertObject(Map<String ,String> param);

	/**
	 * @Description 查询项目名称是否已存在
	 * @author 杜成皓
	 * @date 2017/4/7 23:01
	 * @param objectName
	 * @return
	 */
	Boolean checkObjectName(String objectName);
	Boolean checkIsExist(String username);

	/**
	 * @Description 通过uuid查询users对象
	 * @author 杜成皓
	 * @date 2017/4/9 23:02
	 * @param uuid
	 * @return
	 */
	Users queryUserByUUID(String uuid);

	/**
	 * @Description 通过uuid查询object对象
	 * @author 杜成皓
	 * @date 2017/4/9 23:03
	 * @param uuid
	 * @return
	 */
	Objects queryObjectByUUID(String uuid);

	/**
	 * @Description 修改人员信息
	 * @author 杜成皓
	 * @date 2017/4/9 22:51
	 * @param param
	 */
	void updateTask(Map<String,String> param);

	/**
	 * @Description 修改项目信息
	 * @author 杜成皓
	 * @date 2017/4/9 22:51
	 * @param param
	 */
	void updateObject(Map<String,String> param);

	/**
	 * @Description 删除一个user对象
	 * @author 杜成皓
	 * @date 2017/3/15 23:11
	 * @param uuid
	 * @return
	 */
	String deleteUser(String uuid);

	/**
	 * @Description 删除一个object对象
	 * @author 杜成皓
	 * @date 2017/4/6 23:24
	 * @param uuid
	 * @return
	 */
	String deleteObject(String uuid);
}
