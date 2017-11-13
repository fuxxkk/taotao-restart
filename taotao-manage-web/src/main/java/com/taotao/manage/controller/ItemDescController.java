package com.taotao.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taotao.manage.pojo.ItemDesc;
import com.taotao.manage.service.ItemDescService;

@RequestMapping("item")
public class ItemDescController {

	@Autowired
	private ItemDescService descService;
	
	@RequestMapping(value = "desc",method=RequestMethod.GET)
	public ResponseEntity<ItemDesc> queryItemDescById(@PathVariable("id")Long id){
		
		
		
		return ResponseEntity.status(500).body(null);
	}
}
