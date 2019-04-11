package com.nit.net.course.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import com.nit.net.course.entity.StudentSUH;
import com.nit.net.course.entity.StudentScore;
import com.nit.net.course.entity.StudentUploadHomework;
import com.nit.net.course.entity.TeacherHomework;

@Mapper
public interface StudentHomeworkMapper {
	
	/**
	 * 根据课程id查询所有的作业信息
	 * @param cId
	 * @return
	 */
	@Select("select id,upload_date as uploadDate,knowledge,homework_content as homeworkContent from teacher_homework where c_id=#{cId} order by uploadDate desc")
	List<TeacherHomework> findHomeworkByCid(Integer cId);
	
	/**
	 * 将学生上传的作业答案和老师上传的作业id进行连接
	 * @param studentUploadHomework
	 * @return
	 */
	@Insert("insert into student_upload_homework values(null,#{homeworkContent},#{thId},#{sId})")
	@SelectKey(statement="select LAST_INSERT_ID()",keyProperty="id",before=false,resultType=Integer.class)
	Integer insertStudentHomework(StudentUploadHomework studentUploadHomework);
	
	/**
	 * 将学生上传的作业答案与学生的id进行连接
	 * @param studentSUH
	 * @return
	 */
	@Insert("insert into student_suh values(null,#{sId},#{suhId})")
	Integer insertStudentSUH(StudentSUH studentSUH);
	
	/**
	 * 将学生的作业成绩和学生提交的答案等绑定
	 * @param studentScore
	 * @return
	 */
	@Insert("insert into student_score values(null,#{score},#{suhId},#{sId},#{cId})")
	Integer insertStudentScore(StudentScore studentScore);
	
	/**
	 * 根据学生id、知识点和课程id查询学生提交的答案id
	 * @param sId
	 * @param knowledge
	 * @param cId
	 * @return
	 */
	@Select("select id from student_upload_homework where s_id=#{sId} and th_id = (select id from teacher_homework where knowledge=#{knowledge} and c_id=#{cId})")
	Integer findIdByThidSid(@Param("sId")Integer sId,@Param("knowledge")String knowledge,@Param("cId")Integer cId);
	
}
