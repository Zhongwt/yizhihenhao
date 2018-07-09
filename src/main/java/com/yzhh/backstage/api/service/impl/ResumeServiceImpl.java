package com.yzhh.backstage.api.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yzhh.backstage.api.commons.BizException;
import com.yzhh.backstage.api.dao.IDeliveryResumeDAO;
import com.yzhh.backstage.api.dao.IInterviewDAO;
import com.yzhh.backstage.api.dao.IResumeDAO;
import com.yzhh.backstage.api.dto.PageDTO;
import com.yzhh.backstage.api.dto.resume.AddInterviewDTO;
import com.yzhh.backstage.api.dto.resume.PageResumeDTO;
import com.yzhh.backstage.api.dto.resume.ResumeSearchDTO;
import com.yzhh.backstage.api.entity.DeliveryResume;
import com.yzhh.backstage.api.entity.Interview;
import com.yzhh.backstage.api.enums.DeliveryResumeStatusEnum;
import com.yzhh.backstage.api.service.IResumeService;
import com.yzhh.backstage.api.util.CollectionUtils;
import com.yzhh.backstage.api.util.DateUtils;

@Service
public class ResumeServiceImpl implements IResumeService {

	@Autowired
	private IResumeDAO resumeDAO;
	@Autowired
	private IDeliveryResumeDAO deliveryResumeDAO;
	@Autowired
	private IInterviewDAO interviewDAO;
	
	@Override
	public PageDTO<PageResumeDTO> queryPage(ResumeSearchDTO resumeSearchDTO,Long page,Integer size) {
		
		if(page == null) {
			page = 1L;
		}
		
		if(size == null) {
			size = 10;
		}
		
		Map<String,Object> params = new HashMap<>();
		
		if(resumeSearchDTO == null) {
			resumeSearchDTO = new ResumeSearchDTO();
		}
		if(!StringUtils.isEmpty(resumeSearchDTO.getType())) {
			params.put("type", resumeSearchDTO.getType());
		}
		if(!StringUtils.isEmpty(resumeSearchDTO.getSearchKey())) {
			params.put("searchKey", resumeSearchDTO.getSearchKey());
		}
		if(!StringUtils.isEmpty(resumeSearchDTO.getCity())) {
			params.put("city", resumeSearchDTO.getCity());
		}
		if(resumeSearchDTO.getWorkDay() != null) {
			params.put("workDay", resumeSearchDTO.getWorkDay());
		}
		if(!StringUtils.isEmpty(resumeSearchDTO.getArrayDay())) {
			params.put("arrayDay", resumeSearchDTO.getArrayDay());
		}
		if(!StringUtils.isEmpty(resumeSearchDTO.getEducation())) {
			params.put("education", resumeSearchDTO.getEducation());
		}
		if(!StringUtils.isEmpty(resumeSearchDTO.getGraduationTime())) {
			params.put("graduationTime", resumeSearchDTO.getGraduationTime());
		}
		if(!StringUtils.isEmpty(resumeSearchDTO.getSex())) {
			params.put("sex", resumeSearchDTO.getSex());
		}
		if(!StringUtils.isEmpty(resumeSearchDTO.getStatus())) {
			params.put("status", resumeSearchDTO.getStatus());
		}
		if(resumeSearchDTO.getCompanyId() != null) {
			params.put("companyId", resumeSearchDTO.getCompanyId());
		}
		if(resumeSearchDTO.getPositionId() != null) {
			params.put("positionId", resumeSearchDTO.getPositionId());
		}
		
		List<PageResumeDTO> list = resumeDAO.queryByPage(params);
		Long count = resumeDAO.countByPage(params);
		
		return new PageDTO<>(count, list, page, size);
	}
	
	private DeliveryResume checkDeliveryResume(Long id) {
		DeliveryResume deliveryResume  = deliveryResumeDAO.selectByPrimaryKey(id);
		if(deliveryResume == null) {
			throw new BizException("未找到简历，请刷新");
		}
		return deliveryResume;
	}

	@Override
	public void updateStatus(List<Long> ids, Integer status) {
		if(CollectionUtils.isNotEmpty(ids)) {
			for(Long id:ids) {
				checkDeliveryResume(id);
			}
			Long lastAccess = new Date().getTime();
			DeliveryResume deliveryResume = new DeliveryResume();
			for(Long id : ids) {
				deliveryResume.setId(id);
				deliveryResume.setLastAccess(lastAccess);
				deliveryResume.setStatus(status);
				deliveryResumeDAO.updateByPrimaryKeySelective(deliveryResume);
			}
		}
	}

	@Override
	public void interviewInvitation(AddInterviewDTO addInterviewDTO) {
		
		DeliveryResume deliveryResume  = checkDeliveryResume(addInterviewDTO.getDeliveryResumeId());
		Long date = new Date().getTime();
		
		Interview Interview = new Interview();
		Interview.setDeliveryResumeId(addInterviewDTO.getDeliveryResumeId());
		Interview.setLastAccess(date);
		Interview.setInterviewTime(DateUtils.stringToLong(addInterviewDTO.getInterviewTime(), DateUtils.yymmddhhmmss));
		Interview.setAddress(addInterviewDTO.getAddress());
		Interview.setContacts(addInterviewDTO.getContacts());
		Interview.setPhone(addInterviewDTO.getPhone());
		Interview.setNote(addInterviewDTO.getNote());
		interviewDAO.insertSelective(Interview);
		
		DeliveryResume newDeliveryResume = new DeliveryResume();
		newDeliveryResume.setId(deliveryResume.getId());
		newDeliveryResume.setLastAccess(date);
		newDeliveryResume.setStatus(DeliveryResumeStatusEnum.right.getId());
		deliveryResumeDAO.updateByPrimaryKeySelective(newDeliveryResume);
	}

	@Override
	public String getName(List<Long> ids) {
		Map<String,Object> params = new HashMap<>();
		params.put("ids", ids);
		List<String> names = resumeDAO.getName(params);
		StringBuffer str = new StringBuffer();
		if(CollectionUtils.isNotEmpty(names)) {
			for(String name : names) {
				str.append(name).append(",");
			}
		}
		return str.toString().substring(0,str.length() -1);
	}

}
