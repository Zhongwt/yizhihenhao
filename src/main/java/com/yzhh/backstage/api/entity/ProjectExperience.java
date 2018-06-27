package com.yzhh.backstage.api.entity;

/**
 * @description:项目经验
 * @projectName:backstage-api
 * @className:ProjectExperience.java
 * @author:wentao
 * @createTime:2018年6月27日 下午9:16:55
 * @version 1.0.1
 */
public class ProjectExperience extends BaseEntity{

	private static final long serialVersionUID = 1L;
	private Long resumeId;				//
    private String projectName;		//
    private String city;						//
    private String role;						//
    private Long startTime;				//
    private Long endTime;					//
    private String description;			//

    public Long getResumeId() {
        return resumeId;
    }

    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName == null ? null : projectName.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
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