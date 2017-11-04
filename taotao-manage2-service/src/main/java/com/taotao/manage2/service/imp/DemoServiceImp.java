package com.taotao.manage2.service.imp;

import org.springframework.stereotype.Service;

import com.taotao.manage2.service.DemoService;


@Service
public class DemoServiceImp implements DemoService{

	@Override
	public String demo() {
		
		return "hello";
	}

}
