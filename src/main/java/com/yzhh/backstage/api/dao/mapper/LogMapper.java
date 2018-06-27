package com.yzhh.backstage.api.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.yzhh.backstage.api.entity.Log;
import com.yzhh.backstage.api.entity.LogExample;
@Mapper
public interface LogMapper extends BaseMapper<Log, LogExample>{
   
}