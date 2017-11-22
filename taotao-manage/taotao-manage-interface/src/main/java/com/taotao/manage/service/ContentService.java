package com.taotao.manage.service;

import com.taotao.common.vo.DatagridResult;
import com.taotao.manage.pojo.Content;

public interface ContentService extends BaseService<Content>{
	DatagridResult queryListByCCid(Long categoryId,Integer page,Integer rows);
	
	String queryBigAdData() throws Exception;
}
