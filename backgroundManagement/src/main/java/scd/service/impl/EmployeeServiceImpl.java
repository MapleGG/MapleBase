package scd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import scd.entity.Employee;
import scd.mapper.EmployeeMapper;
import scd.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeMapper employeeMapper;

	@Override
	public Employee findEmpBycodeAndscode(String employee_code, String employee_scode) {
		// TODO Auto-generated method stub
		return employeeMapper.findEmpBycodeAndscode(employee_code, employee_scode);
	}

	@Override
	public List<Employee> findEmp(int pageindex, int pageSize) {
		// TODO Auto-generated method stub
		return employeeMapper.finEmp(pageindex,pageSize);
	}

	@Override
	public int count() {
		// TODO Auto-generated method stub
		return employeeMapper.count();
	}
}
