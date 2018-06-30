package com.yzhh.backstage.api.dto.position;

import javax.validation.constraints.NotNull;

public class PositionDTO {

	private Long id;
	@NotNull
	private Long companyId;
	@NotNull
	private String type;
	@NotNull
	private String name;
	@NotNull
	private String city;
	@NotNull
	private Integer perpleNum;
	private String seduction;			//职位诱惑
	@NotNull
	private String education;			//学历
	private String address;
	@NotNull
	private String workType;			//工作类型
	@NotNull
	private String internshipTime;	//实习时间
	@NotNull
	private Double perDiem;			//日薪
	@NotNull
	private Integer workTime;		//周工作时间
	@NotNull
	private String correctionChance;	//转正机会
	@NotNull
	private String deadline;				//截止日期
	private String description;			//描述
	private String releaseDate;			//发布时间
	private String status;					//
	private String updateTime;			//
	private String deliveryNum;			//
	
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Integer getPerpleNum() {
		return perpleNum;
	}
	public void setPerpleNum(Integer perpleNum) {
		this.perpleNum = perpleNum;
	}
	public String getSeduction() {
		return seduction;
	}
	public void setSeduction(String seduction) {
		this.seduction = seduction;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}
	public String getInternshipTime() {
		return internshipTime;
	}
	public void setInternshipTime(String internshipTime) {
		this.internshipTime = internshipTime;
	}
	public Double getPerDiem() {
		return perDiem;
	}
	public void setPerDiem(Double perDiem) {
		this.perDiem = perDiem;
	}
	public Integer getWorkTime() {
		return workTime;
	}
	public void setWorkTime(Integer workTime) {
		this.workTime = workTime;
	}
	public String getCorrectionChance() {
		return correctionChance;
	}
	public void setCorrectionChance(String correctionChance) {
		this.correctionChance = correctionChance;
	}
	public String getDeadline() {
		return deadline;
	}
	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDeliveryNum() {
		return deliveryNum;
	}
	public void setDeliveryNum(String deliveryNum) {
		this.deliveryNum = deliveryNum;
	}
	
}