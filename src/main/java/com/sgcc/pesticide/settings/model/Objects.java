package com.sgcc.pesticide.settings.model;

import org.apache.ibatis.type.Alias;

/**
 * * @Description
 * @author 杜成皓
 * @date 2017/1/20 9:41

 */
@Alias("Objects")
public class Objects {

	private String uuid;
	private String object_code;
	private String object_name;
	private String isEnable;
	private String createTime;

	public String getObject_name() {
		return object_name;
	}

	public void setObject_name(String object_name) {
		this.object_name = object_name;
	}

	public String getObject_code() {
		return object_code;
	}

	public void setObject_code(String object_code) {
		this.object_code = object_code;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
