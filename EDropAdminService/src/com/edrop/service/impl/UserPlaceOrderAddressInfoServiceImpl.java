package com.edrop.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.edrop.mapper.UserPlaceOrderAddressInfoMapper;
import com.edrop.pojo.UserPlaceOrderAddressInfo;
import com.edrop.service.UserPlaceOrderAddressInfoService;
import com.edrop.utils.ThredQuery;

@Service
public class UserPlaceOrderAddressInfoServiceImpl implements UserPlaceOrderAddressInfoService {
	@Resource
	private UserPlaceOrderAddressInfoMapper userPlaceOrderAddressInfoMapper;
	@Override
	public String getOrderAddressInfo() {
		List<UserPlaceOrderAddressInfo> list = userPlaceOrderAddressInfoMapper
				.selectOrderAddressInfo();
		if (list == null || list.size() == 0) {
			return "";
		} else {
			return transformFormat(list);
		}
	}
	@Override
	public String getOrderAddressInfoByMultipleThread() {
		// 数据条数
		int size = userPlaceOrderAddressInfoMapper.getCountData();
		// 每页大小
		int pageSize = 1000;
		// 页数
		int nums = size / pageSize + (size % pageSize == 0 ? 0 : 1);
		
		// 分装查询请求
		List<Callable<List<UserPlaceOrderAddressInfo>>> tasks = new ArrayList<>();
        for (int i = 0; i < nums; ++i) {
            Callable<List<UserPlaceOrderAddressInfo>> qfe = 
            		new ThredQuery<List<UserPlaceOrderAddressInfo>>
            (userPlaceOrderAddressInfoMapper, i * pageSize, pageSize);
            tasks.add(qfe);
        }
        
        // 定义线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        
        // Future用于获取结果
        List<Future<List<UserPlaceOrderAddressInfo>>> futures = null;
        try {
        	futures = executorService.invokeAll(tasks);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
        
        // 统计查询结果
        List<UserPlaceOrderAddressInfo> ans = new ArrayList<UserPlaceOrderAddressInfo>();
        if (futures != null && futures.size() > 0) {
        	for (int i = 0; i < futures.size(); ++i) {
        		try {
					ans.addAll(futures.get(i).get());
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
        	}
        }
        
        // 关闭线程池
        executorService.shutdown();

		return transformFormat(ans);
	}
	@Override
	public String getOrderAddressInfoByPage(Integer begin, Integer count) {
		List<UserPlaceOrderAddressInfo> list = userPlaceOrderAddressInfoMapper
				.selectOrderAddressInfoByPage(begin, count);
		if (list == null || list.size() == 0) {
			return "";
		} else {
			return transformFormat(list);
		}
	}
	
	/**
	 * 将查询结果转换为指定格式 json 串
	 * @Title: transformFormat   
	 * @Description: TODO(这里用一句话描述这个方法的作用)   
	 * @param: @param list
	 * @param: @return      
	 * @return: String      
	 * @throws
	 */
	public String transformFormat(List<UserPlaceOrderAddressInfo> list) {
		StringBuffer ans = new StringBuffer();
		ans.append("[[");
		if (null != list) {
			for (int i = 0; i < list.size(); ++i) {
				UserPlaceOrderAddressInfo obj = list.get(i);
				ans.append("{\"coord\": [\"" + obj.getLongitude() + "\", \"" + obj.getLatitude() + "\"],"
						+ "\"elevation\":" + obj.getElevation() + "},");
			}
		}
		if (ans.length() > 2) {
			ans.deleteCharAt(ans.length() - 1);
		}
		ans.append("]]");
		return ans.toString();
	}
}
