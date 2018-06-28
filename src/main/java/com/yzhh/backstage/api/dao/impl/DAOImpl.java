package com.yzhh.backstage.api.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.yzhh.backstage.api.dao.IDAO;
import com.yzhh.backstage.api.dao.mapper.BaseMapper;

public class DAOImpl<T, Texample> implements IDAO<T, Texample> {
	@Autowired
	public BaseMapper<T, Texample> mapper;
	
	public Long insert(T record) {
		return mapper.insert(record);
	}

	public Long insertSelective(T record) {
		return mapper.insertSelective(record);
	}
	
	public Long deleteByExample(Texample example) {
		return mapper.deleteByExample(example);
	}

	public Long deleteByPrimaryKey(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}

	public Long updateByExample(T record, Texample example) {
		return mapper.updateByExample(record, example);
	}

	public Long updateByPrimaryKey(T record) {
		return mapper.updateByPrimaryKey(record);
	}
	
	public Long updateByExampleSelective(T record, Texample example) {
		return mapper.updateByExampleSelective(record, example);
	}

	public Long updateByPrimaryKeySelective(T record) {
		return mapper.updateByPrimaryKeySelective(record);
	}

	public List<T> selectByExample(Texample example) {
		return mapper.selectByExample(example);
	}

	public Long countByExample(Texample example) {
		return mapper.countByExample(example);
	}

	public T selectByPrimaryKey(Long id) {
		return mapper.selectByPrimaryKey(id);
	}
	
}
