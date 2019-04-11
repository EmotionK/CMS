package com.nit.net.course.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.nit.net.course.entity.Teacher;

@Mapper
public interface TeacherMapper {
	
	/**
	 * 根据用户名查询教师的信息
	 * @param username
	 * @return
	 */
	@Select("select id,username,password,name from t_user where username=#{username}")
	Teacher findByUsername(String username);
	
	/**
	 * 根据id查询教师信息
	 * @param id
	 * @return
	 */
	@Select("select id,username,password,name,gender,age,college,phone from t_user where id=#{id}")
	Teacher findById(Integer id);
	
	/**
	 * 根据姓名查询教师信息
	 * @param name
	 * @return
	 */
	@Select("select id,username,name,gender,age,college,phone from t_user where name=#{name}")
	Teacher findByName(String name);
	
	/**
	 * 查询所有教师
	 * @return
	 */
	@Select("select id,username,name,gender,age,college,phone from t_user order by username")
	List<Teacher> findAll();
	
	
	
	
	/**
	 * 根据id删除教师信息
	 * @param id
	 * @return
	 */
	@Delete("delete from t_user where id=#{id}")
	Integer deleteById(Integer id);
	
	/**
	 * 注册教师
	 * @param teacher
	 * @return
	 */
	@Insert("insert into t_user values(null,#{username},#{password},#{name},#{gender},#{age},#{college},#{phone})")
	Integer regiter(Teacher teacher);
	
	/**
	 * 根据id修改教师信息
	 * @param id
	 * @return
	 */
	@Update("update t_user set name=#{name},gender=#{gender},age=#{age},college=#{college},phone=#{phone} where id=#{id}")
	Integer update(Teacher teacher);
	
	/**
	 * 根据id修改教师密码
	 * @param id
	 * @param password
	 * @return
	 */
	@Update("update t_user set password=#{password} where id=#{id}")
	Integer updatePwd(@Param("id")Integer id,@Param("password")String password);
	
}
