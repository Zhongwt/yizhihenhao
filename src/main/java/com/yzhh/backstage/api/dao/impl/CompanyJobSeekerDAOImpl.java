package com.yzhh.backstage.api.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yzhh.backstage.api.dao.ICompanyJobSeekerDAO;
import com.yzhh.backstage.api.entity.CompanyJobSeeker;
import com.yzhh.backstage.api.entity.CompanyJobSeekerExample;
import com.yzhh.backstage.api.util.CollectionUtils;

@Repository("companyJobSeekerDAO")
public class CompanyJobSeekerDAOImpl extends DAOImpl<CompanyJobSeeker, CompanyJobSeekerExample> implements ICompanyJobSeekerDAO {

	@Override
	public boolean comfirmIsPay(Long companyId, Long jobSeekerId) {
		CompanyJobSeekerExample example = new CompanyJobSeekerExample();
		example.createCriteria().andCompanyIdEqualTo(companyId).andJobSeekerIdEqualTo(jobSeekerId);
		List<CompanyJobSeeker> list = mapper.selectByExample(example);
		if(CollectionUtils.isNotEmpty(list)) {
			return true;
		}
		return false;
	}


}
