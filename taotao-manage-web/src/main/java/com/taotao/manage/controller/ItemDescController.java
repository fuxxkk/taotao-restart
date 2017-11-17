package com.taotao.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.taotao.manage.pojo.ItemDesc;
import com.taotao.manage.service.ItemDescService;

@Controller
@RequestMapping("item")
public class ItemDescController {

	@Autowired
	private ItemDescService descService;
	
	@RequestMapping(value = "desc/{itemId}",method=RequestMethod.GET)
	public ResponseEntity<ItemDesc> queryItemDescById(@PathVariable("itemId")Long id){
		
		try {
			ItemDesc itemDesc = descService.queryById(id);
			return ResponseEntity.ok(itemDesc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.status(500).body(null);
	}
}
