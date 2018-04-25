package scd.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import scd.entity.Employee;
import scd.service.EmployeeService;

@Controller 
public class LoginController {

	@Autowired
	private EmployeeService  employeeService;//业务逻辑
	
	
	private static Employee emp;//定义实体类  方便引用head时使用
	
	/**
	 * 跳转到登录界面
	 * @return
	 */
	@RequestMapping("/")
	public String ToLogin() {
		
		return "login";
		
	}
	/**
	 * 登录到主界面
	 * @param employee_code
	 * @param employee_scode
	 * @param session
	 * @return
	 */
	@RequestMapping("/login.html")
	public ModelAndView Login(@RequestParam("employee_code")String employee_code,@RequestParam("employee_scode")String employee_scode,Model model) {
		 emp=employeeService.findEmpBycodeAndscode(employee_code, employee_scode);
		
		ModelAndView modelAndView=new ModelAndView();
		
		if(emp==null) {
			modelAndView.setViewName("login");
			return modelAndView;
		}
		System.out.println(emp.getEmployee_name());
		modelAndView.addObject("employee_code",emp.getEmployee_code());
		modelAndView.addObject("employee_scode",emp.getEmployee_scode());
		//modelAndView.setViewName("index");
		modelAndView.setViewName("test");
		return modelAndView;
	}
	
	/**
	 * 头部加载
	 * @return
	 */
	@RequestMapping("/head.html")
	public ModelAndView ToHead() {
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("head");
		modelAndView.addObject("emp", employeeService.findEmpBycodeAndscode(emp.getEmployee_code(), emp.getEmployee_scode()));
		modelAndView.addObject("employee_name", employeeService.findEmpBycodeAndscode(emp.getEmployee_code(), emp.getEmployee_scode()).getEmployee_name());
		return modelAndView;
	}
	/**
	 * 左边选项加载
	 * @return
	 */
	@RequestMapping("/left.html")
	public String Left() {
		
		return "left";
	}
	/**
	 * 主页面加载
	 * @return
	 */
	@RequestMapping("/main.html")
	public String ToMain() {
		return "main";
	}
	
	/**
	 * 退出登录
	 * @return
	 */
	@RequestMapping("/head2.html")
	public String ToHead2() {
		return "head2";
	}
	
	/**
	 * 返回所有员工数据
	 * @return
	 */
	@RequestMapping("/empAll.do")
    @ResponseBody
	public JSONObject empAll(HttpServletRequest request) {
		JSONArray arr=new JSONArray();
		int page = Integer.parseInt(request.getParameter("page"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		
		System.out.println("===================="+page+"===================="+limit);
	     page=(page-1)*limit;
		List<Employee> list=employeeService.findEmp(page, limit);
		for (int i = 0; i < list.size(); i++) {
			JSONObject json=new JSONObject() ;


			json.put("EMPLOYEE_CODE",list.get(i).getEmployee_code() );
			json.put("EMPLOYEE_EDUCATION",list.get(i).getEmployee_education() );
			json.put("EMPLOYEE_LINE",list.get(i).getEmployee_line() );
			json.put("employee_name",list.get(i).getEmployee_name() );
			json.put("EMPLOYEE_PROVINCE",list.get(i).getEmployee_province() );
			json.put("EMPLOYEE_SCODE",list.get(i).getEmployee_scode() );
			json.put("EMPLOYEE_SEX",list.get(i).getEmployee_sex() );
			json.put("EMPLOYEE_TITLE",list.get(i).getEmployee_title() );
			json.put("EMPLOYEE_VOLK",list.get(i).getEmployee_volk() );
			arr.add(json);
		}
		JSONObject json1=new JSONObject() ;
		json1.put("code",0);
        json1.put("msg","");
        json1.put("count",employeeService.count());
		json1.put("data", arr);
		//System.out.println(json1);
		return json1;
	}
}
