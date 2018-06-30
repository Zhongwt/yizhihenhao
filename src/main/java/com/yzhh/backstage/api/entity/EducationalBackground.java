package com.yzhh.backstage.api.entity;

public class EducationalBackground extends BaseEntity{
	private static final long serialVersionUID = 1L;
	private Long resumeId;
    private Long startTime;
    private Long endTime;
    private String eduation;
    private String city;
    private String school;
    private String majorType;
    private String major;
    private String majorCourses;
    private String ranking;
    private String honor;

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

    public String getEduation() {
        return eduation;
    }

    public void setEduation(String eduation) {
        this.eduation = eduation == null ? null : eduation.trim();
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

    public String getMajorType() {
        return majorType;
    }

    public void setMajorType(String majorType) {
        this.majorType = majorType == null ? null : majorType.trim();
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

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking == null ? null : ranking.trim();
    }

    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor == null ? null : honor.trim();
    }
}