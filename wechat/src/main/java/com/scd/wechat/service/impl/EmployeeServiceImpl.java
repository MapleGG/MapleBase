package com.scd.wechat.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.scd.wechat.entity.EmpOnClick;
import com.scd.wechat.entity.Employee;
import com.scd.wechat.mapper.mysql.EmployeeMapper;
import com.scd.wechat.mapper.oracle.EmpOnClickMapper;
import com.scd.wechat.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeMapper employeeMapper;
	@Autowired
	private EmpOnClickMapper empocm;
	/*根据scode字段S4M10880->10880查找员工*/
	public Employee selectByScode(String employeeScode) {
		Employee record=null;
		if(employeeScode!=null&&!employeeScode.equals("")) {
			//访问mysql/Employeemapper.xml下对应方法
			record=employeeMapper.selectByScode(employeeScode);
		}
		return record;		
	}

	@Override
	public Employee selectByOpenID(String code) {
		return employeeMapper.selectByOpenID(code);
	}

	//将微信openID插入到数据库
	@Override
	public Integer insertOpenID(String openId, String employeeId) {
		return employeeMapper.insertOpenID(openId,employeeId);
	}

	//上班打卡业务逻辑(微信)
	@Override
	public JSONObject wechatClockIn(String openId,String area,String time) {
		// TODO Auto-generated method stub
		Employee employee=new Employee();
		JSONObject jsonobject=new JSONObject();

								
			System.out.println("标志码："+openId);
			if(openId==null) {
				//获取openid失败
				System.out.println("openId为null");
				jsonobject.put("state", "fail");
				jsonobject.put("msg", "getOpenIdFail");//获取用户唯一凭证失败
			}else {
				//根据微信接口获取的openid查找用户
				employee=employeeMapper.selectByOpenID(openId);
				System.out.println(openId);
				if(employee!=null) {
					if("".equals(area) || area == null || !"资讯中心".equals(area)) {
						jsonobject.put("state", "address");
						return jsonobject;
					}
					//成功则进行打卡记录插入
					EmpOnClick empOnClick=new EmpOnClick();
					empOnClick.setEMP_ONCLICK_CODE(employee.getEmployeeCode().toUpperCase());
					Date day=new Date();    
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
					int dc=0;
					try {
						Date date = df.parse(time);
						Date date1 = df.parse(df.format(day));
						dc = (int) ((date1.getTime() - date.getTime())/1000/60);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					System.out.println(dc);
					if(dc<=10) {
						empOnClick.setEMP_ONCLICK_TIME(df.format(day));
						empocm.save(empOnClick);
						jsonobject.put("employeeName", employee.getEmployeeCode()+"-"+employee.getDeptLine()+"-"+employee.getEmployeeName());
						jsonobject.put("state", "success");
					}else if(dc>10){
						jsonobject.put("state", "overtime");//返回二维码超时
						return jsonobject;
					}
				}else {
					jsonobject.put("state", "fail");			
				}
				jsonobject.put("openId", openId);//返回openId
			}						

		return jsonobject;
	}

	@Override
	public JSONObject appClockIn(String employeeId, String openId) {
		JSONObject jsonObject=new JSONObject();
		Employee employee=new Employee();
		//如果有传employeeId则进行openID插入操作
	
			//查找员工是否存在（根据员工工号查找）
			employee=employeeMapper.selectByScode(employeeId);
			if(employee!=null) {
				//如果员工存在则绑定微信openId
				if(employeeMapper.insertOpenID(openId,employeeId)>0) {
					jsonObject.put("employeeName", employee.getEmployeeScode()+"-"+employee.getEmployeeName());
					jsonObject.put("state", "success");
				
				}else {
					jsonObject.put("state", "fail");
					jsonObject.put("msg","insertFail");//唯一凭证插入失败
				}
			}else {
				jsonObject.put("state", "fail");
				jsonObject.put("msg", "noEmployee");//员工不存在
			}					
			

		
		return jsonObject;
	}
	
	
	
}
