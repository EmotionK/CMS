package com.nit.net.course.entity;

import java.io.Serializable;

public class Course implements Serializable {

	private static final long serialVersionUID = -6063524977931500350L;
	private Integer id;
	private String name;
	private String time;
	private String location;
	private Integer tId;
	public Integer gettId() {
		return tId;
	}
	public void settId(Integer tId) {
		this.tId = tId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Course [id=" + id + ", name=" + name + ", time=" + time + ", location=" + location + ", tId=" + tId
				+ "]";
	}
	
	
	
	
}
