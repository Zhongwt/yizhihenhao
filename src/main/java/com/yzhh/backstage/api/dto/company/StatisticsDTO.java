package com.yzhh.backstage.api.dto.company;

import io.swagger.annotations.ApiModelProperty;

public class StatisticsDTO {
	
	@ApiModelProperty(value = "多少待处理简历")
	private Long pendingResumeCount;			//
	@ApiModelProperty(value = "未查看简历")
	private Long notlookResumeCount;				//
	@ApiModelProperty(value = "在招职位")
	private Long underwayPositionCount;			//
	@ApiModelProperty(value = "即将过期")
	private Long overduePositionCount;			//
	@ApiModelProperty(value = "已经过期职位")
	private Long alreadyExpiredPositionCount;	//
	@ApiModelProperty(value = "简历处理率")
	private Long simpleTreatmentPercentage;	//
	@ApiModelProperty(value = "收到简历数量")
	private Long acceptResumeCount;				//
	@ApiModelProperty(value = "发布职位数量")
	private Long releasePositionCount;				//
	@ApiModelProperty(value = "面试数量")
	private Long interviewCount;						//
	@ApiModelProperty(value = "将要面试数量")
	private Long willInterviewCount;					//
	@ApiModelProperty(value = "今日面试数量")
	private Long todayInterviewCount;				//
	
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
	public Long getWillInterviewCount() {
		return willInterviewCount;
	}
	public void setWillInterviewCount(Long willInterviewCount) {
		this.willInterviewCount = willInterviewCount;
	}
	public Long getTodayInterviewCount() {
		return todayInterviewCount;
	}
	public void setTodayInterviewCount(Long todayInterviewCount) {
		this.todayInterviewCount = todayInterviewCount;
	}
}
