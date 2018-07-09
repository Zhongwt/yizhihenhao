package com.yzhh.backstage.api.entity;

public class CompanyJobSeeker extends BaseEntity{

	private static final long serialVersionUID = 1L;
	private Long companyId;
    private Long jobSeekerId;

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getJobSeekerId() {
        return jobSeekerId;
    }

    public void setJobSeekerId(Long jobSeekerId) {
        this.jobSeekerId = jobSeekerId;
    }
}