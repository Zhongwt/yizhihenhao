package com.yzhh.backstage.api.dto.company;

public class StatisticsDTO {
	
	private Long pendingResumeCount;			//多少待处理简历
	private Long notlookResumeCount;				//为查看简历
	private Long underwayPositionCount;			//在招职位
	private Long overduePositionCount;			//即将过期
	private Long alreadyExpiredPositionCount;	//已经过期职位
	private Long simpleTreatmentPercentage;	//简历处理率
	private Long acceptResumeCount;				//收到简历数量
	private Long releasePositionCount;				//发布职位数量
	private Long interviewCount;						//面试数量
	
	public Long getPendingResumeCount() {
		return pendingResumeCount;
	}
	public void setPendingResumeCount(Long pendingResumeCount) {
		this.pendingResumeCount = pendingResumeCount;
	}
	public Long getNotlookResumeCount() {
		return notlookResumeCount;
	}
	public void setNotlookResumeCount(Long notlookResumeCount) {
		this.notlookResumeCount = notlookResumeCount;
	}
	public Long getUnderwayPositionCount() {
		return underwayPositionCount;
	}
	public void setUnderwayPositionCount(Long underwayPositionCount) {
		this.underwayPositionCount = underwayPositionCount;
	}
	public Long getOverduePositionCount() {
		return overduePositionCount;
	}
	public void setOverduePositionCount(Long overduePositionCount) {
		this.overduePositionCount = overduePositionCount;
	}
	public Long getAlreadyExpiredPositionCount() {
		return alreadyExpiredPositionCount;
	}
	public void setAlreadyExpiredPositionCount(Long alreadyExpiredPositionCount) {
		this.alreadyExpiredPositionCount = alreadyExpiredPositionCount;
	}
	public Long getSimpleTreatmentPercentage() {
		return simpleTreatmentPercentage;
	}
	public void setSimpleTreatmentPercentage(Long simpleTreatmentPercentage) {
		this.simpleTreatmentPercentage = simpleTreatmentPercentage;
	}
	public Long getAcceptResumeCount() {
		return acceptResumeCount;
	}
	public void setAcceptResumeCount(Long acceptResumeCount) {
		this.acceptResumeCount = acceptResumeCount;
	}
	public Long getReleasePositionCount() {
		return releasePositionCount;
	}
	public void setReleasePositionCount(Long releasePositionCount) {
		this.releasePositionCount = releasePositionCount;
	}
	public Long getInterviewCount() {
		return interviewCount;
	}
	public void setInterviewCount(Long interviewCount) {
		this.interviewCount = interviewCount;
	}
}
