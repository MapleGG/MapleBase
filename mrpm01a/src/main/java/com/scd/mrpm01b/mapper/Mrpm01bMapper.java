package com.scd.mrpm01b.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

import com.scd.mrpm01b.entity.Mrpm01a;

@Mapper
public interface Mrpm01bMapper {

	@Insert(" INSERT INTO MRPM01A(MRPM01A_ID,MRPM01A_MOS,"
			+ "MRPM01A_SMT,MRPM01A_DIN,MRPM01A_DTE, "
			+ "MRPM01A_DPK,MRPM01A_FAC) VALUES(#{MRPM01A_ID},"
			+ "#{MRPM01A_MOS},#{MRPM01A_SMT},#{MRPM01A_DIN},"
			+ "#{MRPM01A_DTE}, #{MRPM01A_DPK},#{MRPM01A_FAC})")
	@Results(value= {
			@Result(column="MRPM01A_MOS" , property="MRPM01A_MOS"),		
			@Result(column="MRPM01A_SMT" , property="MRPM01A_SMT"),
			@Result(column="MRPM01A_DPK" , property="MRPM01A_DPK"),
			@Result(column="MRPM01A_DTE" , property="MRPM01A_DTE"),
			@Result(column="MRPM01A_DIN" , property="MRPM01A_DIN"),
			@Result(column="MRPM01A_FAC" , property="MRPM01A_FAC")
	})
	public int save(Mrpm01a mrpm01b);
}
