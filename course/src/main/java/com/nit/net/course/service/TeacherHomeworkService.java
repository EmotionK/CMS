package com.nit.net.course.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.nit.net.course.entity.Course;
import com.nit.net.course.entity.Student;
import com.nit.net.course.entity.TeacherHomework;
import com.nit.net.course.mapper.CourseMapper;
import com.nit.net.course.mapper.TeacherHomeworkMapper;
import com.nit.net.course.service.exception.DataException;
import com.nit.net.course.service.exception.FileFormatErrorException;
import com.nit.net.course.service.exception.HomeworkNotFoundException;
import com.nit.net.course.service.exception.UploadFileEmptyException;
import com.nit.net.course.util.BASE64;

@Service
public class TeacherHomeworkService {
	
	@Autowired
	private TeacherHomeworkMapper mapper;
	@Autowired
	private CourseMapper courseMapper;
	
	/**
	 * 将教师布置的作业内容存入数据库
	 * @param teacherHomework
	 * @throws DataException
	 */
	@Transactional
	public void assignHomework(Integer cId,Integer tId,String knowledge,MultipartFile uploadFile,HttpServletRequest request) throws UploadFileEmptyException,FileFormatErrorException,DataException {
		//判断上传的是否为空文件
		if (uploadFile.isEmpty() || uploadFile.getSize()==0) {
			throw new UploadFileEmptyException("请上传文件!");
		}
		//获取文件名
		String fileName = uploadFile.getOriginalFilename();
		//检查文件的格式
		String lastName = fileName.substring(fileName.lastIndexOf(".")+1);
		if (!"docx".equals(lastName) || !"doc".equals(lastName)) {
			throw new FileFormatErrorException("请上传文档格式的文件！");
		}
		//获取课程名
		Course course = courseMapper.findById(cId);
		String courseName = course.getName();
		//获取上传的时间
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		String date = sdf.format(new Date());
		//创建新的文件名
		String newFileName = courseName+"/"+knowledge+"-"+date+"-"+fileName;
		//指定位置存储文件
		String pathFile = request.getServletContext().getRealPath("teacherHomework");
		File file = new File(pathFile,newFileName);
		//判断此文件夹是否存在
		if (!file.getParentFile().exists()) {
			//创建文件夹
			file.getParentFile().mkdirs();
		}
		//保存文件
		try {
			uploadFile.transferTo(file);
		} catch (IllegalStateException e) {
			throw new DataException("数据异常,请联系系统管理员!");
		} catch (IOException e) {
			throw new DataException("数据异常,请联系系统管理员!");
		}
		//将数据存入数据库
		TeacherHomework teacherHomework = new TeacherHomework();
		teacherHomework.setKnowledge(knowledge);
		teacherHomework.setUploadDate(date);
		teacherHomework.setHomeworkContent("teacherHomework/"+newFileName);
		teacherHomework.setcId(cId);
		teacherHomework.settId(tId);
		Integer rows = mapper.assignHomework(teacherHomework);
		if (rows!=1) {
			throw new DataException("数据异常,请联系系统管理员!");
		}
	}
	
	/**
	 * 根据教师id查询布置的作业信息
	 * @param tId
	 * @return
	 */
	public List<TeacherHomework> findHomeworkByCid(Integer cId){
		List<TeacherHomework> list = mapper.findHomeworkByCid(cId);
		return list;
	}
	
	public List<TeacherHomework> findHomeworkByKnowledge(String knowledge) throws HomeworkNotFoundException{
		List<TeacherHomework> list = mapper.findHomeworkByKnowledge(knowledge);
		if (list.isEmpty()) {
			throw new HomeworkNotFoundException("未找到相关作业！");
		}
		return list;
	}
	
	/**
	 * 根据老师布置的作业id查询哪些学生已经上传了答案
	 * @param thId
	 * @return
	 */
	public List<Student> findStudentByTeacherHomeworkId(Integer thId){
		List<Student> list = mapper.findStudentByTeacherHomeworkId(thId);
		return list;
	}
	
	/**
	 * 根据学生id和知识点和课程名查询学生上传的作业内容
	 * @param sId
	 * @param konwledge
	 * @param cName
	 * @return
	 */
	public String findStudentHomeworkBySIdKnowledgeCName(Integer sId,String knowledge,String cName,HttpServletRequest request) throws DataException {
		String homeworkContent = mapper.findStudentHomeworkBySIdKnowledgeCName(sId, knowledge, cName);
		if (homeworkContent==null) {
			throw new DataException("系统异常，请联系系统管理员");
		}
		return homeworkContent;
	}
	
}
