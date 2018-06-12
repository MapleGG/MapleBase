package com.scd.wechat.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.scd.wechat.entity.Employee;


@Service
@Transactional
public interface EmployeeService {
	
	/*根据scode字段S4M10880->10880查找员工*/
	public Employee selectByScode(String employeeScode);

	//根据微信获取到的openid获取员工
	public Employee selectByOpenID(String code);

	//将微信openID插入到数据库
	public Integer insertOpenID(String openId, String employeeId);

	//上班打卡业务逻辑（打卡）
	public JSONObject wechatClockIn(String openId, String area, String time);

	//上班打卡业务逻辑（绑定）
	public JSONObject appClockIn(String employeeId, String openId);
	

}