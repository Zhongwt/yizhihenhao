package com.yzhh.backstage.api.entity;

public class Resume extends BaseEntity{

	private static final long serialVersionUID = 1L;
	private Long jobSeekerId;
    private String name;
    private Integer isDefault;
    private String wishPositionName;
    private String wishCity;
    private Integer workDay;
    private String workType;
    private String internshipTime;
    private Double perDiem;
    private Long arrivalDay;
    private Integer isDelete;

    public Long getJobSeekerId() {
        return jobSeekerId;
    }

    public void setJobSeekerId(Long jobSeekerId) {
        this.jobSeekerId = jobSeekerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public String getWishPositionName() {
        return wishPositionName;
    }

    public void setWishPositionName(String wishPositionName) {
        this.wishPositionName = wishPositionName == null ? null : wishPositionName.trim();
    }

    public String getWishCity() {
        return wishCity;
    }

    public void setWishCity(String wishCity) {
        this.wishCity = wishCity == null ? null : wishCity.trim();
    }

    public Integer getWorkDay() {
        return workDay;
    }

    public void setWorkDay(Integer workDay) {
        this.workDay = workDay;
    }

    public String getWorkType() {
        return workType;
    }

    public void setWorkType(String workType) {
        this.workType = workType == null ? null : workType.trim();
    }

    public String getInternshipTime() {
        return internshipTime;
    }

    public void setInternshipTime(String internshipTime) {
        this.internshipTime = internshipTime == null ? null : internshipTime.trim();
    }

    public Double getPerDiem() {
        return perDiem;
    }

    public void setPerDiem(Double perDiem) {
        this.perDiem = perDiem;
    }

    public Long getArrivalDay() {
        return arrivalDay;
    }

    public void setArrivalDay(Long arrivalDay) {
        this.arrivalDay = arrivalDay;
    }

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
}