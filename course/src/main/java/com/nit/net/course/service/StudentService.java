package com.nit.net.course.service;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nit.net.course.VO.StudentScoreVO;
import com.nit.net.course.entity.Student;
import com.nit.net.course.entity.Teacher;
import com.nit.net.course.mapper.StudentMapper;
import com.nit.net.course.service.exception.DataException;
import com.nit.net.course.service.exception.PasswordErrorException;
import com.nit.net.course.service.exception.UserConflictException;
import com.nit.net.course.service.exception.UserNotFoundException;
import com.nit.net.course.service.exception.UsernameFormatException;
import com.nit.net.course.util.TextValidator;

@Service
public class StudentService {
	
	@Autowired
	private StudentMapper mapper;
	
	/**
	 * 注册学生信息
	 * @param student
	 * @throws UsernameFormatException
	 * @throws UserConflictException
	 * @throws DataException
	 */
	public void register(Student student) throws UsernameFormatException,UserConflictException,DataException {
		//验证用户名格式
		if (!TextValidator.checkUsername(student.getUsername())) {
			throw new UsernameFormatException("用户名格式不正确");
		}
		//验证此用户是否存在
		if (mapper.findByUsername(student.getUsername())!=null) {
			throw new UserConflictException("此用户已存在");
		}
		//将密码加密
		String new_psaaword = getEncrpytedPassword(student.getPassword());
		//将密码设置会学生信息
		student.setPassword(new_psaaword);
		//调用dao层
		Integer rows = mapper.register(student);
		//判断是否注册成功
		if (rows!=1) {
			throw new DataException("数据异常,请联系系统管理员!");
		}
	}
	
	/**
	 * 学生登录
	 * @param username
	 * @param password
	 * @return
	 */
	public Student login(String username,String password) throws UserNotFoundException,PasswordErrorException {
		//根据用户名查询学生信息
		Student student = mapper.findByUsername(username);
		//判断用户是否存在
		if (student==null) {
			throw new UserNotFoundException("此用户不存在");
		}else {
			//将输入的密码加密与数据库中的密码进行比对
			if (!student.getPassword().equals(getEncrpytedPassword(password))) {
				throw new PasswordErrorException("密码输入错误");
			}
		}
		return student;
	}
	
	
	/**
	 * 根据id查询学生信息
	 * @param id
	 * @return
	 */
	public Student findById(Integer id) {
		Student student = mapper.findById(id);
		return student;
	}
	
	/**
	 * 根据用户名,姓名或班级查询学生信息
	 * @param other
	 * @return
	 * @throws UserNotFoundException
	 */
	public List<Student> findByOther(String other) throws UserNotFoundException{
		List<Student> students = mapper.findByOther(other);
		if (students.isEmpty()) {
			throw new UserNotFoundException("用户不存在!");
		}
		return students;
	}
	
	/**
	 * 查询所有学生信息
	 * @return
	 * @throws DataException
	 */
	public List<Student> findAll() throws DataException{
		List<Student> students = mapper.findAll();
		if (students==null) {
			throw new DataException("数据异常,请联系系统管理员!");
		}
		return students;
	}
	
	/**
	 * 根据用户名修改学生信息
	 * @param student
	 * @throws DataException
	 */
	public void update(Student student) throws DataException  {
		Integer rows = mapper.update(student);
		if (rows!=1) {
			throw new DataException("数据异常,请联系系统管理员!");
		}
	}
	
	/**
	 * 根据id修改学生密码
	 * @param id
	 * @param password
	 * @throws DataException
	 */
	public void updatePwd(Integer id,String old_password,String new_password) throws PasswordErrorException,DataException {
		//通过id查询学生信息
		Student student = mapper.findById(id);
		//将旧密码加密后与查询出的密码进行比对
		if (getEncrpytedPassword(old_password).equals(student.getPassword())) {
			Integer rows = mapper.updatePwd(id, getEncrpytedPassword(new_password));
			if (rows!=1) {
				throw new DataException("修改数据失败,请联系系统管理员!");
			}
		}else {
			throw new PasswordErrorException("密码输入错误");
		}
		
	}
	
	/**
	 * 根据id删除学生信息
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
	 * 根据课程id查询学生成绩和学生信息
	 * @param cId
	 * @return
	 */
	public List<StudentScoreVO> findScoreStudentByCid(Integer cId){
		return mapper.findScoreStudentByCid(cId);
	}
	
	/**
	 * 根据班级或者学生名查询成绩
	 * @param name
	 * @return
	 * @throws UserNotFoundException
	 */
	public List<StudentScoreVO> findScoreBySNameCName(String name) throws UserNotFoundException{
		List<StudentScoreVO> list = mapper.findScoreBySNameCName(name);
		if (list.isEmpty()) {
			throw new UserNotFoundException("此条数据不存在，请重新输入！");
		}
		return list;
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
