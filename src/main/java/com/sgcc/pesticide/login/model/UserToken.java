package com.sgcc.pesticide.login.model;

import org.apache.ibatis.type.Alias;

/**
 * 用户表
 * @author JerryWang
 */
@Alias("UserToken")
public class UserToken {

	private String uuid;
	private String username;
	private String password;
	private String nickname;
	private String isenable;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getIsenable() {
		return isenable;
	}

	public void setIsenable(String isenable) {
		this.isenable = isenable;
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
