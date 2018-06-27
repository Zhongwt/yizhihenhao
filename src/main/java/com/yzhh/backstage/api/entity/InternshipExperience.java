package com.yzhh.backstage.api.entity;

/**
 * @description:实习经历
 * @projectName:backstage-api
 * @className:InternshipExperience.java
 * @author:wentao
 * @createTime:2018年6月27日 下午9:11:24
 * @version 1.0.1
 */
public class InternshipExperience extends BaseEntity{

	private static final long serialVersionUID = 1L;

	private Long resumeId;				//
    private String companyName;		//
    private String city;						//
    private String dutyName;			//
    private Long startTime;				//
    private Long endTime;					//
    private String description;			//

    public Long getResumeId() {
        return resumeId;
    }

    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getDutyName() {
        return dutyName;
    }

    public void setDutyName(String dutyName) {
        this.dutyName = dutyName == null ? null : dutyName.trim();
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}