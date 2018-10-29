package com.yzhh.backstage.api.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yzhh.backstage.api.commons.BizException;
import com.yzhh.backstage.api.dao.ICollectionPositionDAO;
import com.yzhh.backstage.api.dao.ICompanyDAO;
import com.yzhh.backstage.api.dao.ICompanyNoticeDAO;
import com.yzhh.backstage.api.dao.IDeliveryResumeDAO;
import com.yzhh.backstage.api.dao.IPositionDAO;
import com.yzhh.backstage.api.dao.IResumeDAO;
import com.yzhh.backstage.api.dto.AuditDTO;
import com.yzhh.backstage.api.dto.PageDTO;
import com.yzhh.backstage.api.dto.position.PositionCityDTO;
import com.yzhh.backstage.api.dto.position.PositionDTO;
import com.yzhh.backstage.api.dto.position.SearchPositionDTO;
import com.yzhh.backstage.api.entity.CollectionPosition;
import com.yzhh.backstage.api.entity.CollectionPositionExample;
import com.yzhh.backstage.api.entity.Company;
import com.yzhh.backstage.api.entity.CompanyNotice;
import com.yzhh.backstage.api.entity.Position;
import com.yzhh.backstage.api.entity.PositionExample;
import com.yzhh.backstage.api.enums.IsReadEnum;
import com.yzhh.backstage.api.enums.PositionStatusEnum;
import com.yzhh.backstage.api.service.IJobSeekerService;
import com.yzhh.backstage.api.service.IPositionService;
import com.yzhh.backstage.api.util.ChineseToEnglish;
import com.yzhh.backstage.api.util.CollectionUtils;
import com.yzhh.backstage.api.util.DateUtils;
import com.yzhh.backstage.api.util.camera.SensitiveString;

@Service
public class PositionServiceImpl implements IPositionService {

	@Autowired
	private IPositionDAO positionDAO;
	@Autowired
	private IDeliveryResumeDAO deliveryResumeDAO;
	@Autowired
	private ICompanyNoticeDAO companyNoticeDAO;
	@Autowired
	private ICompanyDAO companyDAO;
	@Autowired
	private ICollectionPositionDAO collectionPositionDAO;
	@Autowired
	private IResumeDAO resumeDAO;

	@Autowired
	private IJobSeekerService jobSeekerService;
	
	@Autowired
	private SensitiveString sensitiveString;

