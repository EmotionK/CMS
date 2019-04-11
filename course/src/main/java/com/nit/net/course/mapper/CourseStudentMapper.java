package com.nit.net.course.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.nit.net.course.entity.Student;
import com.nit.net.course.entity.StudentCourse;

@Mapper
public interface CourseStudentMapper {
	
	/**
	 * 根据课程id查询信息
	 * @param cId
	 * @return
	 */
	@Select("SELECT id,s_id AS sId,c_id AS cId FROM student_course WHERE c_id=#{cId}")
	StudentCourse findBycId(Integer cId);
	
	/**
	 * 将学生和课程绑定
	 * @param studentCourse
	 * @return
	 */
	@Insert("insert into student_course values(null,#{sId},#{cId})")
	Integer optionCourse(StudentCourse studentCourse);
	
	/**
	 * 根据学生id和课程id删除信息
	 * @param sId
	 * @param cId
	 * @return
	 */
	@Delete("delete from student_course where s_id=#{sId} and c_id=#{cId}")
	Integer deleteBySidCid(@Param("sId")Integer sId,@Param("cId")Integer cId);
	
	/**
	 * 根据课程id查询所有选择此课程的学生信息
	 * @param cId
	 * @return
	 */
	@Select("select id,username,name,gender,college,squad from s_user where id in(select s_id from student_course where c_id=#{cId})")
	List<Student> findStudentByCid(Integer cId);
	
	/**
	 * 根据学生用户名或姓名或班级查询选择此课程的所有学生信息
	 * @param cId
	 * @param name
	 * @return
	 */
	@Select("select id,username,name,gender,college,squad from(select id,username,name,gender,college,squad from s_user where id in(select s_id from student_course where c_id=#{cId})) c1 where username=#{name} or name=#{name} or squad=#{name}")
	List<Student> findStudentByUsernameNameSquad(@Param("cId")Integer cId,@Param("name")String name);
	
	/**
	 * 根据课程id和学生id查询课程是否已经选择
	 * @param sId
	 * @param cId
	 * @return
	 */
	@Select("select id,s_id as sId,c_id as cId from student_course where s_id=#{sId} and c_id=#{cId}")
	StudentCourse findBySidCid(@Param("sId")Integer sId,@Param("cId")Integer cId);
	
}
