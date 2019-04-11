package com.nit.net.course.VO;

import java.io.Serializable;
import java.util.List;

import com.nit.net.course.entity.Course;

public class CourseTeacherVO implements Serializable {

	private static final long serialVersionUID = -1015696363318560127L;
	
	private Integer id;
	private String username;
	private String password;
	private String name;
	private String gender;
	private Integer age;
	private String college;
	private String phone;
	private List<Course> courses;
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
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public List<Course> getCourses() {
		return courses;
	}
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "CourseTeacherVO [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name
				+ ", gender=" + gender + ", age=" + age + ", college=" + college + ", phone=" + phone + ", courses="
				+ courses + "]";
	}

	
}
