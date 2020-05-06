/**
 * @Title: EmployeeMapper.java
 * @Package com.edrop.mapper
 * @Description: 
 * @author 13071
 * @date 2019年12月16日
 * @version V1.0
 */
package com.edrop.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.edrop.pojo.Employee;
import com.edrop.pojo.User;

/**
 * @ClassName: EmployeeMapper
 * @Description: 
 * @author zhy
 * @date 2020年5月1日
 *
 */
public interface EmployeeMapper {
	// by username query Employee
	public Employee selEmployeeByUsername(@Param("username")String username);
	
	//插入用户数据
	public int insEmployeeInfo(@Param("phone")String phone, @Param("username")String username,
			@Param("password")String password, @Param("imgpath")String imgpath,
			@Param("qq")String qq,@Param("imgname")String imgname, 
			@Param("gender")String gender, @Param("registerTime")String registerTime);
	
	// by id query Employee
	public Employee selEmployeeInfoById(@Param("id")Integer id);
	
	//查询所有的用户信息
	public List<Employee> selectAllEmployee(@Param("username")String username,@Param("phone")String phone);
	
	//修改工作人员信息
	public void upEmployeeInfo(@Param("id")Integer id,@Param("phone")String phone, @Param("username")String username,
			@Param("imgpath")String imgpath, @Param("qq")String qq, 
			@Param("gender")String gender);
	//删除用户信息
	public void deleteByEmployeeId(Integer id);
	
}
