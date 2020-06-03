package com.edrop.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.edrop.pojo.Admin;
/**
 * admin mapper
 * @ClassName:  AdminMapper   
 * @Description:TODO
 * @author: zcx 
 * @date: 2020年4月16日 下午9:40:12     
 * @Copyright: 2020 com.edrop. All rights reserved.
 */
public interface AdminMapper {
	// select admin by username and password
	public Admin selectAdminByUserNameAndPassword(String userName, String password);
	
	// insert admin
	@Insert("insert into admin(username, password, register_time, last_login_time) values(#{param1},#{param2},#{param3},#{param4})")
	public Integer insertAdmin(String userName, String password, Timestamp registerTime, Timestamp lastLoginTime);
	
	// update last login time
	@Update("update admin set last_login_time=#{param2} where username like #{param1}")
	public Integer updateLastLoginTime(String userName, Timestamp lastLoginTime);
	
	// select admin by username
	public Admin selectAdminByUserName(String userName);
//	查询所有管理员信息
	public List<Admin> selectAllAdminInfo();

	public List<Admin> selectAllAdmin(String username);
	
}
