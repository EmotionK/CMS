package com.nit.net.course.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.nit.net.course.entity.Student;
import com.nit.net.course.entity.StudentSUH;
import com.nit.net.course.entity.StudentScore;
import com.nit.net.course.entity.StudentUploadHomework;
import com.nit.net.course.entity.TeacherHomework;
import com.nit.net.course.mapper.CourseMapper;
import com.nit.net.course.mapper.StudentHomeworkMapper;
import com.nit.net.course.mapper.StudentMapper;
import com.nit.net.course.mapper.TeacherHomeworkMapper;
import com.nit.net.course.service.exception.CourseNotFoundExceptin;
import com.nit.net.course.service.exception.DataException;
import com.nit.net.course.service.exception.FileFormatErrorException;
import com.nit.net.course.service.exception.UploadFileEmptyException;
import com.nit.net.course.service.exception.UserNotFoundException;

@Service
public class StudentHomeworkService {
	
	@Autowired
	private StudentHomeworkMapper studentHomeworkMapper;
	@Autowired
	private TeacherHomeworkMapper teacherHomeworkMapper;
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private CourseMapper courseMapper;
	
	/**
	 * 根据课程id查询所有的作业信息
	 * @param cId
	 * @return
	 */
	public List<TeacherHomework> findHomeworkByCid(Integer cId){
		List<TeacherHomework> list = studentHomeworkMapper.findHomeworkByCid(cId);
		return list;
	}
	
	/**
	 * 下载老师布置的作业
	 * @param homeworkContent
	 * @param response
	 * @throws DataException
	 */
	public void downloadHomework(String homeworkContent,HttpServletRequest request,HttpServletResponse response) throws DataException {
		//获取作业
		String newHomeworkContent1 = homeworkContent.substring(0, homeworkContent.indexOf("/"));
		String newHomeworkContent2 = homeworkContent.substring(homeworkContent.indexOf("/")+1);
		File file = new File(request.getServletContext().getRealPath(newHomeworkContent1),newHomeworkContent2);
		//设置文件名
		String fileName = homeworkContent.substring(homeworkContent.lastIndexOf("-")+1);
		if (file.exists()) {
			// 设置强制下载不打开
			response.setContentType("application/force-download");
			try {
				//设置下载的文件名
				response.addHeader("Content-Disposition", "attachment;fileName=" + new String(fileName.getBytes("UTF8"), "ISO8859-1"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			byte[] buffer = new byte[1024];
			//设置读取文件的字节流
			FileInputStream fis = null;
			//设置读取文件的字符流
			BufferedInputStream bis = null;
			try {
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				//将文件写出
				OutputStream os = response.getOutputStream();
				int line = bis.read(buffer);
				while (line != -1) {
					//将这个长度的字节写出
					os.write(buffer,0,line);
					//读取文件的长度
					line = bis.read(buffer);
				}
			} catch (Exception e) {
				throw new DataException("文件下载失败！请重试或联系系统管理员！");
			}finally {
				//将流关闭
				if (bis != null) {
					try {
						bis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
	/**
	 * 学生上传作业
	 * @param uploadHomework
	 * @param thId
	 * @param sId
	 * @param request
	 * @throws UploadFileEmptyException
	 * @throws DataException
	 */
	@Transactional
	public void uploadHomework(MultipartFile uploadHomework,Integer thId,Integer sId,HttpServletRequest request) throws UploadFileEmptyException,DataException {
		//判断上传文件是否为空
		if (uploadHomework.isEmpty() || uploadHomework.getSize()==0) {
			throw new UploadFileEmptyException("请上传文件");
		}
		//获取文件名
		String homeworkName = uploadHomework.getOriginalFilename();
		//判断上传的文件格式
		String lastName = homeworkName.substring(homeworkName.lastIndexOf(".")+1);
		if (!"jpg".equals(lastName) || !"jpeg".equals(lastName) || !"png".equals(lastName) || !"pdf".equals(lastName)) {
			throw new FileFormatErrorException("请上传正确的图片格式(.jpg/.jpeg/.png/.pdf)!");
		}
		//获取课程名和作业知识点
		List<Map<String, String>> list = teacherHomeworkMapper.findCourseNameByTHId(thId);
		if (list.isEmpty()) {
			throw new CourseNotFoundExceptin();
		}
		//获取课程名
		String courseName = list.get(0).get("name");
		//获取知识点
		String knowledge = list.get(0).get("knowledge");
		//获取学生信息
		Student student = studentMapper.findById(sId);
		if (student==null) {
			throw new UserNotFoundException();
		}
		//获取学生学号
		String studentUsername = student.getUsername();
		//获取学生的姓名
		String studentName = student.getName();
		//获取上传的时间
		String date = new SimpleDateFormat("YYYY-MM-dd").format(new Date());
		//指定文件储存位置
		String filePath = request.getServletContext().getRealPath("studentHomework");
		//创建新的文件名和存储位置
		String newFile = courseName+"/"+studentUsername+"-"+studentName+"/"+knowledge+"-"+date+"-"+homeworkName;
		File file = new File(filePath,newFile);
		//判断此文件夹是否存在
		if (!file.getParentFile().exists()) {
			//创建文件夹
			file.getParentFile().mkdirs();
		}
		//保存文件
		try {
			uploadHomework.transferTo(file);
		} catch (IllegalStateException e) {
			throw new DataException("数据异常,请联系系统管理员!");
		} catch (IOException e) {
			throw new DataException("数据异常,请联系系统管理员!");
		}
		//将数据存入数据库
		StudentUploadHomework studentUploadHomework = new StudentUploadHomework();
		studentUploadHomework.setHomeworkContent("studentHomework/"+newFile);
		studentUploadHomework.setThId(thId);
		studentUploadHomework.setsId(sId);
		Integer rows = studentHomeworkMapper.insertStudentHomework(studentUploadHomework);
		if (rows!=1) {
			throw new DataException("数据异常,请联系系统管理员!");
		}
		//将数据存入数据库
		StudentSUH studentSUH = new StudentSUH();
		studentSUH.setsId(sId);
		studentSUH.setSuhId(studentUploadHomework.getId());
		rows = studentHomeworkMapper.insertStudentSUH(studentSUH);
		if (rows!=1) {
			throw new DataException("数据异常,请联系系统管理员!");
		}	
	}
	
	/**
	 * 将学生的作业成绩和学生提交的答案等绑定
	 * @param studentScore
	 */
	public void insertStudentScore(String score,Integer sId,String cName,String knowledge) {
		//根据课程名查询课程id
		Integer cId = courseMapper.findCidByCname(cName);
		//根据课程id、知识点和学生id获取学生提交作业的id
		Integer suhId = studentHomeworkMapper.findIdByThidSid(sId, knowledge, cId);
		//将数据存入对象中
		StudentScore studentScore = new StudentScore();
		studentScore.setScore(score);
		studentScore.setSuhId(suhId);
		studentScore.setsId(sId);
		studentScore.setcId(cId);
		Integer rows = studentHomeworkMapper.insertStudentScore(studentScore);
		if (rows!=1) {
			throw new DataException("数据异常，请联系系统管理员！");
		}
	}
	
	
}
