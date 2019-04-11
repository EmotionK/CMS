package com.nit.net.course.entity;

import java.io.Serializable;

public class StudentSUH implements Serializable {

	private static final long serialVersionUID = -268561770290711938L;
	
	private Integer id;
	private Integer sId;
	private Integer suhId;
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
	public Integer getSuhId() {
		return suhId;
	}
	public void setSuhId(Integer suhId) {
		this.suhId = suhId;
	}
	@Override
	public String toString() {
		return "StudentSUH [id=" + id + ", sId=" + sId + ", suhId=" + suhId + "]";
	}
	
	
	
}
