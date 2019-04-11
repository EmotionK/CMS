package com.nit.net.course.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nit.net.course.entity.ResponseResult;
import com.nit.net.course.entity.Teacher;
import com.nit.net.course.service.TeacherService;

@RestController
@RequestMapping("/teacher")
public class TeacherController extends BaseController {
	
	@Autowired
	private TeacherService service;
	
	/**
	 * 获得教师的姓名和id
	 * @param session
	 * @return
	 */
	@RequestMapping("/getName.do")
	public ResponseResult<List<Object>> getTeacherName(HttpSession session){
		ResponseResult<List<Object>> responseResult = new ResponseResult<List<Object>>();
		List<Object> list = new ArrayList<Object>();
		//将教师id存入集合中
		list.add(session.getAttribute("tId"));
		//将教师姓名存入集合中
		list.add(session.getAttribute("tName"));
		responseResult.setData(list);
		return responseResult;
	}
	
	
	/**
	 * 教师注册
	 * @param teacher
	 * @return
	 */
	@RequestMapping(value="/reg.do",method=RequestMethod.POST)
	public ResponseResult<Void> reg(Teacher teacher){
		service.reg(teacher);
		return new ResponseResult<Void>();
	}
	
	/**
	 * 教师登录
	 * @param username
	 * @param password
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/login.do",method=RequestMethod.POST)
	public ResponseResult<Void> login(String username,String password,HttpSession session){
		Teacher teacher = service.login(username, password);
		//将教师id保存到session中
		session.setAttribute("tId", teacher.getId());
		//将教师姓名保存到session中
		session.setAttribute("tName", teacher.getName());
		return new ResponseResult<Void>();
	}
	
	
	/**
	 * 根据id查询教师信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/findById.do")
	public ResponseResult<Teacher> findById(Integer id){
		//查询到教师信息
		Teacher teacher = service.findById(id);
		ResponseResult<Teacher> responseResult = new ResponseResult<Teacher>();
		//将教师信息保存到data中
		responseResult.setData(teacher);
		return responseResult;
	}
	
	/**
	 * 根据教师姓名查询教师信息
	 * @param name
	 * @return
	 */
	@RequestMapping("/findByName.do")
	public ResponseResult<Teacher> findByName(String name){
		//查询到教师信息
		Teacher teacher = service.findByName(name);
		ResponseResult<Teacher> responseResult = new ResponseResult<Teacher>();
		//将教师信息保存到data中
		responseResult.setData(teacher);
		return responseResult;
	}
	
	
	/**
	 * 查询所有教师信息
	 * @return
	 */
	@RequestMapping("/selectAll.do")
	public ResponseResult<List<Teacher>> select(){
		//获取教师信息
		List<Teacher> teacher = service.select();
		ResponseResult<List<Teacher>> responseResult = new ResponseResult<List<Teacher>>();
		//将教师的信息赋予data
		responseResult.setData(teacher);
		return responseResult;
	}
	
	/**
	 * 根据id删除教师信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete.do")
	public ResponseResult<Void> deleteById(Integer id){
		service.deleteById(id);
		return new ResponseResult<Void>();
	}
	
	/**
	 * 根据id修改教师信息
	 * @param teacher
	 * @return
	 */
	@RequestMapping(value="/updata.do",method=RequestMethod.POST)
	public ResponseResult<Void> updata(Teacher teacher){
		service.update(teacher);
		return new ResponseResult<Void>();
	}
	
	/**
	 * 根据id修改教师密码
	 * @param id
	 * @param old_password
	 * @param new_password
	 * @return
	 */
	@RequestMapping(value="/updatePwd.do",method=RequestMethod.POST)
	public ResponseResult<Void> updatePwd(Integer id,String old_password,String new_password){
		service.updatePwd(id, old_password, new_password);
		return new ResponseResult<Void>();
	}
	
	
	
}
