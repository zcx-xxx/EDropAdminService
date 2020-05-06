package com.edrop.mapper;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.edrop.pojo.UserOperationStatistics;

public interface UserOperationStatisticsMapper {
	// select user operation data by year
	public List<UserOperationStatistics> selectUserOperationData(
			@Param("userOrEmployee")String userOrEmployee, 
			@Param("loginOrRegister")String loginOrRegister);
	
	// all column query
	public UserOperationStatistics selectRowByAllColumn(@Param("year")Integer year, 
			@Param("month")Integer month, @Param("day")Integer day, 
			@Param("userOrEmployee")String userOrEmployee, 
			@Param("loginOrRegister")String loginOrRegister);
	
	// id 跟新
	@Update("update user_operation_statistics set total_operation_times=#{totalOperationTimes} where id=#{id}")
	public Integer updateUserOperationDataById(@Param("id")Integer id, 
			@Param("totalOperationTimes")Integer totalOperationTimes);
	
	// insert a new user operation
	@Insert("insert into user_operation_statistics"
			+ "(year, month, day, user_or_employee, login_or_register, total_operation_times)"
			+ " values(#{year}, #{month}, #{day}, #{userOrEmployee}, #{loginOrRegister}, #{totalOperationTimes})")
	public Integer insertNewUserOperationData(@Param("year")Integer year, @Param("month")Integer month,
			@Param("day")Integer day, @Param("userOrEmployee")String userOrEmployee, 
			@Param("loginOrRegister")String loginOrRegister,
			@Param("totalOperationTimes")Integer totalOperationTimes);
}
