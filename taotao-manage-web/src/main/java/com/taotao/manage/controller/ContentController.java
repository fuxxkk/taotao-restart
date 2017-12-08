package com.taotao.manage.controller;

import java.util.HashMap;
import java.util.Map;

import com.taotao.manage.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.common.vo.DatagridResult;
import com.taotao.manage.pojo.Content;

@Controller
@RequestMapping("content")
public class ContentController {
	
	@Autowired
	private ContentService contentService;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<DatagridResult> queryListByCCid(@RequestParam("categoryId")Long categoryId,Integer page,Integer rows){
		
		try {
			DatagridResult result = contentService.queryListByCCid(categoryId, page, rows);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(500).body(null);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> addContent(Content content){
		
		try {
			contentService.saveSelective(content);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(500).build();
	}
	
	@RequestMapping(value="edit",method=RequestMethod.POST)
	public ResponseEntity<Void> editContent(Content content){
		
		try {
			contentService.updateSelective(content);
			return ResponseEntity.ok(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(500).build();
	}
	
	@RequestMapping(value="delete",method=RequestMethod.POST)
	public ResponseEntity<Map<String, Integer>> deleteContent(@RequestParam("ids")Long ids){
		
		try {
			contentService.deleteById(ids);
			Map<String, Integer> result = new HashMap<>();
			result.put("status", 200);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.status(500).body(null); 
	}
}
