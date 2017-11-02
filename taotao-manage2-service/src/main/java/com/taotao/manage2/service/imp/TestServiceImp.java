package com.taotao.manage2.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.manage2.mapper.TestMapper;
import com.taotao.manage2.service.TestService;

@Service
public class TestServiceImp implements TestService{
	
	@Autowired
	private TestMapper testMapper;
	
	@Override
	public String queryCurrentDate() {
		//aaaa
		return testMapper.queryCurrentDate();
	}

}
