package com.nit.net.course.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nit.net.course.entity.Administrator;
import com.nit.net.course.mapper.AdministratorMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdministratorTestCase {
	
	@Autowired
	private AdministratorMapper administratorMapper;
	
	@Test
	public void findByUsername() {
		String username = "3150123068";
		Administrator administrator = administratorMapper.findByUsername(username );
		System.err.println(administrator);
	}
	
	
	
}
