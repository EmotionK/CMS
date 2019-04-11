package com.nit.net.course.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nit.net.course.entity.Teacher;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeacherTestCase {
	
	@Autowired
	private TeacherService service;
	
	@Test
	public void select() {
		List<Teacher> teachers = service.select();
		for (Teacher teacher : teachers) {
			System.out.println(teacher);
		}
	}
	
	
}