	@Override
	public void saveOrUpdatePosition(PositionDTO positionDTO) {

		Position position = new Position();
		Long date = new Date().getTime();
		
		if(sensitiveString.comfirmSensitiveString(positionDTO.getSeduction())) {
			throw new BizException("职位诱惑含有敏感词语");
		}
		if(sensitiveString.comfirmSensitiveString(positionDTO.getAddress())) {
			throw new BizException("职位工作地址含有敏感词语");
		}
		if(sensitiveString.comfirmSensitiveString(positionDTO.getRequired())) {
			throw new BizException("职位要求地址含有敏感词语");
		}

		if (positionDTO.getId() == null) {
			// 新增
			position.setLastAccess(date);
			position.setCompanyId(positionDTO.getCompanyId());
			position.setReleaseDate(date);
			position.setType2(positionDTO.getType2());
			position.setType(positionDTO.getType());
			position.setName(positionDTO.getName());
			position.setCity(positionDTO.getCity());
			position.setProvince(positionDTO.getProvince());
			position.setArea(positionDTO.getArea());
			position.setPerpleNum(positionDTO.getPerpleNum());
			position.setSeduction(positionDTO.getSeduction());
			position.setDescription(positionDTO.getDescription());
			position.setEducation(positionDTO.getEducation());
			position.setAddress(positionDTO.getAddress());
			position.setWorkType(positionDTO.getWorkType());
			position.setInternshipTime(positionDTO.getInternshipTime());
			position.setRequired(position.getRequired());
			position.setPerDiem(positionDTO.getPerDiem());
			position.setWorkTime(positionDTO.getWorkTime());
			position.setCorrectionChance(positionDTO.getCorrectionChance());
			position.setDeadline(DateUtils.stringToLong(positionDTO.getDeadline(), null));
			position.setIsPressing(positionDTO.getIsPressing());
			position.setRequired(positionDTO.getRequired());
			position.setWorkDate(positionDTO.getWorkDate());
			
			int status = PositionStatusEnum.pending.getId();
			if(positionDTO.getPerDiem().equals("50以下") || 
					positionDTO.getPerDiem().equals("50-100") ||
					positionDTO.getPerDiem().equals("100-150") ||
					positionDTO.getPerDiem().equals("150-200")) {
				status = PositionStatusEnum.audited.getId();
			}
			
			position.setStatus(status);

			positionDAO.insertSelective(position);
		} else {
			// 更新
			// 校验原职务是否存在
			Position oldPosition = checkPosition(positionDTO.getId());

			if (oldPosition.getStatus().intValue() != PositionStatusEnum.pending.getId()
					&& oldPosition.getStatus().intValue() != PositionStatusEnum.remove.getId()
					&& oldPosition.getStatus().intValue() != PositionStatusEnum.reject.getId()) {
				throw new BizException("职位已通过审核，无法修改");
			}

			position.setId(positionDTO.getId());
			position.setLastAccess(date);
			position.setCompanyId(positionDTO.getCompanyId());
			position.setType(positionDTO.getType());
			position.setName(positionDTO.getName());
			position.setCity(positionDTO.getCity());
			position.setPerpleNum(positionDTO.getPerpleNum());
			position.setSeduction(positionDTO.getSeduction());
			position.setDescription(positionDTO.getDescription());
			position.setEducation(positionDTO.getEducation());
			position.setAddress(positionDTO.getAddress());
			position.setWorkType(positionDTO.getWorkType());
			position.setInternshipTime(positionDTO.getInternshipTime());
			position.setPerDiem(positionDTO.getPerDiem());
			position.setWorkTime(positionDTO.getWorkTime());
			position.setCorrectionChance(positionDTO.getCorrectionChance());
			position.setDeadline(DateUtils.stringToLong(positionDTO.getDeadline(), null));
			position.setRequired(position.getRequired());
			position.setIsPressing(positionDTO.getIsPressing());
			position.setStatus(PositionStatusEnum.pending.getId());

			positionDAO.updateByPrimaryKeySelective(position);
		}
	}

