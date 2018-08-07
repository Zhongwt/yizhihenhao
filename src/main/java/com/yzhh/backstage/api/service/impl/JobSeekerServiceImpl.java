package com.yzhh.backstage.api.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yzhh.backstage.api.commons.BizException;
import com.yzhh.backstage.api.constans.Constants;
import com.yzhh.backstage.api.dao.IAccountDAO;
import com.yzhh.backstage.api.dao.ICollectionPositionDAO;
import com.yzhh.backstage.api.dao.IDeliveryResumeDAO;
import com.yzhh.backstage.api.dao.IFeedBackDAO;
import com.yzhh.backstage.api.dao.IJobSeekerDAO;
import com.yzhh.backstage.api.dao.IPositionDAO;
import com.yzhh.backstage.api.dao.IResumeDAO;
import com.yzhh.backstage.api.dto.UserDTO;
import com.yzhh.backstage.api.dto.jobseeker.FeedBackDTO;
import com.yzhh.backstage.api.dto.jobseeker.JobSeekerDTO;
import com.yzhh.backstage.api.dto.wx.WeChatUserInfo;
import com.yzhh.backstage.api.entity.Account;
import com.yzhh.backstage.api.entity.CollectionPosition;
import com.yzhh.backstage.api.entity.CollectionPositionExample;
import com.yzhh.backstage.api.entity.DeliveryResume;
import com.yzhh.backstage.api.entity.Feedback;
import com.yzhh.backstage.api.entity.JobSeeker;
import com.yzhh.backstage.api.entity.Position;
import com.yzhh.backstage.api.entity.Resume;
import com.yzhh.backstage.api.enums.AccountSettingEnum;
import com.yzhh.backstage.api.enums.AccountTypeEnum;
import com.yzhh.backstage.api.enums.DeliveryResumeStatusEnum;
import com.yzhh.backstage.api.enums.PositionStatusEnum;
import com.yzhh.backstage.api.service.IAccountService;
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
	@Autowired
	private IAccountDAO accountDAO;
	@Autowired
	private IFeedBackDAO feedBackDAO;
	
	@Autowired
	private IAccountService accountService;

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
		
		CollectionPositionExample example = new CollectionPositionExample();
		example.createCriteria()
			.andPositionIdEqualTo(positionId)
			.andJobSeekerIdEqualTo(jobSeekerId);
		List<CollectionPosition> list = collectionPositionDAO.selectByExample(example);
		if(CollectionUtils.isNotEmpty(list)) {
			collectionPositionDAO.deleteByPrimaryKey(list.get(0).getId());
		}else {
			CollectionPosition collectionPosition = new CollectionPosition();
			collectionPosition.setJobSeekerId(jobSeekerId);
			collectionPosition.setPositionId(positionId);
			collectionPosition.setLastAccess(new Date().getTime());
			collectionPositionDAO.insertSelective(collectionPosition);
		}
	}

	@Override
	public void deliveryPosition(Long jobSeekerId, Long positionId,Long resumeId) {
		
		Date date = new Date();
		Account account = accountDAO.getAccountByRelationId(jobSeekerId, AccountTypeEnum.job_seeker.getId());
		if(account == null) {
			throw new BizException("未找到账户信息");
		}
		Double amount = accountService.getAmountSettingByType(AccountSettingEnum.resumes.getName());
		
		if(account.getBalance() -  amount.doubleValue() < 0) {
			throw new BizException("账余额不足");
		}
		
		Resume resume = resumeDAO.selectByPrimaryKey(resumeId);
		if(resume == null) {
			throw new BizException("简历信息未找到");
		}
		Position position = positionDAO.selectByPrimaryKey(positionId);
		if(position == null) {
			throw new BizException("职位信息未找到");
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
		deliveryResume.setPositionId(positionId);
		deliveryResume.setDeliveryTime(date.getTime());
		deliveryResume.setStatus(DeliveryResumeStatusEnum.notlook.getId());
		deliveryResume.setMoney(amount);
		deliveryResumeDAO.insertSelective(deliveryResume);
		
		accountDAO.consumptionWater(account, amount, "用户使用"+resume.getName()+"简历，投递"+position.getName()+"职位，消费"+amount+"元");
	}

	@Override
	public UserDTO login(WeChatUserInfo user) {
		
		JobSeeker jobSeeker = jobSeekerDAO.getJobSeekerByOpenId(user.getOpenId());
		if(jobSeeker == null) {
			Long lastAccess = new Date().getTime();
					
			jobSeeker = new JobSeeker();
			jobSeeker.setLastAccess(lastAccess);
			jobSeeker.setName(user.getNickName());
			jobSeeker.setOpenId(user.getOpenId());
			jobSeeker.setImageUrl(user.getAvatarUrl());
			//jobSeeker.setIsAuth(user.getSubscribe());
			jobSeeker.setSex(user.getGender() == 1 ? "男" : "女");
			//jobSeeker.setProvince(user.getProvince());
			//jobSeeker.setCity(user.getCity());
			jobSeekerDAO.insertSelective(jobSeeker);
			
			Account account = new Account();
			account.setLastAccess(lastAccess);
			account.setType(AccountTypeEnum.job_seeker.getId());
			account.setRelationId(jobSeeker.getId());
			account.setBalance(0d);
			account.setCapital(0d);
			account.setLargess(0d);
			accountDAO.insertSelective(account);
		}
		
		UserDTO userDTO = new UserDTO();
		userDTO.setId(jobSeeker.getId());
		userDTO.setEmail(jobSeeker.getEmail());
		userDTO.setName(jobSeeker.getName());
		userDTO.setPhone(jobSeeker.getPhone());
		userDTO.setPicUrl(jobSeeker.getImageUrl());
		userDTO.setOpenId(jobSeeker.getOpenId());
		userDTO.setSex(jobSeeker.getSex());
		userDTO.setProvince(jobSeeker.getProvince());
		userDTO.setCity(jobSeeker.getCity());
		userDTO.setArea(jobSeeker.getArea());
		userDTO.setEducation(jobSeeker.getEducation());
		userDTO.setGraduationSchool(jobSeeker.getGraduationSchool());
		userDTO.setGraduationTime(jobSeeker.getGraduationTime());
		userDTO.setMajor(jobSeeker.getMajor());
		userDTO.setMajorType(jobSeeker.getMajorType());
		userDTO.setBirthday(jobSeeker.getBirthday());
		userDTO.setNote(jobSeeker.getNote());
		
		redisUtil.set(Constants.JOB_SEEKER_LOGIN +userDTO.getId(), userDTO);
		
		return userDTO;
	}

//	@Override
//	public void paySuccess(Long jobSeekerId, Long positionId,Integer totalFee) {
//		//支付成功  这里给用户记录一笔充值一笔使用
//		JobSeeker jobSeeker = jobSeekerDAO.selectByPrimaryKey(jobSeekerId);
//		Position position = positionDAO.selectByPrimaryKey(positionId);
//		Account account = accountDAO.getAccountByRelationId(jobSeekerId, AccountTypeEnum.job_seeker.getId());
//		if(account == null) {
//			throw new BizException("用户账户为赵到，用户id"+jobSeekerId);
//		}
//		double amount = totalFee/100;
//		accountDAO.recordWater(account, amount, "用户【"+jobSeeker.getName()+"】对职位【"+position.getName()+"】投递简历充值【"+amount+"】元");
//		accountDAO.recordWater(account, -amount, "用户【"+jobSeeker.getName()+"】对职位【"+position.getName()+"】投递简历花费【"+(-amount)+"】元");
//	}

	@Override
	public JobSeekerDTO findById(Long jobSeekerId) {
		
		JobSeeker jobSeeker = this.checkJobSeeker(jobSeekerId);
		
		JobSeekerDTO jobSeekerDTO = new JobSeekerDTO();
		
		jobSeekerDTO.setId(jobSeeker.getId());
		jobSeekerDTO.setName(jobSeeker.getName());
		jobSeekerDTO.setPicUrl(jobSeeker.getImageUrl());
		jobSeekerDTO.setSex(jobSeeker.getSex());
		jobSeekerDTO.setProvince(jobSeeker.getProvince());
		jobSeekerDTO.setCity(jobSeeker.getCity());
		jobSeekerDTO.setArea(jobSeeker.getArea());
		jobSeekerDTO.setBirthday(jobSeeker.getBirthday());
		jobSeekerDTO.setEducation(jobSeeker.getEducation());
		jobSeekerDTO.setGraduationTime(jobSeeker.getGraduationTime());
		jobSeekerDTO.setGraduationSchool(jobSeeker.getGraduationSchool());
		jobSeekerDTO.setMajor(jobSeeker.getMajor());
		jobSeekerDTO.setMajorType(jobSeeker.getMajorType());
		jobSeekerDTO.setPhone(jobSeeker.getPhone());
		jobSeekerDTO.setEmail(jobSeeker.getEmail());
		jobSeekerDTO.setNote(jobSeeker.getNote());
		
		return jobSeekerDTO;
	}
	
	@Override
	public JobSeeker checkJobSeeker(Long jobSeekerId) {
		JobSeeker jobSeeker = jobSeekerDAO.selectByPrimaryKey(jobSeekerId);
		if(jobSeeker == null) {
			throw new BizException("未找到用户信息");
		}
		return jobSeeker;
	}

	@Override
	public void editInfo(JobSeekerDTO jobSeekerDTO) {
		Long lastAccess = new Date().getTime();
		JobSeeker jobSeeker = this.checkJobSeeker(jobSeekerDTO.getId());
		
		jobSeeker.setLastAccess(lastAccess);
		jobSeeker.setName(jobSeekerDTO.getName());
		jobSeeker.setImageUrl(jobSeekerDTO.getPicUrl());
		jobSeeker.setSex(jobSeekerDTO.getSex());
		jobSeeker.setProvince(jobSeekerDTO.getProvince());
		jobSeeker.setCity(jobSeekerDTO.getCity());
		jobSeeker.setArea(jobSeekerDTO.getArea());
		jobSeeker.setBirthday(jobSeekerDTO.getBirthday());
		jobSeeker.setEducation(jobSeekerDTO.getEducation());
		jobSeeker.setGraduationTime(jobSeekerDTO.getGraduationTime());
		jobSeeker.setGraduationSchool(jobSeekerDTO.getGraduationSchool());
		jobSeeker.setMajor(jobSeekerDTO.getMajor());
		jobSeeker.setMajorType(jobSeekerDTO.getMajorType());
		jobSeeker.setPhone(jobSeekerDTO.getPhone());
		jobSeeker.setEmail(jobSeekerDTO.getEmail());
		jobSeeker.setNote(jobSeekerDTO.getNote() == null ? "" : jobSeekerDTO.getNote());
		
		jobSeekerDAO.updateByPrimaryKeySelective(jobSeeker);
		
		//刷新登录信息
		WeChatUserInfo user = new WeChatUserInfo();
		user.setOpenId(jobSeeker.getOpenId());
		login(user);
	}

	@Override
	public String matchResumeAndPosition(Long resumeId, Long positionId) {
		Date date = new Date();
		Resume resume = resumeDAO.selectByPrimaryKey(resumeId);
		if(resume == null) {
			throw new BizException("简历信息未找到");
		}
		JobSeeker jobSeeker = this.checkJobSeeker(resume.getJobSeekerId());
		Position position = positionDAO.selectByPrimaryKey(positionId);
		if(position == null) {
			throw new BizException("职位信息未找到");
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
		
		int matching = 0;
		String str = "";
		String mystr = "";
		
		if(position.getName().equals(resume.getWishPositionName())) {
			matching += 30;
		}else {
			str += "期望职位，";
			mystr += "期望职位："+resume.getWishPositionName() +"，";
		}
		if(position.getCity().equals(resume.getWishCity())) {
			matching += 10;
		}else {
			str += "期望城市，";
			mystr += "期望城市："+resume.getWishCity() +"，";
		}
		if(position.getPerDiem().equals(resume.getPerDiem())) {
			matching += 25;
		}else {
			str += "日薪，";
			mystr += "日薪："+resume.getPerDiem() +"，";
		}
		if(position.getEducation().equals(jobSeeker.getEducation())) {
			matching += 15;
		}else {
			str += "学历，";
			mystr += "学历："+jobSeeker.getEducation() +"，";
		}
		if(position.getWorkDate().equals(resume.getWorkDay())) {
			matching += 20;
		}else {
			str += "周工作天数，";
			mystr += "周工作天数："+resume.getWorkDay() +"，";
		}
		str = str.substring(0, str.length() - 1);
		mystr = mystr.substring(0, str.length() - 1);
		
		String s = "";
		if(matching == 100) {
			s = "匹配度为100%";
		}else {
			s = "匹配度为"+matching+"%，其中"+str+"与该职位要求不匹配，"+mystr;
		}
		return s;
	}

	@Override
	public void addFeedback(Long jobSeekerId, FeedBackDTO feedBackDTO) {
		
		Feedback feedback = new Feedback();
		feedback.setLastAccess(new Date().getTime());
		feedback.setType(AccountTypeEnum.job_seeker.getName());
		feedback.setRelationId(jobSeekerId);
		feedback.setContactWay(feedBackDTO.getContactWay());
		feedback.setFeedback(feedBackDTO.getFeedback());
		feedBackDAO.insertSelective(feedback);
	}

	
	
}












