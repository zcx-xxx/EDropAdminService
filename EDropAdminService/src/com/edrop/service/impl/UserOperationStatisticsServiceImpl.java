package com.edrop.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.edrop.mapper.UserOperationStatisticsMapper;
import com.edrop.pojo.UserOperationStatistics;
import com.edrop.service.UserOperationStatisticsService;
/**
 * implements class of UserOperationStatisticsService
 * @ClassName:  UserOperationStatisticsServiceImpl   
 * @Description:TODO
 * @author: zcx 
 * @date: 2020年4月29日 下午12:14:16     
 * @Copyright: 2020 com.edrop. All rights reserved.
 */
@Service
public class UserOperationStatisticsServiceImpl implements UserOperationStatisticsService{
	@Resource
	private UserOperationStatisticsMapper userOperationStatisticsMapper;

	@Override
	public String getUserOperationData(String userOrEmployee, String loginOrRegister) {
		List<UserOperationStatistics> list = userOperationStatisticsMapper
				.selectUserOperationData(userOrEmployee, loginOrRegister);
		
		StringBuffer ans = new StringBuffer();
		ans.append("[");
		if (null != list) {
			for (int i = 0; i < list.size(); ++i) {
				UserOperationStatistics obj = list.get(i);
				ans.append("[\"" + obj.getYear() + "-" + obj.getMonth() + "-" + obj.getDay() + "\", "
						+ obj.getTotalOperationTimes() + "],");
			}
		}
		if (ans.length() > 1) {
			ans.deleteCharAt(ans.length() - 1);
		}
		ans.append("]");
		
		return ans.toString();
	}
	
	@Override
	public Integer addOneNewOperation(Integer year, Integer month, Integer day, 
			String userOrEmployee, String loginOrRegister, Integer times) {
		// 查询【今天】【userOrEmployee】的 【loginOrRegister】操作是否存在
		UserOperationStatistics record = userOperationStatisticsMapper
				.selectRowByAllColumn(year, month, day, userOrEmployee, loginOrRegister);
		// 存在更新
		Integer ans = -1;
		if (null == record) {
			userOperationStatisticsMapper.insertNewUserOperationData(year, month, day, userOrEmployee, 
					loginOrRegister, times);
		} else {
			Integer id = record.getId();
			Integer counts = record.getTotalOperationTimes();
			ans = userOperationStatisticsMapper.updateUserOperationDataById(id, counts + times);
		}
		
		return ans;
	}
}
