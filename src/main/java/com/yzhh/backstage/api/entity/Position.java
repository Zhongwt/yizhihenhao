package com.yzhh.backstage.api.entity;

/**
 * @description:发布的职务表
 * @projectName:backstage-api
 * @className:Position.java
 * @author:wentao
 * @createTime:2018年6月27日 下午9:15:55
 * @version 1.0.1
 */
public class Position extends BaseEntity{

	private static final long serialVersionUID = 1L;
	private Long companyId;					//
    private Long releaseDate;				//
    private Integer type;						//
    private String name;						//
    private String city;							//
    private Integer perpleNum;				//
    private String seduction;					//
    private String description;				//
    private Integer education;				//
    private String address;						//
    private Integer isInternship;				//
    private Integer internshipTime;		//
    private Double perDiem;					//
    private Integer workTime;				//
    private Integer correctionChance;	//
    private Integer status;						//
    private Long deadline;						//

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
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

    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getIsInternship() {
        return isInternship;
    }

    public void setIsInternship(Integer isInternship) {
        this.isInternship = isInternship;
    }

    public Integer getInternshipTime() {
        return internshipTime;
    }

    public void setInternshipTime(Integer internshipTime) {
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

    public Integer getCorrectionChance() {
        return correctionChance;
    }

    public void setCorrectionChance(Integer correctionChance) {
        this.correctionChance = correctionChance;
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