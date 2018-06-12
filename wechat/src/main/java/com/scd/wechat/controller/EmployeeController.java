package com.scd.wechat.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.scd.wechat.entity.EmpOnClick;
import com.scd.wechat.service.EmployeeService;


@Controller
public class EmployeeController extends BaseController {

	@Autowired
	private EmployeeService employService;
	
	@RequestMapping(value="/show")
	public String show() {
		
		return "show";
	}
	@RequestMapping(value="/showdate")
	public String showdate() {
		
		return "bivector";
	}
	//返回微信端访问结果
		@RequestMapping(value="/bivector.do",method=RequestMethod.POST)
		@ResponseBody
		public JSONObject openId(HttpServletRequest request) throws IOException{
			String code=request.getParameter("code");//微信一次性code，用于获取openId
			String area=request.getParameter("area");//打卡地点
			String time=request.getParameter("time1");//打卡时间
	        String openId =null ; 
	        String appid = "wx43f031f7ad034ec2";
	        String secret = "f7e44bc259d990934f77f2cf8b5f123e";
	        try {
				URL url = new URL("https://api.weixin.qq.com/sns/jscode2session?appid="+appid+"&secret="+secret+"&js_code="+code+"&grant_type=authorization_code");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				
				
				conn.connect();
				
				if(conn.getResponseCode() == 200) {
					InputStream in = conn.getInputStream();
					
					byte[] buf = new byte[1024];
					
					int len = -1;
					StringBuilder sb = new StringBuilder();
					while((len = in.read(buf)) != -1) {
						
						sb.append(new String(buf,0,len));
						
					}
					String[] str = sb.toString().split(",");
					String[] str1 = str[1].split("\\:");
					String[] str2 =str1[1].split("\"");
					openId = str2[1];
				}
				
	        } catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         
			JSONObject jsonobject=new JSONObject();
			if("code been used".equals(openId)) {
				jsonobject.put("state", "openIdnull");
				return jsonobject;	
			}
			if(code!=null&&openId!=null){
				//微信唯一标志相关业务
				jsonobject=employService.wechatClockIn(openId,area,time);
				
			}else {
				jsonobject.put("state","fail");
				jsonobject.put("msg", "notParem");//没有传参数
			}	
			System.out.println(jsonobject.get("state"));
			return jsonobject;		
		}
		
		
		
		@RequestMapping(value="/binding.do",method=RequestMethod.POST)
		@ResponseBody
		public JSONObject binding(HttpServletRequest request) throws IOException {
			
			String code=request.getParameter("code");//微信一次性code，用于获取openId
			String employeeId=request.getParameter("employeeId");//员工工号
	        String openId =null ; 
	        String appid = "wx43f031f7ad034ec2";
	        String secret = "f7e44bc259d990934f77f2cf8b5f123e";
	        try {
	        	//System.out.println(" 获取openid时===》appid:"+appid+"secret:"+secret+"code:"+code);
				URL url = new URL("https://api.weixin.qq.com/sns/jscode2session?appid="+appid+"&secret="+secret+"&js_code="+code+"&grant_type=authorization_code");
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				
				
				conn.connect();
				
				if(conn.getResponseCode() == 200) {
					InputStream in = conn.getInputStream();
					
					byte[] buf = new byte[1024];
					
					int len = -1;
					StringBuilder sb = new StringBuilder();
					while((len = in.read(buf)) != -1) {
						
						sb.append(new String(buf,0,len));
						
					}
					String[] str = sb.toString().split(",");
					String[] str1 = str[1].split("\\:");
					String[] str2 =str1[1].split("\"");
					openId = str2[1];
				}
				
	        } catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        JSONObject jsonObject=new JSONObject();
			if("code been used".equals(openId)) {
				jsonObject.put("state", "openIdnull");
				return jsonObject;	
			}
			if("".equals(employeeId) || employeeId == null) {
				jsonObject.put("state", "notId");
				return jsonObject;
			}
			//根据微信接口获取的openid查找用户
			if(employService.selectByOpenID(openId)==null) {
				jsonObject=employService.appClockIn(employeeId,openId);
			}else {
				jsonObject.put("state", "noWei");
				jsonObject.put("oid", openId);
			}
			System.out.println(jsonObject.get("state"));
			//System.out.println("empid:"+employeeId+",code:"+code+"得到的openid为:"+openId+"==============="+employService.appClockIn(employeeId,openId).get("state"));
			return jsonObject;
		}
}
