package com.nit.net.course.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.nit.net.course.VO.CourseTeacherVO;
import com.nit.net.course.entity.Course;
import com.nit.net.course.entity.StudentScore;

@Mapper
public interface CourseMapper {
	
	/**
	 * 注册课程
	 * @param course
	 * @return
	 */
	@Insert("insert into course values(null,#{name},#{time},#{location},#{tId})")
	Integer reg(Course course);
	
	/**
	 * 根据课程名和教师id查询课程
	 * @param name
	 * @param id
	 * @return
	 */
	@Select("select id,name,time,location from course where name=#{name} and t_id=#{tId}")
	Course findByName(@Param("name")String name,@Param("tId")Integer tId);
	
	/**
	 * 根据教师id查询课程
	 * @param courseTeacher
	 * @return
	 */
	@Select("select id,name,time,location from course where t_id=#{tId}")
	List<Course> select(Integer tId);
	
	/**
	 * 根据课程id查询课程信息
	 * @param id
	 * @return
	 */
	@Select("select id,name,time,location from course where id=#{id}")
	Course findById(Integer id);
	
	
	/**
	 * 根据id修改课程信息
	 * @param id
	 * @return
	 */
	@Update("update course set time=#{time},location=#{location} where id=#{id}")
	Integer updateById(Course course);
	
	/**
	 * 根据id删除课程信息
	 * @param id
	 * @return
	 */
	@Delete("delete from course where id=#{id}")
	Integer delete(Integer id);
	
	
	/**
	 * 查询所有课程与老师信息
	 * @return
	 */
	List<CourseTeacherVO> findAll();
	
	/**
	 * 根据教师名或课程名查询课程和老师信息
	 * @return
	 */
	List<CourseTeacherVO> findByTCName(String name);
	
	/**
	 * 显示学生选择的课程
	 * @param sId
	 * @return
	 */
	List<CourseTeacherVO> showOptionCourse(Integer sId);
	
	/**
	 * 根据课程名或教师名查询学生所选课程
	 * @param name
	 * @return
	 */
	List<CourseTeacherVO> findByStuTnameCname(@Param("name")String name,@Param("sId")Integer sId);
	
	/**
	 * 根据课程名查询课程id
	 * @param cName
	 * @return
	 */
	@Select("select id from course where name=#{cName}")
	Integer findCidByCname(String cName);
}
