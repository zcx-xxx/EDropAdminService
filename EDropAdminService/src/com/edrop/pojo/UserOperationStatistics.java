package com.edrop.pojo;
/**
 * statistic user operation data
 * @ClassName:  UserOperationStatistics   
 * @Description:TODO
 * @author: zcx 
 * @date: 2020年4月29日 下午12:07:30     
 * @Copyright: 2020 com.edrop. All rights reserved.
 */
public class UserOperationStatistics {
	private Integer id;
	private Integer year;
	private Integer month;
	private Integer day;
	private String userOrEmployee;
	private String loginOrRegister;
	private Integer totalOperationTimes;
	
	public String getLoginOrRegister() {
		return loginOrRegister;
	}
	public void setLoginOrRegister(String loginOrRegister) {
		this.loginOrRegister = loginOrRegister;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Integer getMonth() {
		return month;
	}
	public void setMonth(Integer month) {
		this.month = month;
	}
	public Integer getDay() {
		return day;
	}
	public void setDay(Integer day) {
		this.day = day;
	}
	public String getUserOrEmployee() {
		return userOrEmployee;
	}
	public void setUserOrEmployee(String userOrEmployee) {
		this.userOrEmployee = userOrEmployee;
	}
	public Integer getTotalOperationTimes() {
		return totalOperationTimes;
	}
	public void setTotalOperationTimes(Integer totalOperationTimes) {
		this.totalOperationTimes = totalOperationTimes;
	}
}
