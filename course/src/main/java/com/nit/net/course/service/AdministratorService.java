package com.nit.net.course.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nit.net.course.entity.Administrator;
import com.nit.net.course.mapper.AdministratorMapper;
import com.nit.net.course.service.exception.DataException;
import com.nit.net.course.service.exception.PasswordErrorException;
import com.nit.net.course.service.exception.SecretKeyErrorException;
import com.nit.net.course.service.exception.UserConflictException;
import com.nit.net.course.service.exception.UserNotFoundException;
import com.nit.net.course.service.exception.UsernameFormatException;
import com.nit.net.course.util.TextValidator;

@Service
public class AdministratorService {
	
	@Autowired
	private AdministratorMapper mapper;
	
	/**
	 * 管理员注册功能
	 * @param administrator 管理员信息
	 * @throws UserConflictException 用户已存在异常
	 * @throws UsernameFormatException 用户名格式不正确异常
	 */
	public void reg(Administrator administrator) throws DataException,UserConflictException,UsernameFormatException {
		//通过用户名获取管理员信息
		Administrator result = mapper.findByUsername(administrator.getUsername());
		if (!TextValidator.checkUsername(administrator.getUsername())) {
			throw new UsernameFormatException("你尝试注册的用户名格式不正确！");
		}else if (result!=null) {
			//如果返回值不为空，表示用户已存在，则不允许注册
			throw new UserConflictException("尝试注册用户已存在！");
		}else if (!"000000".equals(administrator.getSecretKey())) {
			throw new SecretKeyErrorException("密钥输入错误");
		}else {
			//用户不存在，将密码加密后进行注册
			String new_password = getEncrpytedPassword(administrator.getPassword());
			administrator.setPassword(new_password);
			Integer rows = mapper.register(administrator);
			if (rows!=1) {
				throw new DataException("数据异常,请联系系统管理员!");
			}
		}
	}
	
	/**
	 * 管理员登录
	 * @param username
	 * @param password
	 * @throws UserNotFoundException
	 * @throws PasswordErrorException
	 */
	public Administrator login(String username,String password) throws UserNotFoundException,PasswordErrorException {
		//通过用户名查询用户
		Administrator administrator = mapper.findByUsername(username);
		if (administrator==null) {
			throw new UserNotFoundException("该用户不存在");
		}else {
			//将密码进行加密
			String new_password = getEncrpytedPassword(password);
			//与查询出来的密码进行比较
			if (!new_password.equals(administrator.getPassword())) {
				throw new PasswordErrorException("密码错误");
			}
		}
		return administrator;
	}
	
	
	
	
	
	/**
	 * 将密码加密
	 * @param password 加密前的密码
	 * @return 加密后的密码
	 */
	private String getEncrpytedPassword(String password) {
		return DigestUtils.md5Hex(password).toUpperCase();
	}
	
}
