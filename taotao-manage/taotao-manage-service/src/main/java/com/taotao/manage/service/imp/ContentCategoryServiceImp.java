package com.taotao.manage.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.manage.mapper.ContentCategoryMapper;
import com.taotao.manage.pojo.ContentCategory;
import com.taotao.manage.service.ContentCategoryService;


@Service
public class ContentCategoryServiceImp extends BaseServiceImp<ContentCategory> implements ContentCategoryService {
	
	@Autowired
	private ContentCategoryMapper contentCategoryMapper;

	@Override
	public ContentCategory saveContentCategory(ContentCategory contentCategory) {
		
		
		
		return null;
	}
	
}
