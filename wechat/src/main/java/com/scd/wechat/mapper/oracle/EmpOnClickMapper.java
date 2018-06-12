package com.scd.wechat.mapper.oracle;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.scd.wechat.entity.EmpOnClick;

@Repository
public interface EmpOnClickMapper {
	
	@Insert("insert into EMP_ONCLICK (EMP_ONCLICK_CODE,EMP_ONCLICK_TIME) values (#{EMP_ONCLICK_CODE},to_date(#{EMP_ONCLICK_TIME},'yyyy-mm-dd hh24:mi:ss'))")
	int save(EmpOnClick empOnClick);
	@Select("selct * from EMP_ONCLICK")
	List<EmpOnClick> find(); 
	
}
