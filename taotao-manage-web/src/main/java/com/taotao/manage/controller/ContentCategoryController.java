package com.taotao.manage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.manage.pojo.ContentCategory;
import com.taotao.manage.service.ContentCategoryService;

@Controller
@RequestMapping("content/category")
public class ContentCategoryController {
	@Autowired
	private ContentCategoryService contentCategoryService;
	
	/**
	 * 查询列表
	 * @param id
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ContentCategory>> queryListByParentId(@RequestParam(value="id",defaultValue="0")Long id){
		
		try {
			ContentCategory contentCategory = new ContentCategory();
			contentCategory.setParentId(id);
			List<ContentCategory> list = contentCategoryService.queryListByWhere(contentCategory);
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.status(500).body(null);
	}
	
	@RequestMapping(value="add",method=RequestMethod.POST)
	public ResponseEntity<ContentCategory> addContentCategory(ContentCategory contentCategory){
		
		
		return ResponseEntity.status(500).body(null);
	}
	
}
