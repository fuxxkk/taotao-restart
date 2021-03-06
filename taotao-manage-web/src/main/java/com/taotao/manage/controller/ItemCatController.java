package com.taotao.manage.controller;

import java.util.List;

import com.taotao.manage.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.manage.pojo.ItemCat;

@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
	
	@Autowired
	private ItemCatService itemCatService;
	
	
	@RequestMapping("query/{page}")
	public ResponseEntity<List<ItemCat>> queryItemcatByPage(@PathVariable Integer page,@RequestParam(value="rows",defaultValue="10") Integer rows){
		
		try {
			List<ItemCat> list = itemCatService.queryListByPage(page, rows);
			return ResponseEntity.ok().body(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		
	}
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ItemCat>> queryItemCatByParentId(@RequestParam(value="id",defaultValue="0")Long parentId){
		
		try {
			ItemCat cat = new ItemCat();
			cat.setParentId(parentId);
			List<ItemCat> resultList = itemCatService.queryListByWhere(cat);
			return ResponseEntity.ok(resultList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.status(500).body(null);
	}
	
	@RequestMapping(value = "{id}",method=RequestMethod.GET)
	public ResponseEntity<ItemCat> queryItemCatById(@PathVariable("id")Long id){
		
		try {
			ItemCat itemcat = itemCatService.queryById(id);
			return ResponseEntity.ok(itemcat);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.status(500).body(null);
	}
}
