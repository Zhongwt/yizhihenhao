package com.yzhh.backstage.api.dto.resume;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class ResumeDTO {

	private Long id;
	@ApiModelProperty(value = "城市")
	private String city;
	@ApiModelProperty(value = "学历")
	private String education;
	@ApiModelProperty(value = "毕业学校")
	private String graduationSchool;
	@ApiModelProperty(value = "手机号")
	private String phone;
	@ApiModelProperty(value = "电话")
	private String email;
	@ApiModelProperty(value = "求职者Id")
	private Long jobSeekerId;
	@ApiModelProperty(value = "求职者名称")
	private String jobSeekerName;
	@ApiModelProperty(value = "求职者性别")
	private String sex;
	@ApiModelProperty(value = "简历名称")
	private String name;
	@ApiModelProperty(value = "是否默认")
	private Integer isDefault;
	@ApiModelProperty(value = "期望职位")
	private String wishPositionName;
	@ApiModelProperty(value = "期望城市")
	private String wishCity;
	@ApiModelProperty(value = "日薪")
	private String perDiem;
	@ApiModelProperty(value = "周工作天数")
	private String workDay;
	@ApiModelProperty(value = "工作类型")
	private String workType;
	@ApiModelProperty(value = "到岗日期")
	private String arrivalDay;
	@ApiModelProperty(value = "实习时间")
	private String internshipTime;
	
	@ApiModelProperty(value = "教育背景")
	private List<EducationalBackgroundDTO> educationalBackgroundList;
	@ApiModelProperty(value = "实习经历")
	private List<InternshipExperienceDTO> internshipExperienceList;
	@ApiModelProperty(value = "项目经验")
	private List<ProjectExperienceDTO> projectExperienceList;
	@ApiModelProperty(value = "能力爱好")
	private List<SkillHobbyDTO> skillHobbyList;
	@ApiModelProperty(value = "作品展示")
	private List<WorksShowDTO> worksShowList;
	@ApiModelProperty(value = "自我评价")
	private List<SelfEvaluationDTO> selfEvaluationList;
	
	@ApiModelProperty(value = "投递职位id")
	private Long positionId;
	@ApiModelProperty(value = "投递职位名称")
	private String positionName;
	@ApiModelProperty(value = "投递状态")
	private String deliveryStatus;
	@ApiModelProperty(value = "面试时间")
	private String interviewTime;
	@ApiModelProperty(value = "面试地址")
	private String interviewAddress;
	@ApiModelProperty(value = "面试联系电话")
	private String interviewPhone;
	@ApiModelProperty(value = "面试联系人")
	private String interviewContract;
	@ApiModelProperty(value = "面试备注")
	private String interviewNote;
	@ApiModelProperty(value = "是否需要付费")
	private Boolean needPay;
	@ApiModelProperty(value = "价格")
	private Double amount;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getGraduationSchool() {
		return graduationSchool;
	}
	public void setGraduationSchool(String graduationSchool) {
		this.graduationSchool = graduationSchool;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getJobSeekerName() {
		return jobSeekerName;
	}
	public void setJobSeekerName(String jobSeekerName) {
		this.jobSeekerName = jobSeekerName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
		this.wishPositionName = wishPositionName;
	}
	public String getWishCity() {
		return wishCity;
	}
	public void setWishCity(String wishCity) {
		this.wishCity = wishCity;
	}
	public String getPerDiem() {
		return perDiem;
	}
	public void setPerDiem(String perDiem) {
		this.perDiem = perDiem;
	}
	public String getWorkDay() {
		return workDay;
	}
	public void setWorkDay(String workDay) {
		this.workDay = workDay;
	}
	public String getWorkType() {
		return workType;
	}
	public void setWorkType(String workType) {
		this.workType = workType;
	}
	public String getArrivalDay() {
		return arrivalDay;
	}
	public void setArrivalDay(String arrivalDay) {
		this.arrivalDay = arrivalDay;
	}
	public List<EducationalBackgroundDTO> getEducationalBackgroundList() {
		return educationalBackgroundList;
	}
	public void setEducationalBackgroundList(List<EducationalBackgroundDTO> educationalBackgroundList) {
		this.educationalBackgroundList = educationalBackgroundList;
	}
	public List<InternshipExperienceDTO> getInternshipExperienceList() {
		return internshipExperienceList;
	}
	public void setInternshipExperienceList(List<InternshipExperienceDTO> internshipExperienceList) {
		this.internshipExperienceList = internshipExperienceList;
	}
	public List<ProjectExperienceDTO> getProjectExperienceList() {
		return projectExperienceList;
	}
	public void setProjectExperienceList(List<ProjectExperienceDTO> projectExperienceList) {
		this.projectExperienceList = projectExperienceList;
	}
	public List<SkillHobbyDTO> getSkillHobbyList() {
		return skillHobbyList;
	}
	public void setSkillHobbyList(List<SkillHobbyDTO> skillHobbyList) {
		this.skillHobbyList = skillHobbyList;
	}
	public List<WorksShowDTO> getWorksShowList() {
		return worksShowList;
	}
	public void setWorksShowList(List<WorksShowDTO> worksShowList) {
		this.worksShowList = worksShowList;
	}
	public List<SelfEvaluationDTO> getSelfEvaluationList() {
		return selfEvaluationList;
	}
	public void setSelfEvaluationList(List<SelfEvaluationDTO> selfEvaluationList) {
		this.selfEvaluationList = selfEvaluationList;
	}
	public String getInternshipTime() {
		return internshipTime;
	}
	public void setInternshipTime(String internshipTime) {
		this.internshipTime = internshipTime;
	}
	public Long getPositionId() {
		return positionId;
	}
	public void setPositionId(Long positionId) {
		this.positionId = positionId;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getDeliveryStatus() {
		return deliveryStatus;
	}
	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}
	public String getInterviewTime() {
		return interviewTime;
	}
	public void setInterviewTime(String interviewTime) {
		this.interviewTime = interviewTime;
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
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Boolean getNeedPay() {
		return needPay;
	}
	public void setNeedPay(Boolean needPay) {
		this.needPay = needPay;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Long getJobSeekerId() {
		return jobSeekerId;
	}
	public void setJobSeekerId(Long jobSeekerId) {
		this.jobSeekerId = jobSeekerId;
	}
}
