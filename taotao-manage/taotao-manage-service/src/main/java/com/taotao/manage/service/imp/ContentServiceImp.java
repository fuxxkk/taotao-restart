package com.taotao.manage.service.imp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.vo.DatagridResult;
import com.taotao.manage.mapper.ContentMapper;
import com.taotao.manage.pojo.Content;
import com.taotao.manage.service.ContentService;
import com.taotao.manage.service.RedisService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class ContentServiceImp extends BaseServiceImp<Content> implements ContentService {

	@Autowired
	private ContentMapper contentMapper;
	private static final ObjectMapper MAPPER = new ObjectMapper();
	
	@Value("${CONTENT_CATEGORY_BIG_AD_ID}")
	private Long contentCategoryId ;
	@Value("${TAOTAO_PORTAL_INDEX_BIG_AD_NUMBER}")
	private Integer adNum ;
	
	@Autowired
	private RedisService redisService;
	
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

	@Override
	public String queryBigAdData() throws Exception {
		
		try {
			String bigAd = redisService.get("BIG_AD");
			if(StringUtils.isNotEmpty(bigAd)) {
				return bigAd;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		DatagridResult result = queryListByCCid(contentCategoryId, 1, adNum);
		List<Content> rows = (List<Content>) result.getRows();
		
		List<Map<String, Object>> resultList = new ArrayList<>();
		if(rows!=null&&rows.size()>0) {
			for (Content content : rows) {
				Map<String, Object> map = new HashMap<>();
				map.put("alt", content.getTitle());
				map.put("height", 240);
				map.put("heightB", 240);
				map.put("href", content.getUrl());
				map.put("src", content.getPic());
				map.put("srcB", content.getPic2());
				map.put("width", 670);
				map.put("widthB", 550);
				resultList.add(map);
			}
		}
		String resultStr = MAPPER.writeValueAsString(resultList);
		
		try {
			redisService.setex("BIG_AD", 3600,resultStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultStr;
	}


}
