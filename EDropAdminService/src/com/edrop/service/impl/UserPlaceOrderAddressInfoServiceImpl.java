package com.edrop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.edrop.mapper.UserPlaceOrderAddressInfoMapper;
import com.edrop.pojo.UserPlaceOrderAddressInfo;
import com.edrop.service.UserPlaceOrderAddressInfoService;

@Service
public class UserPlaceOrderAddressInfoServiceImpl implements UserPlaceOrderAddressInfoService {
	@Resource
	private UserPlaceOrderAddressInfoMapper userPlaceOrderAddressInfoMapper;
	@Override
	public String getOrderAddressInfo() {
		List<UserPlaceOrderAddressInfo> list = userPlaceOrderAddressInfoMapper
				.selectOrderAddressInfo();
//		String ans = JSON.toJSONString(list);
//		{"coord": ["114.56003229572319", "38.03209121512538"], "elevation": 397}
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
