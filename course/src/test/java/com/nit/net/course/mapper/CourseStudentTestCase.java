package com.nit.net.course.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nit.net.course.entity.Student;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseStudentTestCase {
	
	@Autowired
	private CourseStudentMapper courseStudentMapper;
	
	@Test
	public void findStudentByUsernameNameSquad() {
		Integer cId = 8;
		String name = "123123";
		List<Student> list = courseStudentMapper.findStudentByUsernameNameSquad(cId, name);
		for (Student student : list) {
			System.err.println(student);
		}
	}
	
}
