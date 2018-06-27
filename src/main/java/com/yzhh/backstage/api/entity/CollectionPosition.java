package com.yzhh.backstage.api.entity;

/**
 * @description:用户职位收藏
 * @projectName:backstage-api
 * @className:CollectionPosition.java
 * @author:wentao
 * @createTime:2018年6月27日 下午9:07:27
 * @version 1.0.1
 */
public class CollectionPosition extends BaseEntity{

	private static final long serialVersionUID = 1L;
	private Long jobSeekerId;
    private Long positionId;

    public Long getJobSeekerId() {
        return jobSeekerId;
    }

    public void setJobSeekerId(Long jobSeekerId) {
        this.jobSeekerId = jobSeekerId;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }
}