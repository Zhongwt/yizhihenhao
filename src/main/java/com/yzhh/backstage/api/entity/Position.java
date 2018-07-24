package com.yzhh.backstage.api.entity;

public class Position extends BaseEntity{

	private static final long serialVersionUID = 1L;

	private Long companyId;

    private Long releaseDate;

    private String type;

    private String name;

    private String province;

    private String city;

    private String area;

    private Integer perpleNum;

    private String seduction;

    private String description;

    private String education;

    private String address;

    private String workType;

    private String internshipTime;

    private String workTime;

    private String correctionChance;

    private Integer status;

    private Long deadline;

    private String perDiem;

    private Boolean isPressing;

    private String workDate;
    
    private String optionNote;
    
    private String required;

    public String getRequired() {
		return required;
	}

	public void setRequired(String required) {
		this.required = required;
	}

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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
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

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime == null ? null : workTime.trim();
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

    public String getPerDiem() {
        return perDiem;
    }

    public void setPerDiem(String perDiem) {
        this.perDiem = perDiem == null ? null : perDiem.trim();
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
        this.workDate = workDate == null ? null : workDate.trim();
    }

	public String getOptionNote() {
		return optionNote;
	}

	public void setOptionNote(String optionNote) {
		this.optionNote = optionNote;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}