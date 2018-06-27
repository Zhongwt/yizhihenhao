package com.yzhh.backstage.api.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.yzhh.backstage.api.entity.CollectionPosition;
import com.yzhh.backstage.api.entity.CollectionPositionExample;

@Mapper
public interface CollectionPositionMapper extends BaseMapper<CollectionPosition, CollectionPositionExample>{
   
}