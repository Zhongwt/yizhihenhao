package com.yzhh.backstage.api.dao.mapper.custom;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.yzhh.backstage.api.dto.jobseeker.DeliveryDTO;
import com.yzhh.backstage.api.entity.DeliveryResume;

@Mapper
public interface DeliveryResumeMapper2{
   
	//获取投递支付记录
	public List<DeliveryDTO> queryByPage(Map<String,Object> params);
	
	public Long countByPage(Map<String, Object> params);
	
	//获取某个简历是否投某个公司
	public List<DeliveryResume> getDeliveryResume(@Param("resumeId")Long resumeId,@Param("companyId")Long companyId);
	
	//获取投递次数
	public Long queryByDeliveryCount(@Param("jobSeekerId") Long jobSeekerId);
}