package com.taotao.manage.service;

import com.taotao.common.vo.DatagridResult;
import com.taotao.manage.pojo.Item;

public interface ItemService extends BaseService<Item>  {
	
	Long saveItem(Item item , String desc);
	
	void updateItem(Item item , String desc);
	
	DatagridResult queryItemList(String title,Integer page,Integer rows);
}
