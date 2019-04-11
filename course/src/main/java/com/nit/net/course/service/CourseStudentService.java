package com.nit.net.course.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nit.net.course.entity.Student;
import com.nit.net.course.entity.StudentCourse;
import com.nit.net.course.mapper.CourseStudentMapper;
import com.nit.net.course.service.exception.CourseConflictException;
import com.nit.net.course.service.exception.DataException;
import com.nit.net.course.service.exception.UserNotFoundException;
import com.nit.net.course.service.exception.UsernameFormatException;

@Service
public class CourseStudentService {
	
	@Autowired
	private CourseStudentMapper mapper;
	
	/**
	 * 将学生和课程绑定
	 * @param studentCourse
	 */
	public void optionCourse(StudentCourse studentCourse) {
		//根据课程id和学生id查询课程是否已经选择
		StudentCourse data = mapper.findBySidCid(studentCourse.getsId(), studentCourse.getcId());
		//判断查询出的信息是否为空
		if (data!=null) {
			throw new CourseConflictException("此课程已被选择,请勿第二次选择!");
		}
		//进行绑定
		Integer rows = mapper.optionCourse(studentCourse);
		//判断是否绑定成功
		if (rows!=1) {
			throw new DataException("数据异常,请联系系统管理员!");
		}
	}
	
	/**
	 * 根据学生id和课程id删除信息
	 * @param sId
	 * @param cId
	 * @throws DataException
	 */
	public void deleteBySidCid(Integer sId,Integer cId) throws DataException {
		Integer rows = mapper.deleteBySidCid(sId, cId);
		//判断是否删除成功
		if (rows!=1) {
			throw new DataException("数据异常,请联系系统管理员!");
		}
	}
	
	/**
	 * 根据课程id查询选择此课程的学生信息
	 * @param cId
	 * @return
	 */
	public List<Student> findStudentByCid(Integer cId){
		List<Student> list = mapper.findStudentByCid(cId);
		return list;
	}
	
	/**
	 * 根据学生用户名或姓名或班级查询选择此课程的所有学生信息
	 * @param cId
	 * @param name
	 * @return
	 * @throws UserNotFoundException
	 */
	public List<Student> findStudentByUsernameNameSquad(Integer cId,String name) throws UserNotFoundException{
		List<Student> list = mapper.findStudentByUsernameNameSquad(cId, name);
		if (list.isEmpty()) {
			throw new UserNotFoundException("此学生不存在!");
		}
		return list;
	}
	
	
}
