package com.yzhh.backstage.api.dto.position;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

public class PositionDTO {

	private Long id;
	@NotNull
	@ApiModelProperty(value="公司id")
	private Long companyId;
	@NotNull
	@ApiModelProperty(value="职位类型")
	private String type;
	@NotNull
	@ApiModelProperty(value="职位名称")
	private String name;
	@NotNull
	@ApiModelProperty(value="城市")
	private String city;
	@ApiModelProperty(value="招聘人数")
	private Integer perpleNum;
	@ApiModelProperty(value="职位诱惑")
	private String seduction;			//职位诱惑
	@NotNull
	@ApiModelProperty(value="学历要求")
	private String education;			//学历
	@ApiModelProperty(value="招聘位置")
	private String address;
	@ApiModelProperty(value="工作类型")
	private String workType;			//工作类型
	@ApiModelProperty(value="实习时间/工作时长")
	private String internshipTime;	//实习时间/工作时长
	@ApiModelProperty(value="日薪")
	private String perDiem;			//日薪
	@ApiModelProperty(value="周工作时间")
	private String workTime;		//周工作时间
	@ApiModelProperty(value="转正机会")
	private String correctionChance;	//转正机会
	@NotNull
	@ApiModelProperty(value="截止日期")
	private String deadline;				//截止日期
	@ApiModelProperty(value="是否急招")
	private Boolean isPressing;			//急招？
	@ApiModelProperty(value="职位描述")
	private String description;			//描述
	@ApiModelProperty(value="发布时间")
	private String releaseDate;			//发布时间
	@ApiModelProperty(value="状态")
	private String status;					//
	@ApiModelProperty(value="更新时间")
	private String updateTime;			//
	@ApiModelProperty(value="投递人数")
	private String deliveryNum;			//
	@ApiModelProperty(value="工作时长")
	private String workDate;				//工作时长
	@ApiModelProperty(value="公司logo")
	private String companyLogo;		//
	@ApiModelProperty(value="公司名称")
	private String companyName;		//
	@ApiModelProperty(value="公司领域")
	private String companyField;			//公司领域
	@ApiModelProperty(value="公司规模")
	private String companyScale;		//公司规模
	@ApiModelProperty(value="公司昵称")
	private String companyNickName;		//公司规模
	@ApiModelProperty(value="是否收藏")
	private Boolean isCollection;		//是否收藏
	@ApiModelProperty(value="简历要求")
	private String required;				//简历要求
	@ApiModelProperty(value="是否投递")
	private Boolean isDelivery;			//是否投递
	
	
	public String getCompanyNickName() {
		return companyNickName;
	}
	public void setCompanyNickName(String companyNickName) {
		this.companyNickName = companyNickName;
	}
	public String getRequired() {
		return required;
	}
	public void setRequired(String required) {
		this.required = required;
	}
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
	public Boolean getIsPressing() {
		return isPressing;
	}
	public void setIsPressing(Boolean isPressing) {
		this.isPressing = isPressing;
	}
	public String getWorkDate() {
		return workDate;
	}
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	public String getCompanyLogo() {
		return companyLogo;
	}
	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyScale() {
		return companyScale;
	}
	public void setCompanyScale(String companyScale) {
		this.companyScale = companyScale;
	}
	public String getCompanyField() {
		return companyField;
	}
	public void setCompanyField(String companyField) {
		this.companyField = companyField;
	}
	public Boolean getIsCollection() {
		return isCollection;
	}
	public void setIsCollection(Boolean isCollection) {
		this.isCollection = isCollection;
	}
	public Boolean getIsDelivery() {
		return isDelivery;
	}
	public void setIsDelivery(Boolean isDelivery) {
		this.isDelivery = isDelivery;
	}
}
