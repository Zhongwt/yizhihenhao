package com.yzhh.backstage.api.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yzhh.backstage.api.commons.BizException;
import com.yzhh.backstage.api.constans.Constants;
import com.yzhh.backstage.api.dao.ICollectionPositionDAO;
import com.yzhh.backstage.api.dao.IDeliveryResumeDAO;
import com.yzhh.backstage.api.dao.IJobSeekerDAO;
import com.yzhh.backstage.api.dao.IPositionDAO;
import com.yzhh.backstage.api.dao.IResumeDAO;
import com.yzhh.backstage.api.dto.UserDTO;
import com.yzhh.backstage.api.dto.wx.WeChatUserInfo;
import com.yzhh.backstage.api.entity.CollectionPosition;
import com.yzhh.backstage.api.entity.CollectionPositionExample;
import com.yzhh.backstage.api.entity.DeliveryResume;
import com.yzhh.backstage.api.entity.JobSeeker;
import com.yzhh.backstage.api.entity.Position;
import com.yzhh.backstage.api.entity.Resume;
import com.yzhh.backstage.api.enums.DeliveryResumeStatusEnum;
import com.yzhh.backstage.api.enums.PositionStatusEnum;
import com.yzhh.backstage.api.service.IJobSeekerService;
import com.yzhh.backstage.api.util.CollectionUtils;
import com.yzhh.backstage.api.util.RedisUtil;

@Service
public class JobSeekerServiceImpl implements IJobSeekerService {

	@Autowired
	private ICollectionPositionDAO collectionPositionDAO;
	@Autowired
	private IResumeDAO resumeDAO;
	@Autowired
	private IPositionDAO positionDAO;
	@Autowired
	private IDeliveryResumeDAO deliveryResumeDAO;
	@Autowired
	private IJobSeekerDAO jobSeekerDAO;
	@Autowired
	private RedisUtil redisUtil;

	@Override
	public boolean isCollectionPosition(Long positionId, Long jobSeekerId) {
		CollectionPositionExample example = new CollectionPositionExample();
		example.createCriteria().andPositionIdEqualTo(positionId).andJobSeekerIdEqualTo(jobSeekerId);
		List<CollectionPosition> list = collectionPositionDAO.selectByExample(example);
		if(CollectionUtils.isNotEmpty(list)) {
			return true;
		}
		return false;
	}

	@Override
	public void collectionPosition(Long positionId, Long jobSeekerId) {
		CollectionPosition collectionPosition = new CollectionPosition();
		collectionPosition.setJobSeekerId(jobSeekerId);
		collectionPosition.setPositionId(positionId);
		collectionPosition.setLastAccess(new Date().getTime());
		collectionPositionDAO.insertSelective(collectionPosition);
	}

	@Override
	public void deliveryPosition(Long jobSeekerId, Long positionId) {
		Resume resume = resumeDAO.getJobSeekerDefaultResume(jobSeekerId);
		Date date = new Date();
		if(resume == null) {
			throw new BizException("请先设置默认简历");
		}
		Position position = positionDAO.selectByPrimaryKey(positionId);
		if(position == null) {
			throw new BizException("职位未找到");
		}
		
		if(position.getStatus().intValue() != PositionStatusEnum.audited.getId()) {
			throw new BizException("职位已不在招聘中");
		}
		if(position.getStatus().intValue() != PositionStatusEnum.audited.getId()) {
			throw new BizException("职位已不在招聘中");
		}
		if(date.after(new Date(position.getDeadline()))) {
			throw new BizException("职位招聘已截止");
		}
		
		DeliveryResume deliveryResume = new DeliveryResume();
		deliveryResume.setLastAccess(date.getTime());
		deliveryResume.setResumeId(resume.getId());
		deliveryResume.setDeliveryTime(date.getTime());
		deliveryResume.setStatus(DeliveryResumeStatusEnum.notlook.getId());
		deliveryResumeDAO.insertSelective(deliveryResume);
	}

	@Override
	public UserDTO login(WeChatUserInfo user) {
		
		JobSeeker jobSeeker = jobSeekerDAO.getJobSeekerByOpenId(user.getOpenId());
		if(jobSeeker == null) {
			jobSeeker = new JobSeeker();
			jobSeeker.setName(user.getNickname());
			jobSeeker.setOpenId(user.getOpenId());
			jobSeeker.setImageUrl(user.getHeadImgUrl());
			jobSeeker.setIsAuth(user.getSubscribe());
			jobSeeker.setSex(user.getSex() == 1 ? "男" : "女");
			jobSeeker.setProvince(user.getProvince());
			jobSeeker.setCity(user.getCity());
			jobSeekerDAO.insertSelective(jobSeeker);
		}
		
		UserDTO userDTO = new UserDTO();
		userDTO.setId(jobSeeker.getId());
		userDTO.setEmail(jobSeeker.getEmail());
		userDTO.setName(jobSeeker.getName());
		userDTO.setPhone(jobSeeker.getPhone());
		userDTO.setPicUrl(jobSeeker.getImageUrl());
		
		redisUtil.set(Constants.USER_LOGIN +userDTO.getId(), userDTO,Constants.TWO_HOUR);
		
		return userDTO;
	}
	
}












