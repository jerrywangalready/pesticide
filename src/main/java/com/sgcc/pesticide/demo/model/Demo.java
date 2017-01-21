package com.sgcc.pesticide.demo.model;

import org.apache.ibatis.type.Alias;

/**
 * * @Description
 * @author 杜成皓
 * @date 2017/1/20 9:41

 */
@Alias("Demo")
public class Demo {

	private String uuid;
	private String name;
	private String code;
	private String type;
	private String isEnable;
	private String createTime;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
