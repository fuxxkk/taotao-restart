package com.taotao.manage.service.imp;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.manage.mapper.ItemCatMapper;
import com.taotao.manage.mapper.ItemDescMapper;
import com.taotao.manage.mapper.ItemMapper;
import com.taotao.manage.pojo.Item;
import com.taotao.manage.pojo.ItemDesc;
import com.taotao.manage.service.ItemService;

@Service
public class ItemServiceImp extends BaseServiceImp<Item> implements ItemService{
	
	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private ItemDescMapper itemDescMapper;
	
	@Override
	public Long saveItem(Item item, String desc) {
		
		saveSelective(item);
		
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setCreated(new Date());
		itemDesc.setUpdated(new Date());
		itemDesc.setItemDesc(desc);
		itemDesc.setItemId(item.getCid());
		itemDescMapper.insertSelective(itemDesc);
		
		return item.getId();
	}

	@Override
	public void updateItem(Item item, String desc) {
		updateSelective(item);
		
		ItemDesc itemDesc = new ItemDesc();
		itemDesc.setUpdated(new Date());
		itemDesc.setItemDesc(desc);
		itemDesc.setItemId(item.getCid());
		itemDescMapper.updateByPrimaryKeySelective(itemDesc);
	}

}
