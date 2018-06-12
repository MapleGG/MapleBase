package com.scd.excelData.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.scd.excelData.entity.Employee;


public interface EmployeeMapper {

	@Select(value = { "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_SCODE=#{employeeScode} OR EMPLOYEE_CODE=#{employeeScode}" })
    @Results(value = {
            @Result(column = "EMPLOYEE_ID",property = "employeeId"),
            @Result(column = "EMPLOYEE_SCODE",property = "employeeScode"),
            @Result(column = "EMPLOYEE_NAME",property = "employeeName"),
            @Result(column = "EMPLOYEE_CODE",property = "employeeCode")
    })
    public Employee getByNumber(@Param("employeeScode")String employeeScode);
}
