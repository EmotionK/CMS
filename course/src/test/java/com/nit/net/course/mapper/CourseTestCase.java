package com.nit.net.course.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nit.net.course.VO.CourseTeacherVO;
import com.nit.net.course.entity.Course;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseTestCase {
	
	@Autowired
	private CourseMapper courseMapper;
	
	@Test
	public void select() {
		List<Course> list = courseMapper.select(6);
		for (Course course : list) {
			System.out.println(course);
		}
	}
	
	
	@Test
	public void findById() {
		Course course = courseMapper.findById(4);
		System.out.println(course);
	}
	
	
	@Test
	public void findByName() {
		String name = "PHP";
		Integer tId = 6;
		Course course = courseMapper.findByName(name, tId);
		System.out.println(course);
	}
	
	@Test
	public void findAll() {
		List<CourseTeacherVO> list = courseMapper.findAll();
		for (CourseTeacherVO courseTeacherVO : list) {
			System.err.println(courseTeacherVO);
		}
	}
	
	@Test
	public void showOptionCourse() {
		Integer sId = 7;
		List<CourseTeacherVO> list = courseMapper.showOptionCourse(sId );
		for (CourseTeacherVO courseTeacherVO : list) {
			System.err.println(courseTeacherVO);
		}
	}
	
	@Test
	public void findByStuTnameCname() {
		String name = "映心";
		Integer sId = 7;
		List<CourseTeacherVO> list = courseMapper.findByStuTnameCname(name, sId);
		for (CourseTeacherVO courseTeacherVO : list) {
			System.err.println(courseTeacherVO);
		}
	}
	
	
	
}
