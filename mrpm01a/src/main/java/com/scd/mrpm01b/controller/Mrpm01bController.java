package com.scd.mrpm01b.controller;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.scd.mrpm01b.entity.Mrpm01a;
import com.scd.mrpm01b.services.Mrpm01bService;

import net.sf.json.JSONObject;

@Controller
public class Mrpm01bController {

	@Autowired
	private Mrpm01bService mrpm01bService;
	/**
	 * 转到添加页面
	 * @return
	 */
	@RequestMapping("/")
	public String toSaveMrpm01b() {
		return "SaveMrpm01b";
	}
	/**
	 * 添加
	 * @param mrpm01b
	 * @return
	 */
	@RequestMapping("/SaveMrpm01a")
	public ModelAndView save(boolean MRPM01B_SMT1,boolean MRPM01B_DIN1,boolean MRPM01B_DTE1,boolean MRPM01B_DPK1,@RequestParam("MRPM01B_FAC")String MRPM01B_FAC,@RequestParam("MRPM01B_MOS")String MRPM01B_MOS) {
		ModelAndView modelAndView=new ModelAndView();
		Mrpm01a mrpm01b=new Mrpm01a();
		if(MRPM01B_SMT1) {
			mrpm01b.setMRPM01A_SMT("y");
		}else {
			mrpm01b.setMRPM01A_SMT("n");
		}
		if(MRPM01B_DIN1) {
			mrpm01b.setMRPM01A_DIN("y");
		}else {
			mrpm01b.setMRPM01A_DIN("n");
		}
		if(MRPM01B_DTE1) {
			mrpm01b.setMRPM01A_DTE("y");
		}else {
			mrpm01b.setMRPM01A_DTE("n");
		}
		if(MRPM01B_DPK1) {
			mrpm01b.setMRPM01A_DPK("y");
		}else {
			mrpm01b.setMRPM01A_DPK("n");
		}
		if(MRPM01B_FAC!=null && MRPM01B_FAC!="") {
			mrpm01b.setMRPM01A_FAC(MRPM01B_FAC);
		}else {
			modelAndView.setViewName("SaveMrpm01b");
			modelAndView.addObject("error", "添加失败！错误信息：厂别不能为空。请重新输入~");
			return modelAndView;
		}
		if(MRPM01B_MOS!=null) {
			mrpm01b.setMRPM01A_MOS(MRPM01B_MOS);
		}else {
			mrpm01b.setMRPM01A_MOS("");
		}
		System.out.println("+++++++++++"+mrpm01b.getMRPM01A_FAC()+mrpm01b.getMRPM01A_DIN()+mrpm01b.getMRPM01A_DPK()+mrpm01b.getMRPM01A_DTE()+mrpm01b.getMRPM01A_SMT());
		int count=mrpm01bService.save(mrpm01b);
		if(count>0) {
			modelAndView.setViewName("SaveMrpm01b");
			return modelAndView;
		}else {
			modelAndView.setViewName("SaveMrpm01b");
			modelAndView.addObject("error", "添加失败！错误信息：数据库未能插入数据");
			return modelAndView;
		}
		
	}
	/**
	 * 检查工号
	 * @return
	 */
	@RequestMapping("/checkMos")
	@ResponseBody
	public JSONObject checkMos(String mos1) {
		URL url1;
		JSONObject json=new JSONObject();
		try {
			url1 = new URL("http://192.168.16.10/cgi-cgi/wip/check-barcode.pl?rm=state1_validate"+"&mos="+mos1);
			HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
			
			
			conn.connect();
			
			if(conn.getResponseCode() == 200) {
				InputStream in = conn.getInputStream();
				
				byte[] buf = new byte[1024];
				
				int len = -1;
				StringBuilder sb = new StringBuilder();
				while((len = in.read(buf)) != -1) {
					
					sb.append(new String(buf,0,len));
					
				}
								
				String content = sb.toString();
				json.put("mesg",content);				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return json;
		
	} 
}
