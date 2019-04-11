package com.nit.net.course.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nit.net.course.entity.ResponseResult;
import com.nit.net.course.service.exception.CourseConflictException;
import com.nit.net.course.service.exception.CourseNotFoundExceptin;
import com.nit.net.course.service.exception.DataException;
import com.nit.net.course.service.exception.FileFormatErrorException;
import com.nit.net.course.service.exception.HomeworkNotFoundException;
import com.nit.net.course.service.exception.PasswordErrorException;
import com.nit.net.course.service.exception.SecretKeyErrorException;
import com.nit.net.course.service.exception.ServiceException;
import com.nit.net.course.service.exception.UploadFileEmptyException;
import com.nit.net.course.service.exception.UserConflictException;
import com.nit.net.course.service.exception.UserNotFoundException;
import com.nit.net.course.service.exception.UsernameFormatException;

public abstract class BaseController {
	
	@ExceptionHandler(ServiceException.class)
    public ResponseResult<Void> handleException(Exception e) {
        // 判断异常的种类并进行处理
        if (e instanceof UserConflictException) {
        	// -1 用户已存在异常
            return new ResponseResult<>(-1, e);
        } else if (e instanceof UsernameFormatException) {
        	//-2 用户名格式不正确异常
        	return new ResponseResult<>(-2, e);
        }else if (e instanceof SecretKeyErrorException) {
        	//-3 密钥错误异常
			return new ResponseResult<>(-3, e);
		}else if (e instanceof UserNotFoundException) {
			//-4 用户没有找到异常
			return new ResponseResult<>(-4, e);
		}else if (e instanceof PasswordErrorException) {
			//-5 密码错误异常
			return new ResponseResult<>(-5, e);
		}else if (e instanceof DataException) {
			//-6 数据异常
			return new ResponseResult<>(-6, e);
		}else if (e instanceof CourseNotFoundExceptin) {
			//-7 课程没有找到异常
			return new ResponseResult<>(-7, e);
		}else if (e instanceof CourseConflictException) {
			// -8 课程冲突异常
			return new ResponseResult<>(-8,e);
		}else if (e instanceof UploadFileEmptyException) {
			// -9 上传文件为空
			return new ResponseResult<>(-9,e);
		}else if (e instanceof HomeworkNotFoundException) {
			// -10作业没有找到异常
			return new ResponseResult<>(-10,e);
		}else if (e instanceof UploadFileEmptyException) {
			// -11上传文件为空
			return new ResponseResult<>(-11,e);
		}else if (e instanceof FileFormatErrorException) {
			// -12文件格式不正确
			return new ResponseResult<>(-11,e);
		}

        return null;
    }
}
