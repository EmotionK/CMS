package com.nit.net.course.entity;

import java.io.Serializable;

public class Student implements Serializable {
	
	private static final long serialVersionUID = 1050670102910956206L;
	private Integer id;
	private String username;
	private String password;
	private String name;
	private String gender;
	private String college;
	private String squad;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getSquad() {
		return squad;
	}
	public void setSquad(String squad) {
		this.squad = squad;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Student [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name
				+ ", gender=" + gender + ", college=" + college + ", squad=" + squad + "]";
	}
	
	
}
