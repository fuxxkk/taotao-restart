package com.taotao.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.vo.DatagridResult;
import com.taotao.manage.pojo.Item;
import com.taotao.manage.service.ItemService;

@RequestMapping("item")
@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> saveItem(Item item,@RequestParam(value="desc",required=false)String desc){
		
		try {
			itemService.saveItem(item, desc);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.status(500).body(null);
	}
	
	@RequestMapping(value="update",method=RequestMethod.POST)
	@ResponseBody
	public void updateItem(Item item,@RequestParam(value="desc",required=false)String desc) {
		try {
			itemService.updateItem(item, desc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<DatagridResult> queryItemList(@RequestParam(value="title",required=false)String title,Integer page,Integer rows){
		
		try {
			DatagridResult result = itemService.queryItemList(title, page, rows);
			return ResponseEntity.ok().body(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.status(500).body(null);
	}
}
