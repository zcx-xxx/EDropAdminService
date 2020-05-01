/**
 * @Title: UserMapper.java
 * @Package com.zcx.mapper
 * @Description: 
 * @author 13071
 * @date 2019年11月27日
 * @version V1.0
 */
package com.edrop.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.edrop.pojo.User;

/**
 * @ClassName: UserMapper
 * @Description: 
 * @author 13071
 * @date 2019年11月27日
 *
 */
public interface UserMapper {

	//通过用户名查询用户的信息
	@Select("select * from user where username like #{param1}")
	public User selUserByUsername(String username);
	
	//通过电话查询用户的信息
	@Select("select * from user where phone like #{param1}")
	public User selUserByPhone(String phone);

	//通过 qq 查询用户的信息
	@Select("select * from user where qq like #{param1}")
	public User selUserByQq(String qq);

	//更新用户信息
	public int upUserInfo(@Param("id")Integer id,@Param("phone")String phone, @Param("username")String username, @Param("address")String address, @Param("detailAddress")String detailAddress, @Param("gender")String gender);
	
	//插入用户数据
	public int insUserInfo(@Param("phone")String phone, @Param("username")String username, @Param("password")String password, @Param("imgpath")String imgpath, @Param("imgname")String imgname, @Param("address")String address, @Param("detailAddress")String detailAddress, @Param("gender")String gender, @Param("registerTime")String registerTime);

	//id 查询用户信息
	public User selUserInfoById(String id);
		
	//查询所有的用户信息
	public List<User> selectAllUser(@Param("username")String username,@Param("phone")String phone);
	
}
