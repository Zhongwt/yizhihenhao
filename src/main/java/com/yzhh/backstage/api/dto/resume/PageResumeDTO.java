package com.yzhh.backstage.api.dto.resume;

import io.swagger.annotations.ApiModelProperty;

public class PageResumeDTO {

	@ApiModelProperty(value = "简历id")
	private Long id;
	@ApiModelProperty(value = "投递简历id")
	private Long deliveryResumeId;
	@ApiModelProperty(value = "投递时间")
	private String deliveryTime;
	@ApiModelProperty(value = "期望工作")
	private String wishPositionName;
	@ApiModelProperty(value = "简历名称")
	private String name;
	@ApiModelProperty(value = "投递用户名称")
	private String jobSeekerName;
	@ApiModelProperty(value = "性别")
	private String sex;
	@ApiModelProperty(value = "城市")
	private String city;
	@ApiModelProperty(value = "期望工作天数")
	private String workDay;
	@ApiModelProperty(value = "实习时间")
	private String internshipTime;
	@ApiModelProperty(value = "预计到达时间")
	private String arrivalDay;
	@ApiModelProperty(value = "毕业学校")
	private String graduationSchool;
	@ApiModelProperty(value = "学历")
	private String education;
	@ApiModelProperty(value = "专业")
	private String major;
	@ApiModelProperty(value = "毕业时间")
	private String graduationTime;
	@ApiModelProperty(value = "面试时间")
	private String interviewTime;
	@ApiModelProperty(value = "简历状态")
	private String status;
	@ApiModelProperty(value = "公司名称")
	private String companyName;
	@ApiModelProperty(value = "公司id")
	private String companyId;
	@ApiModelProperty(value = "公司地址")
	private String companyAddress;
	@ApiModelProperty(value = "公司logo")
	private String companyLogo;
	@ApiModelProperty(value = "职位id")
	private Long positionId;
	@ApiModelProperty(value = "职位名称")
	private String positionName;
	@ApiModelProperty(value = "职位类型")
	private String positionType;
	@ApiModelProperty(value = "面试地址")
	private String interviewAddress;
	@ApiModelProperty(value = "面试联系电话")
	private String interviewPhone;
	@ApiModelProperty(value = "面试联系人")
	private String interviewContract;
	@ApiModelProperty(value = "面试备注")
	private String interviewNote;
	
	
	public Long getDeliveryResumeId() {
		return deliveryResumeId;
	}
	public void setDeliveryResumeId(Long deliveryResumeId) {
		this.deliveryResumeId = deliveryResumeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getJobSeekerName() {
		return jobSeekerName;
	}
	public void setJobSeekerName(String jobSeekerName) {
		this.jobSeekerName = jobSeekerName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getWorkDay() {
		return workDay;
	}
	public void setWorkDay(String workDay) {
		this.workDay = workDay;
	}
	public String getInternshipTime() {
		return internshipTime;
	}
	public void setInternshipTime(String internshipTime) {
		this.internshipTime = internshipTime;
	}
	public String getArrivalDay() {
		return arrivalDay;
	}
	public void setArrivalDay(String arrivalDay) {
		this.arrivalDay = arrivalDay;
	}
	public String getGraduationSchool() {
		return graduationSchool;
	}
	public void setGraduationSchool(String graduationSchool) {
		this.graduationSchool = graduationSchool;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getGraduationTime() {
		return graduationTime;
	}
	public void setGraduationTime(String graduationTime) {
		this.graduationTime = graduationTime;
	}
	public String getInterviewTime() {
		return interviewTime;
	}
	public void setInterviewTime(String interviewTime) {
		this.interviewTime = interviewTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public String getCompanyLogo() {
		return companyLogo;
	}
	public void setCompanyLogo(String companyLogo) {
		this.companyLogo = companyLogo;
	}
	public String getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getWishPositionName() {
		return wishPositionName;
	}
	public void setWishPositionName(String wishPositionName) {
		this.wishPositionName = wishPositionName;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getPositionType() {
		return positionType;
	}
	public void setPositionType(String positionType) {
		this.positionType = positionType;
	}
	public String getInterviewAddress() {
		return interviewAddress;
	}
	public void setInterviewAddress(String interviewAddress) {
		this.interviewAddress = interviewAddress;
	}
	public String getInterviewPhone() {
		return interviewPhone;
	}
	public void setInterviewPhone(String interviewPhone) {
		this.interviewPhone = interviewPhone;
	}
	public String getInterviewContract() {
		return interviewContract;
	}
	public void setInterviewContract(String interviewContract) {
		this.interviewContract = interviewContract;
	}
	public String getInterviewNote() {
		return interviewNote;
	}
	public void setInterviewNote(String interviewNote) {
		this.interviewNote = interviewNote;
	}
	public String getCompanyAddress() {
		return companyAddress;
	}
	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}
	public Long getPositionId() {
		return positionId;
	}
	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}
}
