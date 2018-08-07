package com.yzhh.backstage.api.dao;

import com.yzhh.backstage.api.entity.CompanyJobSeeker;
import com.yzhh.backstage.api.entity.CompanyJobSeekerExample;

public interface ICompanyJobSeekerDAO extends IDAO<CompanyJobSeeker, CompanyJobSeekerExample> {

	//判断一个公司是否下载过一个用户的简历
	public boolean comfirmIsPay(Long companyId,Long jobSeekerId);
}
