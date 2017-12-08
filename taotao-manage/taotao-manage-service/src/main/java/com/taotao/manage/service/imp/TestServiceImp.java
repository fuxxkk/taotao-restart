package com.taotao.manage.service.imp;

import com.taotao.manage.mapper.TestMapper;
import com.taotao.manage.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImp implements TestService {
	
	@Autowired
	private TestMapper testMapper;
	
	@Override
	public String queryCurrentDate() {
		return testMapper.queryCurrentDate();
	}
	
	

}
