package com.scd.mrpm01b.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scd.mrpm01b.entity.Mrpm01a;
import com.scd.mrpm01b.mapper.Mrpm01bMapper;
import com.scd.mrpm01b.services.Mrpm01bService;
@Service
public class Mrpm01bServiceImpl implements Mrpm01bService {
	
	@Autowired
	private Mrpm01bMapper mrpm01bMapper;
	
	@Override
	public int save(Mrpm01a mrpm01b) {
		// TODO Auto-generated method stub
		return mrpm01bMapper.save(mrpm01b);
	}

}
