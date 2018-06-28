package com.yzhh.backstage.api.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BaseMapper <T, Texample> {
	Long countByExample(Texample example);

	Long deleteByExample(Texample example);

	Long deleteByPrimaryKey(Long id);

    List<T> selectByExample(Texample example);

    Long updateByExample(@Param("record") T record, @Param("example") Texample example);

    Long updateByPrimaryKey(T record);
    
    Long insert(T record);

    Long insertSelective(T record);

    T selectByPrimaryKey(Long id);

    Long updateByExampleSelective(@Param("record") T record, @Param("example") Texample example);

    Long updateByPrimaryKeySelective(T record);
}
