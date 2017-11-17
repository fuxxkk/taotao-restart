package com.taotao.manage.service.imp;

import com.taotao.common.vo.DatagridResult;
import com.taotao.manage.pojo.Content;
import com.taotao.manage.service.BaseService;

public interface ContentService extends BaseService<Content>{
	DatagridResult queryListByCCid(Long categoryId,Integer page,Integer rows);
}
