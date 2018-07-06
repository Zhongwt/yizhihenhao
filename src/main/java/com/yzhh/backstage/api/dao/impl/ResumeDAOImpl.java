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
}
