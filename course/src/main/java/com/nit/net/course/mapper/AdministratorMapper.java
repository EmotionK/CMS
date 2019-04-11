package com.nit.net.course.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.nit.net.course.entity.Administrator;

@Mapper
public interface AdministratorMapper {
	
	/**
	 * 根据管理员用户名查询用户
	 * @param username 用户名
	 * @return 管理员信息
	 */
	@Select("select id,username,password from administrator where username=#{username}")
	Administrator findByUsername(String username);
	
	@Insert("insert into administrator values(null,#{username},#{password})")
	Integer register(Administrator administrator);
	
}
