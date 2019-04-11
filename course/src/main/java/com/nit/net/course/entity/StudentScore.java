package com.nit.net.course.entity;

import java.io.Serializable;

public class StudentScore implements Serializable {

	private static final long serialVersionUID = -5732634334125098298L;

	
	private Integer id;
	private String score;
	private Integer suhId;
	private Integer sId;
	private Integer cId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public Integer getSuhId() {
		return suhId;
	}
	public void setSuhId(Integer suhId) {
		this.suhId = suhId;
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
		return "StudentScore [id=" + id + ", score=" + score + ", suhId=" + suhId + ", sId=" + sId + ", cId=" + cId
				+ "]";
	}
	
	
	
}
