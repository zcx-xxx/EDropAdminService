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
	public List<Integer> getUserOperationDataByYear(Integer year, String userOrEmployee, String loginOrRegister) {
		List<UserOperationStatistics> list = userOperationStatisticsMapper
				.selectUserOperationDataByYear(year, userOrEmployee, loginOrRegister);
		
		// 统计每月的人数
		List<Integer> ans = new ArrayList<Integer>();
		ans.clear();
		for (int i = 0; i <= 12; ++i) {
			ans.add(0);
		}
		if (null != list) {
			for (int i = 0; i < list.size(); ++i) {
				Integer month = list.get(i).getMonth();
				ans.set(month, ans.get(month) + list.get(i).getTotalOperationTimes());
			}
		} else {
			System.out.println("list is null");
		}
		return ans;
	}
	
	@Override
	public Integer addOneNewOperation(Integer year, Integer month, Integer day, 
			String userOrEmployee, String loginOrRegister) {
		// 查询【今天】【userOrEmployee】的 【loginOrRegister】操作是否存在
		UserOperationStatistics record = userOperationStatisticsMapper
				.selectRowByAllColumn(year, month, day, userOrEmployee, loginOrRegister);
		// 存在更新
		Integer ans = -1;
		if (null != record) {
			Integer id = record.getId();
			Integer counts = record.getTotalOperationTimes();
			ans = userOperationStatisticsMapper.updateUserOperationDataById(id, counts + 1);
		} else {
			// 否则插入新的数据
			ans = userOperationStatisticsMapper.insertNewUserOperationData(year, month, day, userOrEmployee, 
					loginOrRegister, 1);
		}
		
		return ans;
	}
}
