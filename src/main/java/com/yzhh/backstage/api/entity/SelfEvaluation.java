package com.yzhh.backstage.api.entity;

/**
 * @description:自我描述
 * @projectName:backstage-api
 * @className:SelfEvaluation.java
 * @author:wentao
 * @createTime:2018年6月27日 下午9:18:49
 * @version 1.0.1
 */
public class SelfEvaluation extends BaseEntity{

	private static final long serialVersionUID = 1L;
	private Long resumeId;		//
    private String description;	//

    public Long getResumeId() {
        return resumeId;
    }

    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}