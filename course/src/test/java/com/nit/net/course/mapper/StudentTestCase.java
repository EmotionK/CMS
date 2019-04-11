package com.nit.net.course.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nit.net.course.entity.Student;
import com.nit.net.course.mapper.StudentMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentTestCase {
	
	@Autowired
	private StudentMapper studentMapper;
	
	
	@Test
	public void findAll() {
		List<Student> students = studentMapper.findAll();
		for (Student student : students) {
			System.out.println(student);
		}
	}
	
	@Test
	public void findByOther() {
		String other = "市场营销152";
		List<Student> students = studentMapper.findByOther(other );
		for (Student student : students) {
			System.out.println(student);
		}
	}
	
	
}
