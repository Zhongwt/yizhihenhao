package com.yzhh.backstage.api.entity;

/**
 * @description:能力爱好
 * @projectName:backstage-api
 * @className:SkillHobby.java
 * @author:wentao
 * @createTime:2018年6月27日 下午9:19:15
 * @version 1.0.1
 */
public class SkillHobby extends BaseEntity{

	private static final long serialVersionUID = 1L;
	private Long resumeId;		//
    private String name;			//
    private Integer level;			//

    public Long getResumeId() {
        return resumeId;
    }

    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}