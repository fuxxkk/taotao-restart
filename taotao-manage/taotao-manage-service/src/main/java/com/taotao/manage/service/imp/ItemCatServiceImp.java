package com.taotao.manage.service.imp;

import com.taotao.manage.mapper.ItemCatMapper;
import com.taotao.manage.pojo.ItemCat;
import com.taotao.manage.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemCatServiceImp extends BaseServiceImp<ItemCat> implements ItemCatService {
	
	@Autowired
	private ItemCatMapper itemCatMapper;
	

}
