package com.nit.net.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nit.net.course.VO.CourseTeacherVO;
import com.nit.net.course.entity.Course;
import com.nit.net.course.mapper.CourseMapper;
import com.nit.net.course.service.exception.CourseNotFoundExceptin;
import com.nit.net.course.service.exception.DataException;

@Service
public class CourseService {
	
	@Autowired
	private CourseMapper mapper;
	
	/**
	 * 课程注册
	 * @param course
	 * @return
	 */
	public Integer reg(Course course) throws DataException {
		Integer rows = mapper.reg(course);
		if (rows!=1) {
			throw new DataException("插入数据错误,请联系系统管理员!");
		}
		return rows;
	}

	/**
	 * 根据教师id查询所有课程
	 * @param tId
	 * @return
	 */
	public List<Course> select(Integer tId){
		List<Course> courses = mapper.select(tId);
		return courses;
	}
	
	/**
	 * 根据id查询课程信息
	 * @param id
	 * @return
	 * @throws DataException
	 */
	public Course findById(Integer id) throws DataException {
		//根据id查询课程信息
		Course course = mapper.findById(id);
		if (course==null) {
			throw new DataException("数据异常，请联系系统管理员!");
		}
		return course;
	}
	
	/**
	 * 根据课程名和教师id查询课程
	 * @param name
	 * @param id
	 * @return
	 */
	public Course findByName(String name,Integer tId) throws CourseNotFoundExceptin {
		Course course = mapper.findByName(name, tId);
		if (course==null) {
			throw new CourseNotFoundExceptin("该课程不存在!");
		}
		return course;
	}
	
	
	/**
	 * 根据id修改课程信息
	 * @param course
	 * @throws DataException
	 */
	public void updateById(Course course) throws DataException {
		Integer rows = mapper.updateById(course);
		if (rows!=1) {
			throw new DataException("数据异常,请联系系统管理员!");
		}
	}
	
	/**
	 * 根据id删除数据
	 * @param id
	 * @throws DataException
	 */
	public void delete(Integer id) throws DataException {
		Integer rows = mapper.delete(id);
		if (rows!=1) {
			throw new DataException("数据异常,请联系系统管理员!");
		}
	}
	
	/**
	 * 查询所有课程和教师信息
	 * @return
	 */
	public List<CourseTeacherVO> findAll(){
		return mapper.findAll();
	}
	
	/**
	 * 根据教师名或课程名查询课程和老师信息
	 * @param name
	 * @return
	 * @throws CourseNotFoundExceptin
	 */
	public List<CourseTeacherVO> findByTCName(String name) throws CourseNotFoundExceptin{
		List<CourseTeacherVO> list = mapper.findByTCName(name);
		//判断是否查询出信息
		if (list.isEmpty()) {
			throw new CourseNotFoundExceptin("查找的内容不存在!");
		}
		return list;
	}
	
	/**
	 * 显示学生选择的课程信息
	 * @param sId
	 * @return
	 */
	public List<CourseTeacherVO> showOptionCourse(Integer sId) throws CourseNotFoundExceptin{
		List<CourseTeacherVO> list = mapper.showOptionCourse(sId);
		//判断是否查询出信息
		if (list.isEmpty()) {
			throw new CourseNotFoundExceptin("没有选择的课程,请前往选择课程!");
		}
		return list;
	}
	
	/**
	 * 根据课程名或教师名查询学生所选课程
	 * @param name
	 * @param sId
	 * @return
	 * @throws CourseNotFoundExceptin
	 */
	public List<CourseTeacherVO> findByStuTnameCname(String name,Integer sId) throws CourseNotFoundExceptin{
		List<CourseTeacherVO> list = mapper.findByStuTnameCname(name, sId);
		//判断是否查询出信息
		if (list.isEmpty()) {
			throw new CourseNotFoundExceptin("查询信息不存在!");
		}
		return list;
	}
	
}
