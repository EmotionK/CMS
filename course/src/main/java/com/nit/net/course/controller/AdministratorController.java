package com.nit.net.course.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nit.net.course.entity.Administrator;
import com.nit.net.course.entity.ResponseResult;
import com.nit.net.course.service.AdministratorService;

@RestController
@RequestMapping("/administrator")
public class AdministratorController extends BaseController {
	
	@Autowired
	private AdministratorService service;
	
	/**
	 * 管理员注册
	 * @param administrator 管理员
	 * @return
	 */
	@RequestMapping(value="/reg.do",method=RequestMethod.POST)
	public ResponseResult<Void> reg(Administrator administrator){
		//进行注册
		service.reg(administrator);
		return new ResponseResult<Void>();
	}
	
	/**
	 * 登录
	 * @param username
	 * @param password
	 * @param jurisdiction
	 * @return
	 */
	@RequestMapping(value="/login.do",method=RequestMethod.POST)
	public ResponseResult<Void> login(String username,String password,String option,HttpSession session){
		Administrator administrator = service.login(username, password);
		session.setAttribute("aId", administrator.getId());
		session.setAttribute("option", option);
		return new ResponseResult<Void>();
	}
	
	
}
