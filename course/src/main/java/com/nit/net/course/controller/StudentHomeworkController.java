package com.nit.net.course.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.nit.net.course.entity.ResponseResult;
import com.nit.net.course.entity.StudentScore;
import com.nit.net.course.entity.StudentUploadHomework;
import com.nit.net.course.entity.TeacherHomework;
import com.nit.net.course.service.StudentHomeworkService;

@Controller
@RequestMapping("/StudentHomework")
public class StudentHomeworkController extends BaseController {
	
	@Autowired
	private StudentHomeworkService studentHomeworkService;
	
	/**
	 * 根据课程id查询所有的作业信息
	 * @param cId
	 * @return
	 */
	@RequestMapping("/findHomeworkByCid.do")
	@ResponseBody
	public ResponseResult<List<TeacherHomework>> findHomeworkByCid(Integer cId){
		ResponseResult<List<TeacherHomework>> responseResult = new ResponseResult<List<TeacherHomework>>();
		List<TeacherHomework> list = studentHomeworkService.findHomeworkByCid(cId);
		responseResult.setData(list);
		return responseResult;
	}
	
	/**
	 * 下载老师布置的作业
	 * @param homeworkContent
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/downloadHomework.do",method=RequestMethod.GET)
	public ResponseResult<Void> downloadHomework(String homeworkContent,HttpServletRequest request,HttpServletResponse response){
		studentHomeworkService.downloadHomework(homeworkContent,request,response);
		return new ResponseResult<Void>();
	}
	
	/**
	 * 学生上传作业
	 * @param uploadHomework
	 * @param thId
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/uploadHomework.do",method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Void> uploadHomework(MultipartFile uploadHomework,Integer thId,HttpSession session,HttpServletRequest request){
		Integer sId = (Integer) session.getAttribute("sId");
		studentHomeworkService.uploadHomework(uploadHomework, thId, sId,request);
		return new ResponseResult<Void>();
	}
	
	/**
	 * 将学生的作业成绩和学生提交的答案等绑定
	 * @param studentScore
	 * @return
	 */
	@RequestMapping(value="/insertStudentScore.do",method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Void> insertStudentScore(String score,Integer sId,String cName,String knowledge){
		studentHomeworkService.insertStudentScore(score, sId, cName, knowledge);
		return new ResponseResult<Void>();
	}
	
}
