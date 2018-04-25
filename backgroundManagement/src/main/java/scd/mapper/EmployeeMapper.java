package scd.mapper;

import scd.entity.Employee;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {
	
	@Select(value= {"SELECT * FROM employee  limit #{pageindex},#{pageSize}   "})
	public List<Employee> finEmp (@Param(value = "pageindex")int pageindex, @Param(value = "pageSize")int pageSize);
	
	@Select(value= {"SELECT count(*) FROM employee   "})
	public int count();

	@Select(value= {"SELECT * FROM employee WHERE employee_code =#{employee_code} AND employee_scode =#{employee_scode}"})
	@Results(value= {
			@Result(column="employee_code" , property="employee_code"),		
			@Result(column="employee_scode" , property="employee_scode"),
			@Result(column="employee_name" , property="employee_name"),
			@Result(column="employee_title" , property="employee_title"),
			@Result(column="employee_line" , property="employee_line"),
			@Result(column="employee_sex" , property="employee_sex"),
			@Result(column="employee_province" , property="employee_province"),
			@Result(column="employee_volk" , property="employee_volk"),
			@Result(column="employee_education" , property="employee_education")
	})
	public Employee findEmpBycodeAndscode(@Param(value = "employee_code") String employee_code,@Param(value = "employee_scode") String employee_scode);
}
