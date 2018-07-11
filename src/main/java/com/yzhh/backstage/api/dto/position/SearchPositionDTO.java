package com.yzhh.backstage.api.dto.position;

public class SearchPositionDTO {

	private String searchKey;
	private String status;
	private Long companyId;
	private String city;
	private String perDiem;			//日薪
	private String workTime;			//周工作天数
	private String workDate;			//工作时长（1一个月或长期）
	private String education;			//要求学历
	private String correctionChance;	//转正机会
	private String type;					//职位类别
	private String name;				//职位名称
	private String workType;			//工作类型
	
	public String getSearchKey() {
		return searchKey;
	}
	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPerDiem() {
		return perDiem;
	}
	public void setPerDiem(String perDiem) {
		this.perDiem = perDiem;
	}
	public String getWorkTime() {
		return workTime;
	}
	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}
	public String getWorkDate() {
		return workDate;
	}
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getCorrectionChance() {
		return correctionChance;
	}
	public void setCorrectionChance(String correctionChance) {
		this.correctionChance = correctionChance;
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
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}
}
