package com.edrop.service;

import java.util.List;

/**
 * service interface of user operation statistics
 * @ClassName:  UserOperationStatisticsService   
 * @Description:TODO
 * @author: zcx 
 * @date: 2020年4月29日 下午12:12:57     
 * @Copyright: 2020 com.edrop. All rights reserved.
 */
public interface UserOperationStatisticsService {
	/**
	 * by year get user operation data
	 * @Title: getUserOperationByYear   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param year
	 * @param: @param userOrEmployee
	 * @param: @param loginOrRegister
	 * @param: @return      
	 * @return: List<Integer>      
	 * @throws
	 */
	public String getUserOperationData(String userOrEmployee, String loginOrRegister);

	/**
	 * add a new operation for user or employee
	 * @Title: addOneNewOperation   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param year
	 * @param: @param month
	 * @param: @param day
	 * @param: @param userOrEmployee
	 * @param: @param loginOrRegister
	 * @param: @return      
	 * @return: Integer      
	 * @throws
	 */
	public Integer addOneNewOperation(Integer year, Integer month, Integer day, 
			String userOrEmployee, String loginOrRegister, Integer times);
}
