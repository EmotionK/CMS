package com.nit.net.course.entity;

import java.io.Serializable;

public class StudentUploadHomework implements Serializable {

	private static final long serialVersionUID = -4655003825881069692L;
	
	private Integer id;
	private String homeworkContent;
	private Integer thId;
	private Integer sId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getHomeworkContent() {
		return homeworkContent;
	}
	public void setHomeworkContent(String homeworkContent) {
		this.homeworkContent = homeworkContent;
	}
	public Integer getThId() {
		return thId;
	}
	public void setThId(Integer thId) {
		this.thId = thId;
	}
	
	public Integer getsId() {
		return sId;
	}
	public void setsId(Integer sId) {
		this.sId = sId;
	}
	@Override
	public String toString() {
		return "StudentUploadHomework [id=" + id + ", homeworkContent=" + homeworkContent + ", thId=" + thId + ", sId="
				+ sId + "]";
	}
	
	
	
}
