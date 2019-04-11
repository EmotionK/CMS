package com.nit.net.course.entity;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class TeacherHomework implements Serializable {

	private static final long serialVersionUID = -3284668556603759540L;
	private Integer id;
	private String knowledge;
	private String uploadDate;
	private String homeworkContent;
	private Integer cId;
	private Integer tId;
	
	public String getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getKnowledge() {
		return knowledge;
	}
	public void setKnowledge(String knowledge) {
		this.knowledge = knowledge;
	}
	
	public String getHomeworkContent() {
		return homeworkContent;
	}
	public void setHomeworkContent(String homeworkContent) {
		this.homeworkContent = homeworkContent;
	}
	public Integer getcId() {
		return cId;
	}
	public void setcId(Integer cId) {
		this.cId = cId;
	}
	public Integer gettId() {
		return tId;
	}
	public void settId(Integer tId) {
		this.tId = tId;
	}
	@Override
	public String toString() {
		return "TeacherHomework [id=" + id + ", knowledge=" + knowledge + ", uploadDate=" + uploadDate
				+ ", homeworkContent=" + homeworkContent + ", cId=" + cId + ", tId=" + tId + "]";
	}
	
	
}
