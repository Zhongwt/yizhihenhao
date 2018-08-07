package com.yzhh.backstage.api.dto.position;

import java.util.Set;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="职位搜索实体类")
public class SearchPositionDTO {

	@ApiModelProperty(value = "搜索关键字")
	private String searchKey;
	@ApiModelProperty(value = "搜索状态，有：招聘中，已下线，已过期，未审核，求职者端不用传")
	private String status;
	@ApiModelProperty(value = "公司id，用于限定某一个特定公司")
	private Long companyId;
	@ApiModelProperty(value = "城市")
	private String city;
	@ApiModelProperty(value = "日薪")
	private String perDiem;			//日薪
	@ApiModelProperty(value = "周工作天数")
	private String workTime;			//周工作天数
	@ApiModelProperty(value = "工作时长（一个月或长期）")
	private String workDate;			//工作时长（1一个月或长期）
	@ApiModelProperty(value = "要求学历")
	private String education;			//要求学历
	@ApiModelProperty(value = "转正机会")
	private String correctionChance;	//转正机会
	@ApiModelProperty(value = "职位类别")
	private String type;					//职位类别
	@ApiModelProperty(value = "职位名称")
	private String name;				//职位名称
	@ApiModelProperty(value = "工作类型")
	private String workType;			//工作类型
	
	//private String schoolType;
	
	@ApiModelProperty(value = "收藏职位id集合，前端不用传")
	private Set<Long> positionIds;
	
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
	public Set<Long> getPositionIds() {
		return positionIds;
	}
	public void setPositionIds(Set<Long> positionIds) {
		this.positionIds = positionIds;
	}
}