	@Override
	public PageDTO<PositionDTO> list(SearchPositionDTO searchPositionDTO, Long page, Integer size, Long jobSeekerId) {

		if (page == null) {
			page = 1L;
		}

		if (size == null) {
			size = 10;
		}

		Map<String, Object> params = new HashMap<>();

		params.put("starNum", (page - 1) * size);
		params.put("size", size);

		if (searchPositionDTO == null) {
			searchPositionDTO = new SearchPositionDTO();
		}

		if (searchPositionDTO.getCompanyId() != null) {
			params.put("companyId", searchPositionDTO.getCompanyId());
		}

		Date date = new Date();

		if (searchPositionDTO.getStatus() != null) {
			if (searchPositionDTO.getStatus().equals("招聘中")) {
				// 招聘中
				params.put("status", PositionStatusEnum.audited.getId());
				params.put("deadLineStar", DateUtils.stringToLong(DateUtils.dateToString(date, null), null));
			}
			if (searchPositionDTO.getStatus().equals("已下线")) {
				// 已下线
				params.put("status", PositionStatusEnum.remove.getId());
			}
			if (searchPositionDTO.getStatus().equals("已过期")) {
				// 已过期
				params.put("status", PositionStatusEnum.audited.getId());
				params.put("deadLineEnd", DateUtils.stringToLong(DateUtils.dateToString(date, null), null));
			}
			if (searchPositionDTO.getStatus().equals("未审核")) {
				// 审核中
				params.put("status", PositionStatusEnum.pending.getId());
			}
		}

		if (!StringUtils.isEmpty(searchPositionDTO.getCity())) {
			params.put("city", searchPositionDTO.getCity());
		}

		if (!StringUtils.isEmpty(searchPositionDTO.getPerDiem())) {
			params.put("perDiems", searchPositionDTO.getPerDiem());
		}

		if (!StringUtils.isEmpty(searchPositionDTO.getWorkTime())) {
			params.put("workTimes", searchPositionDTO.getWorkTime());
		}

		if (!StringUtils.isEmpty(searchPositionDTO.getWorkDate())) {
			params.put("workDates", searchPositionDTO.getWorkDate());
		}
		if (!StringUtils.isEmpty(searchPositionDTO.getEducation())) {
			params.put("education", searchPositionDTO.getEducation());
		}
		if (!StringUtils.isEmpty(searchPositionDTO.getCorrectionChance())) {
			if("可转正".equals(searchPositionDTO.getCorrectionChance())) {
				searchPositionDTO.setCorrectionChance("提供转正");
			}
			params.put("correctionChance", searchPositionDTO.getCorrectionChance());
		}
		if (!StringUtils.isEmpty(searchPositionDTO.getType())) {
			params.put("type", searchPositionDTO.getType());
		}
		if (!StringUtils.isEmpty(searchPositionDTO.getName())) {
			params.put("name", searchPositionDTO.getName());
			if (!StringUtils.isEmpty(searchPositionDTO.getSearchKey())) {
				params.put("companyName", searchPositionDTO.getSearchKey());
			}
		} else {
			if (!StringUtils.isEmpty(searchPositionDTO.getSearchKey())) {
				params.put("searchKey", searchPositionDTO.getSearchKey());
			}
		}

		if (!StringUtils.isEmpty(searchPositionDTO.getWorkType())) {
			if (searchPositionDTO.getWorkType().equals("周边兼职")) {
				params.put("city", searchPositionDTO.getCity());
				params.put("workType", "周末兼职");
			}
			if (searchPositionDTO.getWorkType().equals("寒暑假工")) {
				params.put("workTypes", "寒暑假工");
			}
			if (searchPositionDTO.getWorkType().equals("应届实习")) {
				params.put("workType", "实习");
			}
			/*
			if (searchPositionDTO.getWorkType().equals("急招推荐")) {
				params.put("isPressing", 1);
			}*/
			if (searchPositionDTO.getWorkType().equals("高薪推荐")) {
				params.put("perDiems", "250以上");
			}
		}

		if (searchPositionDTO.getPositionIds() != null && searchPositionDTO.getPositionIds().size() != 0) {
			params.put("positionIds", searchPositionDTO.getPositionIds());
		}

		List<PositionDTO> list = new ArrayList<>();
		List<Position> positionList = positionDAO.queryByPage(params);
		Long count = positionDAO.countByPage(params);

		if (CollectionUtils.isNotEmpty(positionList)) {
			for (Position position : positionList) {

				Long num = deliveryResumeDAO.countDeliveryCount(position.getId());
				Company company = companyDAO.selectByPrimaryKey(position.getCompanyId());
				
				String status = PositionStatusEnum.getValueById(position.getStatus());
				if(position.getDeadline() < new Date().getTime()) {
					status = PositionStatusEnum.remove.getName();
				}

				PositionDTO positionDTO = new PositionDTO();
				positionDTO.setId(position.getId());
				positionDTO.setCompanyId(position.getCompanyId());
				positionDTO.setUpdateTime(DateUtils.longToString(position.getLastAccess(), null));
				positionDTO.setReleaseDate(DateUtils.longToString(position.getReleaseDate(), null));
				positionDTO.setDeliveryNum(num + "人投递简历");
				positionDTO.setType(position.getType());
				positionDTO.setType2(position.getType2());
				positionDTO.setName(position.getName());
				positionDTO.setCity(position.getCity());
				positionDTO.setProvince(position.getProvince());
				positionDTO.setArea(position.getArea());
				positionDTO.setPerpleNum(position.getPerpleNum());
				positionDTO.setSeduction(position.getSeduction());
				positionDTO.setDescription(position.getDescription());
				positionDTO.setEducation("".equals(position.getEducation()) ? "不限" :  position.getEducation());
				positionDTO.setAddress(position.getAddress());
				positionDTO.setWorkType(position.getWorkType());
				positionDTO.setInternshipTime(position.getInternshipTime());
				positionDTO.setPerDiem(position.getPerDiem());
				positionDTO.setWorkTime(position.getWorkTime());
				positionDTO.setCorrectionChance(position.getCorrectionChance());
				positionDTO.setDeadline(DateUtils.longToString(position.getDeadline(), null));
				positionDTO.setStatus(status);
				positionDTO.setCompanyLogo(company.getLogo());
				positionDTO.setCompanyName(company.getName());
				positionDTO.setRequired(position.getRequired());
				positionDTO.setCompanyNickName(company.getNickName());
				positionDTO.setCompanyField(company.getField());
				positionDTO.setCompanyScale(company.getScale());
				positionDTO.setIsPressing(position.getIsPressing());
				positionDTO.setWorkDate(position.getWorkDate());
				positionDTO.setIsDelivery(jobSeekerId == null ? false : this.isDelivery(jobSeekerId, position.getId()));
				positionDTO.setIsCollection(jobSeekerId == null ? false
						: jobSeekerService.isCollectionPosition(position.getId(), jobSeekerId));

				list.add(positionDTO);
			}
		}

		return new PageDTO<PositionDTO>(count, list, page, size);
	}

