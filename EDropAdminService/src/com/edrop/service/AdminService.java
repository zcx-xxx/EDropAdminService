package com.edrop.service;

import java.util.List;

import com.edrop.pojo.Admin;
import com.github.pagehelper.PageInfo;
/**
 * admin service
 * @ClassName:  AdminService   
 * @Description:TODO
 * @author: zcx 
 * @date: 2020年4月16日 下午9:39:31     
 * @Copyright: 2020 com.edrop. All rights reserved.
 */
public interface AdminService {
	/**
	 * login by userName and password
	 * @Title: loginByUserNameAndPassword   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param userName
	 * @param: @param password
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public String loginByUserNameAndPassword(String userName, String password);
	
	/**
	 * add Admin
	 * @Title: addAdmin   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param userName
	 * @param: @param password
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public String addAdmin(String userName, String password);
	/**
	 * 
	 * @Title: getAllAdminInfo   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @return      
	 * @return: List<Admin>      
	 * @throws
	 */
	public List<Admin> getAllAdminInfo();

	public PageInfo<Admin> getAllAdmin(String username, Integer page, Integer size);
}
