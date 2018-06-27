package com.yzhh.backstage.api.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.yzhh.backstage.api.entity.SelfEvaluation;
import com.yzhh.backstage.api.entity.SelfEvaluationExample;
@Mapper
public interface SelfEvaluationMapper extends BaseMapper<SelfEvaluation, SelfEvaluationExample>{
    
}