	@Override
	public void downLine(List<Long> ids) {

		Long lastAccess = new Date().getTime();
		Position newPosition = new Position();

		if (CollectionUtils.isNotEmpty(ids)) {

			for (Long id : ids) {
				checkPosition(id);
			}

			for (Long id : ids) {
				newPosition.setId(id);
				newPosition.setLastAccess(lastAccess);
				newPosition.setStatus(PositionStatusEnum.remove.getId());

				positionDAO.updateByPrimaryKeySelective(newPosition);
			}
		}
	}

	private Position checkPosition(Long id) {
		Position position = positionDAO.selectByPrimaryKey(id);
		if (position == null) {
			throw new BizException("职务未找到，请刷新页面");
		}
		return position;
	}

	@Override
	public PositionDTO findById(Long id, Long jobSeekerId) {

		Position position = checkPosition(id);

		PositionDTO positionDTO = new PositionDTO();

		Company company = companyDAO.selectByPrimaryKey(position.getCompanyId());
		
		String status = PositionStatusEnum.getValueById(position.getStatus());
		if(position.getDeadline() < new Date().getTime()) {
			status = PositionStatusEnum.remove.getName();
		}

		positionDTO.setId(position.getId());
		positionDTO.setCompanyId(position.getCompanyId());
		positionDTO.setReleaseDate(DateUtils.longToString(position.getReleaseDate(), null));
		positionDTO.setType(position.getType());
		positionDTO.setType2(position.getType2());
		positionDTO.setName(position.getName());
		positionDTO.setCity(position.getCity());
		positionDTO.setProvince(position.getProvince());
		positionDTO.setArea(position.getArea());
		positionDTO.setPerpleNum(position.getPerpleNum());
		positionDTO.setSeduction(position.getSeduction());
		positionDTO.setDescription(position.getDescription());
		positionDTO.setEducation("".equals(position.getEducation()) ? "不限" :  position.getEducation());
		positionDTO.setAddress(position.getAddress());
		positionDTO.setWorkType(position.getWorkType());
		positionDTO.setInternshipTime(position.getInternshipTime());
		positionDTO.setPerDiem(position.getPerDiem());
		positionDTO.setWorkDate(position.getWorkDate());
		positionDTO.setIsPressing(position.getIsPressing());
		positionDTO.setWorkTime(position.getWorkTime());
		positionDTO.setCorrectionChance(position.getCorrectionChance());
		positionDTO.setDeadline(DateUtils.longToString(position.getDeadline(), null));
		positionDTO.setStatus(status);
		positionDTO.setCompanyName(company.getName());
		positionDTO.setCompanyLogo(company.getLogo());
		positionDTO.setCompanyField(company.getField());
		positionDTO.setCompanyScale(company.getScale());
		positionDTO.setRequired(position.getRequired());
		positionDTO.setCompanyNickName(company.getNickName());
		if (jobSeekerId != null) {
			positionDTO.setIsCollection(jobSeekerService.isCollectionPosition(position.getId(), jobSeekerId));
			positionDTO.setIsDelivery(this.isDelivery(jobSeekerId, position.getId()));
		}

		return positionDTO;
	}

