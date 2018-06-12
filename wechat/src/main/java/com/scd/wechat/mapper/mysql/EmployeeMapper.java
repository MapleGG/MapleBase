package com.scd.wechat.mapper.mysql;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.scd.wechat.entity.Employee;


@Repository
public interface EmployeeMapper {

    int insert(Employee record);

    int insertSelective(Employee record);

    int updateByPrimaryKeySelective(Employee record);

    int updateByPrimaryKey(Employee record);
    
    int employeeCount();//查找员工总数
    
    List<Employee> selectByPage(@Param("pageindex") int pageindex,@Param("limit") int limit);//数据库分页
    
    Employee selectByScode(String scode);//根据员工代号查找员工S4M10880->10880

	Employee selectByOpenID(String code);//根据微信获得的openID查询员工

	Integer insertOpenID(@Param("openId") String openId,@Param("employeeId") String employeeId);//插入openId
}