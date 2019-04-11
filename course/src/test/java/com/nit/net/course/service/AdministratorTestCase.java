package com.nit.net.course.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nit.net.course.entity.Administrator;
import com.nit.net.course.service.exception.ServiceException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdministratorTestCase {
	
	@Autowired
	private AdministratorService service;
	
	@Test
	public void reg() {
		try {
			Administrator administrator = new Administrator();
			administrator.setUsername("315012306t");
			administrator.setPassword("201314");
			service.reg(administrator );
			System.out.println("ok");
		} catch (ServiceException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void login() {
		try {
			String username = "315012368";
			String password = "xl5255";
			service.login(username, password);
			System.out.println("ok");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	
}