	@Override
	public void passPosition(AuditDTO auditDTO) {

		Long lastAccess = new Date().getTime();
		Position position = checkPosition(auditDTO.getId());
		Position newPosition = new Position();
		newPosition.setId(auditDTO.getId());
		newPosition.setLastAccess(lastAccess);
		// newPosition.setOption(auditDTO.getNote());
		newPosition.setOptionNote(auditDTO.getNote());
		if ("通过".equals(auditDTO.getStatus())) {
			newPosition.setStatus(PositionStatusEnum.audited.getId());
		} else {
			newPosition.setStatus(PositionStatusEnum.reject.getId());
		}

		positionDAO.updateByPrimaryKeySelective(newPosition);

		CompanyNotice companyNotice = new CompanyNotice();
		companyNotice.setCompanyId(position.getCompanyId());
		companyNotice.setIsRead(IsReadEnum.not_read.getId());
		companyNotice.setLastAccess(lastAccess);

		if ("通过".equals(auditDTO.getStatus())) {
			companyNotice.setMessage("审核消息:您发布的职位【" + position.getName() + "】审核通过啦!");
		} else {
			companyNotice.setMessage("审核消息:您发布的职位【" + position.getName() + "】审核被驳回了啦，驳回理由：" + auditDTO.getNote());
		}
		companyNoticeDAO.insertSelective(companyNotice);
	}

