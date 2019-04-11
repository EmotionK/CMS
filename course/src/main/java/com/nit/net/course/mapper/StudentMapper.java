package com.nit.net.course.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.nit.net.course.VO.StudentScoreVO;
import com.nit.net.course.entity.Student;

@Mapper
public interface StudentMapper {
	
	/**
	 * 注册学生信息
	 * @param student
	 * @return
	 */
	@Insert("insert into s_user values(null,#{username},#{password},#{name},#{gender},#{college},#{squad})")
	Integer register(Student student);
	
	
	/**
	 * 根据用户名查询学生信息
	 * @param username
	 * @return
	 */
	@Select("select id,password,name,gender,college,squad from s_user where username=#{username}")
	Student findByUsername(String username);
	
	/**
	 * 根据id查询学生信息
	 * @param id
	 * @return
	 */
	@Select("select id,username,password,name,gender,college,squad from s_user where id=#{id}")
	Student findById(Integer id);
	
	/**
	 * 根据学号或姓名或班级查询学生信息
	 * @param other
	 * @return
	 */
	@Select("select id,username,name,gender,college,squad from s_user where username=#{other} or name=#{other} or squad=#{other} order by username")
	List<Student> findByOther(String other);
	
	/**
	 * 查询所有的学生信息
	 * @return
	 */
	@Select("select id,username,name,gender,college,squad from s_user order by username")
	List<Student> findAll();
	
	/**
	 * 根据id修改学生密码
	 * @param id
	 * @param password
	 * @return
	 */
	@Update("update s_user set password=#{password} where id=#{id}")
	Integer updatePwd(@Param("id")Integer id,@Param("password")String password);
	
	/**
	 * 修改学生信息
	 * @param student
	 * @return
	 */
	@Update("update s_user set name=#{name},gender=#{gender},college=#{college},squad=#{squad} where id=#{id}")
	Integer update(Student student);
	
	/**
	 * 根据id删除学生信息
	 * @param id
	 * @return
	 */
	@Delete("delete from s_user where id=#{id}")
	Integer delete(Integer id);	
	
	/**
	 * 根据课程id查询学生成绩和学生信息
	 * @param cId
	 * @return
	 */
	List<StudentScoreVO> findScoreStudentByCid(Integer cId);
	
	/**
	 * 根据班级或者学生名查询成绩
	 * @param name
	 * @return
	 */
	List<StudentScoreVO> findScoreBySNameCName(String name);
	
}
