package com.yzhh.backstage.api.dao;

import java.util.List;

public interface IDAO<T, Texample> {
	Long countByExample(Texample example);

	Long deleteByExample(Texample example);

	Long deleteByPrimaryKey(Long id);

    List<T> selectByExample(Texample example);

    Long updateByExample(T record, Texample example);

    Long updateByPrimaryKey(T record);
    
    Long insert(T record);

    Long insertSelective(T record);

    T selectByPrimaryKey(Long id);

    Long updateByExampleSelective(T record, Texample example);

    Long updateByPrimaryKeySelective(T record);
}
