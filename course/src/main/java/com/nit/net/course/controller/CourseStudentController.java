package com.nit.net.course.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nit.net.course.entity.ResponseResult;
import com.nit.net.course.entity.Student;
import com.nit.net.course.entity.StudentCourse;
import com.nit.net.course.service.CourseStudentService;

@RestController
@RequestMapping("/courseStudent")
public class CourseStudentController extends BaseController {
	
	@Autowired
	private CourseStudentService service;
	
	/**
	 * 将学生和课程绑定
	 * @param cId
	 * @param session
	 * @return
	 */
	@RequestMapping("/optionCourse.do")
	public ResponseResult<Void> optionCourse(Integer cId,HttpSession session){
		StudentCourse studentCourse = new StudentCourse();
		//将课程id和学生id设置进studentCourse对象中
		studentCourse.setcId(cId);
		studentCourse.setsId((Integer)session.getAttribute("sId"));
		//调用业务层方法
		service.optionCourse(studentCourse);
		return new ResponseResult<Void>();
	}
	
	/**
	 * 根据学生id和课程id删除信息
	 * @param cId
	 * @param session
	 * @return
	 */
	@RequestMapping("/deleteBySidCid.do")
	public ResponseResult<Void> deleteBySidCid(Integer sId,Integer cId,HttpSession session){
		//判断是否由ajax传入学生id,若是,则证明是老师操作,若不是,则证明是学生操作
		if (sId==null) {
			//学生操作,获取学生id
			sId = (Integer)session.getAttribute("sId");
		}
		//调用业务层方法
		service.deleteBySidCid(sId, cId);
		return new ResponseResult<Void>();
	}
	
	/**
	 * 根据课程id查询选择此课程的所有学生信息
	 * @param cId
	 * @return
	 */
	@RequestMapping("/findStudentByCid.do")
	public ResponseResult<List<Student>> findStudentByCid(Integer cId){
		//获得查询的学生信息
		List<Student> list = service.findStudentByCid(cId);
		ResponseResult<List<Student>> responseResult = new ResponseResult<List<Student>>();
		responseResult.setData(list);
		return responseResult;
	}
	
	/**
	 * 根据学生用户名或姓名或班级查询选择此课程的所有学生信息
	 * @param cId
	 * @param name
	 * @return
	 */
	@RequestMapping("/findStudentByUsernameNameSquad.do")
	public ResponseResult<List<Student>> findStudentByUsernameNameSquad(Integer cId,String name){
		//获得查询的学生信息
		List<Student> list = service.findStudentByUsernameNameSquad(cId, name);
		ResponseResult<List<Student>> responseResult = new ResponseResult<List<Student>>();
		responseResult.setData(list);
		return responseResult;
	}
	
}
