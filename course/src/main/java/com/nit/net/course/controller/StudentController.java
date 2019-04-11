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

import com.nit.net.course.VO.StudentScoreVO;
import com.nit.net.course.entity.ResponseResult;
import com.nit.net.course.entity.Student;
import com.nit.net.course.interceptor.LoginInterceptor;
import com.nit.net.course.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController extends BaseController {
	
	@Autowired
	private StudentService service;
	
	/**
	 * 获得学生的姓名和id
	 * @param session
	 * @return
	 */
	@RequestMapping("/getName.do")
	public ResponseResult<List<Object>> getTeacherName(HttpSession session){
		ResponseResult<List<Object>> responseResult = new ResponseResult<List<Object>>();
		List<Object> list = new ArrayList<Object>();
		//将学生id存入集合中
		list.add(session.getAttribute("sId"));
		//将学生姓名存入集合中
		list.add(session.getAttribute("sName"));
		responseResult.setData(list);
		return responseResult;
	}
	
	/**
	 * 注册学生信息
	 * @param student
	 * @return
	 */
	@RequestMapping(value="/reg.do",method=RequestMethod.POST)
	public ResponseResult<Void> register(Student student){
		service.register(student);
		return new ResponseResult<Void>();
	}
	
	/**
	 * 学生登录
	 * @param username
	 * @param password
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/login.do",method=RequestMethod.POST)
	public ResponseResult<Void> login(String username,String password,HttpSession session){
		Student student = service.login(username, password);
		//将学生id存入session中
		session.setAttribute("sId", student.getId());
		//将学生姓名存入session中
		session.setAttribute("sName", student.getName());
		return new ResponseResult<Void>();
	}
	
	/**
	 * 根据id查询学生信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/findById.do")
	public ResponseResult<Student> findById(Integer id){
		Student student = service.findById(id);
		ResponseResult<Student> responseResult = new ResponseResult<Student>();
		responseResult.setData(student);
		return responseResult;
	}
	
	/**
	 * 根据用户名,姓名或班级查询学生信息
	 * @param other
	 * @return
	 */
	@RequestMapping("/findByOther.do")
	public ResponseResult<List<Student>> findByOther(String other){
		ResponseResult<List<Student>> responseResult = new ResponseResult<List<Student>>();
		List<Student> students = service.findByOther(other);
		responseResult.setData(students);
		return responseResult;
	}
	
	/**
	 * 查询所有学生信息
	 * @return
	 */
	@RequestMapping("/findAll.do")
	public ResponseResult<List<Student>> findAll(){
		List<Student> students = service.findAll();
		ResponseResult<List<Student>> responseResult = new ResponseResult<List<Student>>();
		responseResult.setData(students);
		return responseResult;
	}
	
	/**
	 * 根据用户名修改学生信息
	 * @param student
	 * @return
	 */
	@RequestMapping(value="/update.do",method=RequestMethod.POST)
	public ResponseResult<Void> update(Student student){
		service.update(student);
		return new ResponseResult<Void>();
	}
	
	/**
	 * 根据id修改学生密码
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
	
	/**
	 * 根据id删除学生信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete.do")
	public ResponseResult<Void> dalete(Integer id){
		service.delete(id);
		return new ResponseResult<Void>();
	}
	
	/**
	 * 根据课程id查询学生成绩和学生信息
	 * @param cId
	 * @return
	 */
	@RequestMapping("/findScoreStudentByCid.do")
	public ResponseResult<List<StudentScoreVO>> findScoreStudentByCid(Integer cId){
		List<StudentScoreVO> list = service.findScoreStudentByCid(cId);
		ResponseResult<List<StudentScoreVO>> responseResult = new ResponseResult<List<StudentScoreVO>>();
		responseResult.setData(list);
		return responseResult;
	}
	
	/**
	 * 根据课程id查询学生成绩和学生信息
	 * @param cId
	 * @return
	 */
	@RequestMapping("/findScoreBySNameCName.do")
	public ResponseResult<List<StudentScoreVO>> findScoreBySNameCName(String name){
		List<StudentScoreVO> list = service.findScoreBySNameCName(name);
		ResponseResult<List<StudentScoreVO>> responseResult = new ResponseResult<List<StudentScoreVO>>();
		responseResult.setData(list);
		return responseResult;
	}
	
	
}
