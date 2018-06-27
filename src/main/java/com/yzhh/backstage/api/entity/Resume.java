package com.yzhh.backstage.api.entity;

/**
 * @description:简历表
 * @projectName:backstage-api
 * @className:Resume.java
 * @author:wentao
 * @createTime:2018年6月27日 下午9:17:43
 * @version 1.0.1
 */
public class Resume extends BaseEntity{

	private static final long serialVersionUID = 1L;

	private Long jobSeekerId;				//
    private String name;						//
    private Integer isDefault;					//
    private String wishPositionName;	//
    private String wishCity;					//
    private Integer workDay;					//
    private Long fieldStart;					//
    private Long fieldEnd;						//
    private Double perDiem;					//
    private Long arrivalDay;					//

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

    public Long getFieldStart() {
        return fieldStart;
    }

    public void setFieldStart(Long fieldStart) {
        this.fieldStart = fieldStart;
    }

    public Long getFieldEnd() {
        return fieldEnd;
    }

    public void setFieldEnd(Long fieldEnd) {
        this.fieldEnd = fieldEnd;
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
}