package com.nit.net.course.entity;

import java.io.Serializable;

public class Administrator implements Serializable {
	private static final long serialVersionUID = 3569462027862612833L;
	private Integer id;
	private String username;
	private String password;
	private String secretKey;
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Administrator [id=" + id + ", username=" + username + ", password=" + password + ", secretKey="
				+ secretKey + "]";
	}
	
	
}
