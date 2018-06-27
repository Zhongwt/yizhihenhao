package com.yzhh.backstage.api.entity;

/**
 * @description:投递简历
 * @projectName:backstage-api
 * @className:DeliveryResume.java
 * @author:wentao
 * @createTime:2018年6月27日 下午9:10:02
 * @version 1.0.1
 */
public class DeliveryResume extends BaseEntity{

	private static final long serialVersionUID = 1L;
	private Long resumeId;			//
    private Long positionId;			//
    private Long deliveryTime;		//
    private Integer status;				//

    public Long getResumeId() {
        return resumeId;
    }

    public void setResumeId(Long resumeId) {
        this.resumeId = resumeId;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public Long getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Long deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}