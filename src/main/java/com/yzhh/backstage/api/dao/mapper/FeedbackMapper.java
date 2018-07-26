package com.yzhh.backstage.api.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.yzhh.backstage.api.entity.Feedback;
import com.yzhh.backstage.api.entity.FeedbackExample;

@Mapper
public interface FeedbackMapper extends BaseMapper<Feedback, FeedbackExample>{
}