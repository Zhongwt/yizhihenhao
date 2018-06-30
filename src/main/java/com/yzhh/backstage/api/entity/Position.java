package com.yzhh.backstage.api.entity;

public class Position extends BaseEntity{

	private static final long serialVersionUID = 1L;
	private Long companyId;				//	公司id
    private Long releaseDate;			//发布日期
    private String type;						//职务类型
    private String name;					//职位名称
    private String city;						//城市
    private Integer perpleNum;			//招聘人数
    private String seduction;				//职业诱惑
    private String description;			//描述
    private String education;				//学历要求
    private String address;					//地址
    private String workType;				//工作类型（周末兼职，暑期工，寒假工）
    private String internshipTime;		//实习时长
    private Double perDiem;				//日薪
    private Integer workTime;			//工作时间
    private String correctionChance;	//转正机会
    private Integer status;					//状态
    private Long deadline;					//截止时间

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Long releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
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
        this.seduction = seduction == null ? null : seduction.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education == null ? null : education.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
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
        this.correctionChance = correctionChance == null ? null : correctionChance.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getDeadline() {
        return deadline;
    }

    public void setDeadline(Long deadline) {
        this.deadline = deadline;
    }
}