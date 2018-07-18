package com.yzhh.backstage.api.dao.mapper.custom;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.yzhh.backstage.api.dto.jobseeker.DeliveryDTO;

@Mapper
public interface DeliveryResumeMapper2{
   
	//获取投递支付记录
	public List<DeliveryDTO> queryByPage(Map<String,Object> params);
	
	public Long countByPage(Map<String, Object> params);
	
}