package scd.service;

import java.util.List;


import scd.entity.Employee;

public interface EmployeeService {
	
	Employee findEmpBycodeAndscode(String code,String scode);

	List<Employee> findEmp(int pageindex, int pageSize);
	
	int count();
}