	@Override
	public String getName(List<Long> ids) {
		StringBuffer str = new StringBuffer();
		if (CollectionUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				Position position = checkPosition(id);
				str.append(position.getName()).append(",");
			}
		}
		return str.toString().substring(0, str.length() - 1);
	}

	@Override
	public void delete(List<Long> ids) {
		Position position = new Position();
		position.setLastAccess(new Date().getTime());
		if (CollectionUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				checkPosition(id);
			}
			for (Long id : ids) {
				position.setId(id);
				position.setStatus(PositionStatusEnum.delete.getId());
				positionDAO.updateByPrimaryKeySelective(position);
			}
		}
	}

	@Override
	public PositionCityDTO getPositionCity() {

		PositionCityDTO positionCityDTO = new PositionCityDTO();

		List<String> cityList = positionDAO.getCityForPosition();
		if (CollectionUtils.isNotEmpty(cityList)) {
			for (String city : cityList) {
				String cityEnglish = ChineseToEnglish.getPinYinHeadChar(city.substring(0, 1)).toUpperCase();
				;
				// positionCityDTO.setA(a);
				try {
					Method set = positionCityDTO.getClass().getMethod("set" + cityEnglish, List.class);
					Method get = positionCityDTO.getClass().getMethod("get" + cityEnglish);
					@SuppressWarnings("unchecked")
					List<String> list = (List<String>) get.invoke(positionCityDTO);
					list.add(city);
					set.invoke(positionCityDTO, list);
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
		}

		return positionCityDTO;
	}

	@Override
	public PageDTO<PositionDTO> collectionList(Long jobSeekerId, Long page, Integer size) {

		CollectionPositionExample example = new CollectionPositionExample();
		example.createCriteria().andJobSeekerIdEqualTo(jobSeekerId);

		List<CollectionPosition> list = collectionPositionDAO.selectByExample(example);

		if (CollectionUtils.isNotEmpty(list)) {
			Set<Long> positionIds = new HashSet<>();
			for (CollectionPosition collectionPosition : list) {
				positionIds.add(collectionPosition.getPositionId());
			}
			SearchPositionDTO searchPositionDTO = new SearchPositionDTO();
			searchPositionDTO.setStatus("招聘中");
			searchPositionDTO.setPositionIds(positionIds);
			return this.list(searchPositionDTO, page, size, jobSeekerId);
		}
		List<PositionDTO> l = new ArrayList<>();

		return new PageDTO<PositionDTO>(0L, l, 0L, 0);
	}

	@Override
	public Boolean isDelivery(Long jobSeekerId, Long positionId) {

		Map<String, Object> params = new HashMap<>();
		params.put("jobSeekerId", jobSeekerId);
		params.put("positionId", positionId);
		Long count = resumeDAO.isDelivery(params);

		return count != null && count > 0 ? true : false;
	}

	@Override
	public void testInsert() {
		// Position position = new Position();
		// for(int i=0;i<=20;i++) {
		// Long lastAccess = new Date().getTime();
		// position.setId(null);
		// position.setLastAccess(lastAccess);
		// position.setCompanyId(1L);
		// position.setReleaseDate(lastAccess);
		// position.setType("测试类");
		// position.setName("测试职位"+i);
		// position.setProvince("北京");
		// position.setPerpleNum(1);
		// position.setCity("北京");
		// position.setArea("海淀");
		// position.setSeduction("职业诱惑");
		// position.setDescription("描述");
		// position.setEducation("本科");
		// position.setAddress("江西省南昌市老福山");
		// position.setWorkType("实习");
		// position.setCorrectionChance("面议");
		// position.setStatus(2);
		// position.setDeadline(lastAccess+10000000);
		// position.setPerDiem("100-200");
		// position.setIsPressing(false);
		// position.setWorkDate("长期");
		// position.setInternshipTime("一个月");
		// position.setWorkTime("1");
		// position.setRequired("没要求");
		// positionDAO.insertSelective(position);
		// }
		PositionExample example = new PositionExample();
		example.createCriteria();
		List<Position> list = positionDAO.selectByExample(example);
		int a = 0;
		Position p = new Position();
		for (int i = 0; i < list.size(); i++) {
			Position position = list.get(i);
			p.setId(position.getId());
			a = i % 16;
			switch (a) {
			case 1:
				p.setPerDiem("100-150");
				break;
			case 2:
				p.setPerDiem("150-200");
				break;
			case 3:
				p.setPerDiem("200-250");
				break;
			case 4:
				p.setPerDiem("250-300");
				break;
			case 5:
				p.setPerDiem("500-600");
				break;
			case 6:
				p.setPerDiem("600-700");
				break;
			case 7:
				p.setPerDiem("700-800");
				break;
			case 8:
				p.setPerDiem("800-900");
				break;
			case 9:
				p.setPerDiem("900-1000");
				break;
			case 10:
				p.setPerDiem("1000以上");
				break;
			case 11:
				p.setPerDiem("300-350");
				break;
			case 12:
				p.setPerDiem("350-400");
				break;
			case 13:
				p.setPerDiem("400-450");
				break;
			case 14:
				p.setPerDiem("450-500");
				break;
			case 15:
				p.setPerDiem("50-100");
				break;
			case 0:
				p.setPerDiem("50以下");
				break;
			}
			positionDAO.updateByPrimaryKeySelective(p);
		}
	}

}
