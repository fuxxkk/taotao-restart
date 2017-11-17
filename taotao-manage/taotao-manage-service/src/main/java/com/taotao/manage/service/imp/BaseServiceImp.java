package com.taotao.manage.service.imp;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.PageHelper;
import com.taotao.manage.mapper.ItemCatMapper;
import com.taotao.manage.pojo.BasePojo;
import com.taotao.manage.service.BaseService;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

public class BaseServiceImp<T extends BasePojo> implements BaseService<T> {
	
	@Autowired
	private Mapper<T> mapper;
	
	private Class<T> clazz;
	
	 public BaseServiceImp() {
		 
		 ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
		 clazz = (Class<T>) pt.getActualTypeArguments()[0];
		 
	}
	
	@Override
	public T queryById(Serializable id) {
		
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<T> queryAll() {
		return mapper.selectAll();
	}

	@Override
	public Integer queryCountByWhere(T t) {
		return mapper.selectCount(t);
	}

	@Override
	public List<T> queryListByWhere(T t) {
		
		return mapper.select(t);
	}

	@Override
	public List<T> queryListByPage(Integer page, Integer rows) {
		PageHelper.startPage(page, rows);
		return mapper.selectAll();
	}

	@Override
	public void saveSelective(T t) {
		
		if(t.getCreated()==null) {
			t.setCreated(new Date());
			t.setUpdated(new Date());
		}else if(t.getUpdated()==null) {
			t.setUpdated(new Date());
		}
		mapper.insertSelective(t);
		
	}

	@Override
	public void updateSelective(T t) {
		t.setUpdated(new Date());
		mapper.updateByPrimaryKeySelective(t);
		
	}

	@Override
	public void deleteById(Serializable id) {
		mapper.deleteByPrimaryKey(id);
		
	}

	@Override
	public void deleteByIds(Serializable[] ids) {
		Example example = new Example(this.clazz);
		Criteria criteria = example.createCriteria();
		criteria.andIn("id", Arrays.asList(ids));
		mapper.deleteByExample(example);
		
	}

}
