package com.taotao.manage.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.taotao.manage.mapper.ItemCatMapper;
import com.taotao.manage.pojo.ItemCat;
import com.taotao.manage.service.ItemCatService;

@Service
public class ItemCatServiceImp implements ItemCatService{
	
	@Autowired
	private ItemCatMapper itemCatMapper;
	
	@Override
	public List<ItemCat> queryItemCatByPage(Integer page, Integer rows) {
		
		PageHelper.startPage(page,rows);
		
		List<ItemCat> list = itemCatMapper.selectAll();
		
		return list;
	}

}
