package com.nit.net.course.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nit.net.course.VO.CourseTeacherVO;
import com.nit.net.course.entity.Course;
import com.nit.net.course.entity.ResponseResult;
import com.nit.net.course.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController extends BaseController {
	
	@Autowired
	private CourseService service;
	
	/**
	 * 注册课程
	 * @param course
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/reg.do",method=RequestMethod.POST)
	public ResponseResult<Void> reg(Course course,HttpSession session){
		course.settId((Integer)session.getAttribute("tId"));
		service.reg(course);
		return new ResponseResult<Void>();
	}
	
	/**
	 * 根据教师id查询课程
	 * @param session
	 * @return
	 */
	@RequestMapping("/select.do")
	public ResponseResult<List<Course>> select(HttpSession session){
		Integer tId = (Integer) session.getAttribute("tId");
		ResponseResult<List<Course>> responseResult = new ResponseResult<List<Course>>();
		List<Course> courses = service.select(tId);
		responseResult.setData(courses);
		return responseResult;
	}
	
	/**
	 * 根据id查询课程信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/findById.do")
	public ResponseResult<Course> findById(Integer id){
		ResponseResult<Course> responseResult = new ResponseResult<Course>();
		Course course = service.findById(id);
		responseResult.setData(course);
		return responseResult;
	}
	
	/**
	 * 根据课程名和教师id查询课程
	 * @param name
	 * @param session
	 * @return
	 */
	@RequestMapping("/findByName.do")
	public ResponseResult<Course> findByName(String name,HttpSession session){
		//获取教师的id
		Integer tId = (Integer) session.getAttribute("tId");
		ResponseResult<Course> responseResult = new ResponseResult<Course>();
		Course course = service.findByName(name, tId);
		responseResult.setData(course);
		return responseResult;
	}
	
	/**
	 * 根据id修改课程信息
	 * @param course
	 * @return
	 */
	@RequestMapping(value="updateById.do",method=RequestMethod.POST)
	public ResponseResult<Void> updateById(Course course){
		service.updateById(course);
		return new ResponseResult<Void>();
	}
	
	/**
	 * 根据id删除课程信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete.do")
	public ResponseResult<Void> delete(Integer id){
		service.delete(id);
		return new ResponseResult<Void>();
	}
	
	/**
	 * 查询所有课程和教师信息
	 * @return
	 */
	@RequestMapping("/findAll.do")
	public ResponseResult<List<CourseTeacherVO>> findAll(){
		List<CourseTeacherVO> list = service.findAll();
		ResponseResult<List<CourseTeacherVO>> responseResult = new ResponseResult<List<CourseTeacherVO>>();
		responseResult.setData(list);
		return responseResult;
	}
	
	/**
	 * 根据教师名或课程名查询课程和老师信息
	 * @param name
	 * @return
	 */
	@RequestMapping("/findByTCName.do")
	public ResponseResult<List<CourseTeacherVO>> findByTCName(String name){
		List<CourseTeacherVO> list = service.findByTCName(name);
		ResponseResult<List<CourseTeacherVO>> responseResult = new ResponseResult<List<CourseTeacherVO>>();
		responseResult.setData(list);
		return responseResult;
	}
	
	/**
	 * 显示学生选择的课程信息
	 * @param session
	 * @return
	 */
	@RequestMapping("/showOptionCourse.do")
	public ResponseResult<List<CourseTeacherVO>> shouOptionCourse(HttpSession session){
		//获取session中的学生id
		Integer sId = (Integer) session.getAttribute("sId");
		//获取查询出的课程信息
		List<CourseTeacherVO> list = service.showOptionCourse(sId);
		ResponseResult<List<CourseTeacherVO>> responseResult = new ResponseResult<List<CourseTeacherVO>>();
		responseResult.setData(list);
		return responseResult;
	}
	
	/**
	 * 根据课程名或教师名查询学生所选课程
	 * @param name
	 * @param session
	 * @return
	 */
	@RequestMapping("/findByStuTnameCname.do")
	public ResponseResult<List<CourseTeacherVO>> findByStuTnameCname(String name,HttpSession session){
		//获取session中的学生id
		Integer sId = (Integer)session.getAttribute("sId");
		//获取查询出的课程信息
		List<CourseTeacherVO> list = service.findByStuTnameCname(name, sId);
		ResponseResult<List<CourseTeacherVO>> responseResult = new ResponseResult<List<CourseTeacherVO>>();
		responseResult.setData(list);
		return responseResult;
	}
	
}
