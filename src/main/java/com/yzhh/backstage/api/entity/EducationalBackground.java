package com.yzhh.backstage.api.entity;

/**
 * @description:教育背景
 * @projectName:backstage-api
 * @className:EducationalBackground.java
 * @author:wentao
 * @createTime:2018年6月27日 下午9:11:03
 * @version 1.0.1
 */
public class EducationalBackground extends BaseEntity{

	private static final long serialVersionUID = 1L;
	private Long resumeId;			//
    private Long startTime;			//
    private Long endTime;				//
    private Integer eduation;			//
    private String city;					//
    private String school;				//
    private Integer majorType;		//
    private String major;				//
    private String majorCourses;	//
    private Integer ranking;			//
    private String honor;				//


    public Long getResumeId() {
        return resumeId;
    }

    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
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

    public Integer getEduation() {
        return eduation;
    }

    public void setEduation(Integer eduation) {
        this.eduation = eduation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school == null ? null : school.trim();
    }

    public Integer getMajorType() {
        return majorType;
    }

    public void setMajorType(Integer majorType) {
        this.majorType = majorType;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major == null ? null : major.trim();
    }

    public String getMajorCourses() {
        return majorCourses;
    }

    public void setMajorCourses(String majorCourses) {
        this.majorCourses = majorCourses == null ? null : majorCourses.trim();
    }

    public Integer getRanking() {
        return ranking;
    }

    public void setRanking(Integer ranking) {
        this.ranking = ranking;
    }

    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor == null ? null : honor.trim();
    }
}