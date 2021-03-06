package com.yzhh.backstage.api.dao.mapper.custom;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.yzhh.backstage.api.dto.resume.PageResumeDTO;
import com.yzhh.backstage.api.dto.resume.ResumeLibDTO;

@Mapper
public interface ResumeMapper2 {

	// 分页查询日志
	public List<PageResumeDTO> queryByPage(Map<String, Object> params);

	// 计算公司数量
	public Long countByPage(Map<String, Object> params);

	// 通过简历id获取简历名称
	public List<String> getName(Map<String, Object> params);

	// 获取简历数量
	public Long countResume(Map<String, Object> params);

	// 获取一个人是否投递了职位
	public Long isDeliveryPosition(Map<String, Object> params);
	
	//分页查简历库
	public List<ResumeLibDTO> queryLibByPage(Map<String,Object> params);
	
	public Long countLibByPage(Map<String,Object> params);
}