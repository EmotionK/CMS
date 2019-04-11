package com.nit.net.course.mapper;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nit.net.course.entity.Teacher;
import com.nit.net.course.entity.TeacherHomework;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeacherTestCase {
	
	@Autowired
	private TeacherMapper mapper;
	
	@Autowired
	private TeacherHomeworkMapper teacher;
	
	@Test
	public void select() {
		List<Teacher> teacher = mapper.findAll();
		for (Teacher teacher2 : teacher) {
			System.out.println(teacher2);
		}
	}
	
	@Test
	public void findCourseNameByTHId() {
		List<Map<String, String>> list = teacher.findCourseNameByTHId(4);
		for (Map<String, String> map : list) {
			for(String key : map.keySet()) {
				System.err.println(key+","+map.get(key));
			}
		}
	}
	
	
	
}
