package com.nit.net.course.entity;

import java.io.Serializable;

public class StudentCourse implements Serializable {

	private static final long serialVersionUID = 2915143300051115380L;
	private Integer id;
	private Integer sId;
	private Integer cId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getsId() {
		return sId;
	}
	public void setsId(Integer sId) {
		this.sId = sId;
	}
	public Integer getcId() {
		return cId;
	}
	public void setcId(Integer cId) {
		this.cId = cId;
	}
	@Override
	public String toString() {
		return "StudentCourse [id=" + id + ", sId=" + sId + ", cId=" + cId + "]";
	}
	
}
