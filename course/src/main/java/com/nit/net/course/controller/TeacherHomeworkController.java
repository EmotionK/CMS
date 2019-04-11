package com.nit.net.course.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nit.net.course.entity.ResponseResult;
import com.nit.net.course.entity.Student;
import com.nit.net.course.entity.TeacherHomework;
import com.nit.net.course.service.TeacherHomeworkService;

@RestController
@RequestMapping("/tacherHomework")
public class TeacherHomeworkController extends BaseController {
	
	@Autowired
	private TeacherHomeworkService service;
	
	/**
	 * 将教师布置的作业内容存入数据库
	 * @param cId
	 * @param knowledge
	 * @param uploadFile
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/assignHomework.do",method=RequestMethod.POST)
	public ResponseResult<Void> assignHomework(String knowledge,@RequestParam(value="uploadFile")MultipartFile uploadFile,Integer cId,HttpSession session,HttpServletRequest request){
		//获取教师的id
		Integer tId = (Integer)session.getAttribute("tId");
		service.assignHomework(cId, tId, knowledge, uploadFile,request);
		return new ResponseResult<Void>();
	}
	
	/**
	 * 根据课程id查询布置的作业信息
	 * @param tId
	 * @return
	 */
	@RequestMapping("/findHomeworkByCid.do")
	public ResponseResult<List<TeacherHomework>> findHomeworkByCid(Integer cId){
		ResponseResult<List<TeacherHomework>> responseResult = new ResponseResult<List<TeacherHomework>>();
		List<TeacherHomework> list = service.findHomeworkByCid(cId);
		responseResult.setData(list);
		return responseResult;
	}
	
	/**
	 * 根据知识点查询布置的作业信息
	 * @param tId
	 * @return
	 */
	@RequestMapping("/findHomeworkByKnowledge.do")
	public ResponseResult<List<TeacherHomework>> findHomeworkByKnowledge(String knowledge){
		ResponseResult<List<TeacherHomework>> responseResult = new ResponseResult<List<TeacherHomework>>();
		List<TeacherHomework> list = service.findHomeworkByKnowledge(knowledge);
		responseResult.setData(list);
		return responseResult;
	}
	
	/**
	 * 根据老师布置的作业id查询哪些学生已经上传了答案
	 * @param thId
	 * @return
	 */
	@RequestMapping("/findStudentByTeacherHomeworkId.do")
	public ResponseResult<List<Student>> findStudentByTeacherHomeworkId(Integer thId){
		ResponseResult<List<Student>> responseResult = new ResponseResult<List<Student>>();
		List<Student> list = service.findStudentByTeacherHomeworkId(thId);
		responseResult.setData(list);
		return responseResult;
	}
	
	/**
	 * 根据学生id和知识点和课程名查询学生上传的作业内容
	 * @param sId
	 * @param konwledge
	 * @param cName
	 * @return
	 */
	@RequestMapping(value="/findStudentHomeworkBySIdKnowledgeCName.do",method=RequestMethod.POST)
	public ResponseResult<String> findStudentHomeworkBySIdKnowledgeCName(Integer sId,String knowledge,String cName,HttpServletRequest request){
		ResponseResult<String> responseResult = new ResponseResult<String>();
		String homeworkContent = service.findStudentHomeworkBySIdKnowledgeCName(sId, knowledge, cName,request);
		responseResult.setData(homeworkContent);
		return responseResult;
	}
	
}
