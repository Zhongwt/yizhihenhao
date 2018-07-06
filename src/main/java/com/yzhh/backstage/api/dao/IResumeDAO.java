package com.yzhh.backstage.api.dao;

import java.util.List;
import java.util.Map;

import com.yzhh.backstage.api.dto.resume.PageResumeDTO;
import com.yzhh.backstage.api.entity.Resume;
import com.yzhh.backstage.api.entity.ResumeExample;

public interface IResumeDAO extends IDAO<Resume, ResumeExample> {

	// 分页查询日志
	public List<PageResumeDTO> queryByPage(Map<String, Object> params);

	// 计算公司数量
	public Long countByPage(Map<String, Object> params);

	// 通过简历id获取简历名称
	public List<String> getName(Map<String, Object> params);
	
	//根据条件获取简历数量
	public Long countResume(Map<String,Object> params);
}
