package com.yzhh.backstage.api.entity;

/**
 * @description:作品展示
 * @projectName:backstage-api
 * @className:WorksShow.java
 * @author:wentao
 * @createTime:2018年6月27日 下午9:19:52
 * @version 1.0.1
 */
public class WorksShow extends BaseEntity{

	private static final long serialVersionUID = 1L;
	private Long resumeId;			//
    private String worksUrl;			//
    private String description;		//

    public Long getResumeId() {
        return resumeId;
    }

    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }

    public String getWorksUrl() {
        return worksUrl;
    }

    public void setWorksUrl(String worksUrl) {
        this.worksUrl = worksUrl == null ? null : worksUrl.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }
}