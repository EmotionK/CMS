package com.nit.net.course.service;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nit.net.course.entity.Teacher;
import com.nit.net.course.mapper.TeacherMapper;
import com.nit.net.course.service.exception.DataException;
import com.nit.net.course.service.exception.PasswordErrorException;
import com.nit.net.course.service.exception.UserConflictException;
import com.nit.net.course.service.exception.UserNotFoundException;
import com.nit.net.course.service.exception.UsernameFormatException;
import com.nit.net.course.util.TextValidator;

@Service
public class TeacherService {
	
	@Autowired
	private TeacherMapper mapper;
	
	/**
	 * 教师注册
	 * @param teacher
	 * @throws UserConflictException
	 * @throws UsernameFormatException
	 */
	public void reg(Teacher teacher) throws UserConflictException,UsernameFormatException {
		if (!TextValidator.checkUsername(teacher.getUsername())) {
			throw new UsernameFormatException("用户名格式不正确");
		}
		//获得老师的信息
		Teacher result = mapper.findByUsername(teacher.getUsername());
		//如果查询到信息，则表示用户已存在，不允许注册
		if (result!=null) {
			throw new UserConflictException("用户已存在");
		}else {
			//表示用户不存在，允许注册
			String new_password = getEncrpytedPassword(teacher.getPassword());
			teacher.setPassword(new_password);
			Integer rows = mapper.regiter(teacher);
		}
		
	}
	
	/**
	 * 根据id查询教师信息
	 * @param id
	 * @return
	 */
	public Teacher findById(Integer id) throws DataException {
		//获得教师信息
		Teacher teacher = mapper.findById(id);
		if (teacher==null) {
			throw new DataException("数据异常,请联系系统管理员!");
		}
		return teacher;
	}
	
	/**
	 * 通过姓名查询教师信息
	 * @param name
	 * @return
	 * @throws UserNotFoundException
	 */
	public Teacher findByName(String name) throws UserNotFoundException {
		//获得教师信息
		Teacher teacher = mapper.findByName(name);
		//如果没查到,则没有这个教师
		if (teacher==null) {
			throw new UserNotFoundException("您想查询的用户不存在!");
		}else {
			return teacher;
		}
	}
	
	/**
	 * 教师登录
	 * @param username
	 * @param password
	 * @return
	 * @throws PasswordErrorException
	 * @throws UserNotFoundException
	 */
	public Teacher login(String username,String password) throws PasswordErrorException,UserNotFoundException {
		//根据用户名查询教师信息
		Teacher teacher = mapper.findByUsername(username);
		if (teacher!=null) {
			//如果有此教师,则验证密码是否正确
			//将登陆密码进行加密
			String new_password = getEncrpytedPassword(password);
			//与查询出的密码进行比较
			if (new_password.equals(teacher.getPassword())) {
				//如果相等,则登陆成功
				return teacher;
			}else {
				throw new PasswordErrorException("密码错误!");
			}
		}else {
			throw new UserNotFoundException("此用户不存在!");
		}
	}
	
	
	/**
	 * 查询所有教师信息
	 * @return
	 */
	public List<Teacher> select() {
		List<Teacher> teacher = mapper.findAll();;
		return teacher;
	}
	
	/**
	 * 根据id删除教师信息
	 * @param id
	 * @return
	 */
	public Integer deleteById(Integer id) throws DataException {
		//通过id删除教师信息
		Integer rows = mapper.deleteById(id);
		if (rows!=1) {
			throw new DataException("删除数据异常,请联系系统管理员!");
		}
		return rows;
	}
	
	/**
	 * 根据id修改教师信息
	 * @param teacher
	 * @return
	 * @throws DataException
	 */
	public Integer update(Teacher teacher) throws DataException {
		//进行修改
		Integer rows = mapper.update(teacher);
		if (rows!=1) {
			throw new DataException("修改数据失败,请联系系统管理员!");
		}
		return rows;
	}
	
	/**
	 * 根据id修改教师密码
	 * @param id
	 * @param password
	 * @throws DataException
	 */
	public void updatePwd(Integer id,String old_password,String new_password) throws PasswordErrorException,DataException {
		//通过id查询教师信息
		Teacher teacher = mapper.findById(id);
		//将旧密码加密后与查询出的密码进行比对
		if (getEncrpytedPassword(old_password).equals(teacher.getPassword())) {
			Integer rows = mapper.updatePwd(id, getEncrpytedPassword(new_password));
			if (rows!=1) {
				throw new DataException("修改数据失败,请联系系统管理员!");
			}
		}else {
			throw new PasswordErrorException("密码输入错误");
		}
		
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
