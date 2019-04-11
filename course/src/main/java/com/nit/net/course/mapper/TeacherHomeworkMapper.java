package com.nit.net.course.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.nit.net.course.entity.Student;
import com.nit.net.course.entity.TeacherHomework;

@Mapper
public interface TeacherHomeworkMapper {
	
	/**
	 * 将教师布置的作业内容存入数据库
	 * @param teacherHomework
	 * @return
	 */
	@Insert("insert into teacher_homework values(null,#{knowledge},#{uploadDate},#{homeworkContent},#{cId},#{tId})")
	Integer assignHomework(TeacherHomework teacherHomework);
	
	/**
	 * 根据教师id查询布置的作业信息
	 * @param tId
	 * @return
	 */
	@Select("select id,knowledge,upload_date as uploadDate from teacher_homework where c_id=#{cId} order by uploadDate desc")
	List<TeacherHomework> findHomeworkByCid(Integer cId);
	
	/**
	 * 根据知识点查询布置的作业信息
	 * @param knowledge
	 * @return
	 */
	@Select("select knowledge,upload_date as uploadDate from teacher_homework where knowledge=#{knowledge}")
	List<TeacherHomework> findHomeworkByKnowledge(String knowledge);
	
	/**
	 * 根据教师布置的作业id查询课程名和知识点
	 * @param thId
	 * @return
	 */
	@Select("select c.name,th.knowledge from course as c join teacher_homework as th on c.id=th.c_id where th.id=#{thId}")
	List<Map<String, String>> findCourseNameByTHId(Integer thId);
	
	/**
	 * 根据老师布置的作业id查询哪些学生已经上传了答案
	 * @param thId
	 * @return
	 */
	@Select("select s.id,s.username,s.name from s_user as s join student_suh as ssuh on s.id=ssuh.s_id where ssuh.suh_id in (select id from student_upload_homework where th_id=#{thId})")
	List<Student> findStudentByTeacherHomeworkId(Integer thId);
	
	/**
	 * 根据学生id和知识点和课程名查询学生上传的作业内容
	 * @param sId
	 * @param konwledge
	 * @param cName
	 * @return
	 */
	@Select("select homework_content from student_upload_homework where  id in (select suh_id from student_suh where s_id=#{sId}) and  th_id=(select id from teacher_homework where knowledge = #{knowledge} and c_id = (select id from course where name=#{cName}))")
	String findStudentHomeworkBySIdKnowledgeCName(@Param("sId")Integer sId,@Param("knowledge")String knowledge,@Param("cName")String cName);
	
}
