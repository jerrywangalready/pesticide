package com.sgcc.pesticide.login.model;

/**
 * 用户表
 * @author fenghaifeng
 * 2014年2月11日
 */
public class User {

	private String uuid;
	private String username;
	private String password;
	private String is_enable;

	public void setIs_enable(String is_enable) {
		this.is_enable = is_enable;
	}

	public String getIs_enable() {
		return is_enable;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUuid() {
		return uuid;
	}
}
