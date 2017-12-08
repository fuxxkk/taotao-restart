package com.taotao.manage.service.imp;

import java.net.URLDecoder;
import java.util.Date;
import java.util.List;

import com.taotao.manage.mapper.ItemDescMapper;
import com.taotao.manage.mapper.ItemMapper;
import com.taotao.manage.pojo.Item;
import com.taotao.manage.pojo.ItemDesc;
import com.taotao.manage.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.vo.DatagridResult;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class ItemServiceImp extends BaseServiceImp<Item> implements ItemService {
	
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

	@Override
	public DatagridResult queryItemList(String title, Integer page, Integer rows) {
		
		Example example = new Example(Item.class);
		if(StringUtils.isNotEmpty(title)) {
			try {
				Criteria criteria = example.createCriteria();
				//解码
				title = URLDecoder.decode(title, "utf-8");
				criteria.andLike("title","%"+title+"%");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		example.orderBy("updated").desc();
		PageHelper.startPage(page,rows);
		List<Item> reusultList = itemMapper.selectByExample(example);
		PageInfo<Item> pageInfo = new PageInfo<>(reusultList);
		DatagridResult datagridResult = new DatagridResult();
		datagridResult.setRows(reusultList);
		datagridResult.setTotal(pageInfo.getTotal());
		
		return datagridResult;
	}

}
