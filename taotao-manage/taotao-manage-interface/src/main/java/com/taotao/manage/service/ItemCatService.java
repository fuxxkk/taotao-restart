package com.taotao.manage.service;

import java.util.List;

import com.taotao.manage.pojo.ItemCat;

public interface ItemCatService {
	/**
	 * 
	 * @param page 当前页
	 * @param rows 页大小
	 * @return
	 */
	List<ItemCat> queryItemCatByPage(Integer page,Integer rows);
}
