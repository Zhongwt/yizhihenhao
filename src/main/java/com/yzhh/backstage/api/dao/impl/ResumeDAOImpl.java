package com.yzhh.backstage.api.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yzhh.backstage.api.dao.IResumeDAO;
import com.yzhh.backstage.api.dao.mapper.custom.ResumeMapper2;
import com.yzhh.backstage.api.dto.resume.PageResumeDTO;
import com.yzhh.backstage.api.entity.Resume;
import com.yzhh.backstage.api.entity.ResumeExample;
import com.yzhh.backstage.api.enums.IsDeleteEnum;
import com.yzhh.backstage.api.util.CollectionUtils;

@Repository("resumeDAO")
public class ResumeDAOImpl extends DAOImpl<Resume, ResumeExample> implements IResumeDAO {

	@Autowired
	private ResumeMapper2 mapper2;
	
	@Override
	public List<PageResumeDTO> queryByPage(Map<String, Object> params) {
		return mapper2.queryByPage(params);
	}

	@Override
	public Long countByPage(Map<String, Object> params) {
		return mapper2.countByPage(params);
	}

	@Override
	public List<String> getName(Map<String, Object> params) {
		return mapper2.getName(params);
	}

	@Override
	public Long countResume(Map<String, Object> params) {
		return mapper2.countResume(params);
	}

	@Override
	public Resume getJobSeekerDefaultResume(Long jobSeekerId) {
		ResumeExample example = new ResumeExample();
		example.createCriteria().andJobSeekerIdEqualTo(jobSeekerId).andIsDefaultEqualTo(IsDeleteEnum.nomal.getId());
		List<Resume> list = mapper.selectByExample(example);
		if(CollectionUtils.isNotEmpty(list)) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public Long isDelivery(Map<String, Object> params) {
		return mapper2.isDeliveryPosition(params);
	}
}
