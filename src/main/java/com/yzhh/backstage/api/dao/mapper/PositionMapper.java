package com.yzhh.backstage.api.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.yzhh.backstage.api.entity.Position;
import com.yzhh.backstage.api.entity.PositionExample;
@Mapper
public interface PositionMapper extends BaseMapper<Position, PositionExample>{
   
}