package com.taotao.manage.service.imp;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.vo.DatagridResult;
import com.taotao.manage.mapper.ContentMapper;
import com.taotao.manage.pojo.Content;
import com.taotao.manage.service.ContentService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class ContentServiceImp extends BaseServiceImp<Content> implements ContentService {

	@Autowired
	private ContentMapper contentMapper;
	
	@Override
	public DatagridResult queryListByCCid(Long categoryId, Integer page, Integer rows) {
		
		Example example = new Example(Content.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("categoryId", categoryId);
		example.orderBy("updated").desc();
		
		
		PageHelper.startPage(page, rows);
		List<Content> result = contentMapper.selectByExample(example);
		
		PageInfo<Content> pageInfo = new PageInfo<>(result);
		
		DatagridResult datagridResult = new DatagridResult();
		datagridResult.setRows(pageInfo.getList());
		datagridResult.setTotal(pageInfo.getTotal());
		
		return datagridResult;
	}


}
