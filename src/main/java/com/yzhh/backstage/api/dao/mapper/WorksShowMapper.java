package com.yzhh.backstage.api.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.yzhh.backstage.api.entity.WorksShow;
import com.yzhh.backstage.api.entity.WorksShowExample;
@Mapper
public interface WorksShowMapper extends BaseMapper<WorksShow, WorksShowExample>{
    
}