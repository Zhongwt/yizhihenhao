package com.yzhh.backstage.api.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yzhh.backstage.api.commons.BizException;
import com.yzhh.backstage.api.dao.ICompanyDAO;
import com.yzhh.backstage.api.dao.ICompanyNoticeDAO;
import com.yzhh.backstage.api.dao.IDeliveryResumeDAO;
import com.yzhh.backstage.api.dao.IPositionDAO;
import com.yzhh.backstage.api.dto.AuditDTO;
import com.yzhh.backstage.api.dto.PageDTO;
import com.yzhh.backstage.api.dto.position.PositionDTO;
import com.yzhh.backstage.api.dto.position.SearchPositionDTO;
import com.yzhh.backstage.api.entity.Company;
import com.yzhh.backstage.api.entity.CompanyNotice;
import com.yzhh.backstage.api.entity.Position;
import com.yzhh.backstage.api.enums.IsReadEnum;
import com.yzhh.backstage.api.enums.PositionStatusEnum;
import com.yzhh.backstage.api.service.IJobSeekerService;
import com.yzhh.backstage.api.service.IPositionService;
import com.yzhh.backstage.api.util.CollectionUtils;
import com.yzhh.backstage.api.util.DateUtils;

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
	private IJobSeekerService jobSeekerService;

	@Override
	public void saveOrUpdatePosition(PositionDTO positionDTO) {

		Position position = new Position();
		Long date = new Date().getTime();

		if (positionDTO.getId() == null) {
			// 新增
			position.setLastAccess(date);
			position.setCompanyId(positionDTO.getCompanyId());
			position.setReleaseDate(date);
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
			position.setStatus(PositionStatusEnum.pending.getId());

			positionDAO.insertSelective(position);
		} else {
			// 更新
			// 校验原职务是否存在
			checkPosition(positionDTO.getId());

			position.setId(positionDTO.getId());
			position.setLastAccess(date);
			position.setCompanyId(positionDTO.getCompanyId());
			// position.setReleaseDate(date);
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
			// position.setStatus(PositionStatusEnum.pending.getId());

			positionDAO.updateByPrimaryKeySelective(position);
		}
	}

	@Override
	public PageDTO<PositionDTO> list(SearchPositionDTO searchPositionDTO, Long page, Integer size) {

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

		if (!StringUtils.isEmpty(searchPositionDTO.getSearchKey())) {
			params.put("searchKey", searchPositionDTO.getSearchKey());
		}
		if (searchPositionDTO.getCompanyId() != null) {
			params.put("companyId", searchPositionDTO.getCompanyId());
		}

		Date date = new Date();

		if (searchPositionDTO.getStatus() != null) {
			if (searchPositionDTO.getStatus().equals("招聘中")) {
				// 招聘中
				params.put("status", PositionStatusEnum.audited.getId());
				params.put("deadLineEnd", DateUtils.dateToString(date, null));
			}
			if (searchPositionDTO.getStatus().equals("已下线")) {
				// 已下线
				params.put("status", PositionStatusEnum.remove.getId());
			}
			if (searchPositionDTO.getStatus().equals("已过期")) {
				// 已过期
				params.put("status", PositionStatusEnum.audited.getId());
				params.put("deadLineStar", DateUtils.dateToString(date, null));
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
			params.put("perDiem", searchPositionDTO.getPerDiem());
		}

		if (!StringUtils.isEmpty(searchPositionDTO.getWorkTime())) {
			params.put("workTime", searchPositionDTO.getWorkTime());
		}

		if (!StringUtils.isEmpty(searchPositionDTO.getWorkDate())) {
			params.put("workDate", searchPositionDTO.getWorkDate());
		}
		if (!StringUtils.isEmpty(searchPositionDTO.getEducation())) {
			params.put("education", searchPositionDTO.getEducation());
		}
		if (!StringUtils.isEmpty(searchPositionDTO.getCorrectionChance())) {
			params.put("correctionChance", searchPositionDTO.getCorrectionChance());
		}
		if (!StringUtils.isEmpty(searchPositionDTO.getType())) {
			params.put("type", searchPositionDTO.getCity());
		}
		if (!StringUtils.isEmpty(searchPositionDTO.getName())) {
			params.put("name", searchPositionDTO.getName());
		}

		if (!StringUtils.isEmpty(searchPositionDTO.getCity())) {
			if (searchPositionDTO.getWorkType().equals("周边兼职")) {
				params.put("city", searchPositionDTO.getCity());
				params.put("workType", "兼职");
			}
			if (searchPositionDTO.getWorkType().equals("寒暑假工")) {
				params.put("workTypes", "寒暑假工");
			}
			if (searchPositionDTO.getWorkType().equals("应届实习")) {
				params.put("workType", "实习");
			}
			if (searchPositionDTO.getWorkType().equals("急招推荐")) {
				//params.put("workType", searchPositionDTO.getCity());
				params.put("isPressing", 1);
			}
		}

		List<PositionDTO> list = new ArrayList<>();
		List<Position> positionList = positionDAO.queryByPage(params);
		Long count = positionDAO.countByPage(params);

		if (CollectionUtils.isNotEmpty(positionList)) {
			for (Position position : positionList) {

				Long num = deliveryResumeDAO.countDeliveryCount(count);
				Company company = companyDAO.selectByPrimaryKey(position.getCompanyId());

				PositionDTO positionDTO = new PositionDTO();
				positionDTO.setId(position.getId());
				positionDTO.setCompanyId(positionDTO.getCompanyId());
				positionDTO.setUpdateTime(DateUtils.longToString(position.getLastAccess(), null));
				positionDTO.setReleaseDate(DateUtils.longToString(position.getReleaseDate(), null));
				positionDTO.setDeliveryNum(num + "人投递简历");
				positionDTO.setType(position.getType());
				positionDTO.setName(position.getName());
				positionDTO.setCity(position.getCity());
				positionDTO.setPerpleNum(position.getPerpleNum());
				positionDTO.setSeduction(position.getSeduction());
				positionDTO.setDescription(position.getDescription());
				positionDTO.setEducation(position.getEducation());
				positionDTO.setAddress(position.getAddress());
				positionDTO.setWorkType(position.getWorkType());
				positionDTO.setInternshipTime(position.getInternshipTime());
				positionDTO.setPerDiem(position.getPerDiem());
				positionDTO.setWorkTime(position.getWorkTime());
				positionDTO.setCorrectionChance(position.getCorrectionChance());
				positionDTO.setDeadline(DateUtils.longToString(position.getDeadline(), null));
				positionDTO.setStatus(PositionStatusEnum.getValueById(position.getStatus()));
				positionDTO.setCompanyLogo(company.getLogo());
				positionDTO.setCompanyName(company.getName());

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
	public PositionDTO findById(Long id,Long jobSeekerId) {

		Position position = checkPosition(id);

		PositionDTO positionDTO = new PositionDTO();
		
		Company company = companyDAO.selectByPrimaryKey(position.getCompanyId());
		
		// positionDTO.setLastAccess(DateUtils.longToString(position.getLastAccess(),
		// null));
		positionDTO.setId(position.getId());
		positionDTO.setCompanyId(positionDTO.getCompanyId());
		positionDTO.setReleaseDate(DateUtils.longToString(position.getReleaseDate(), null));
		positionDTO.setType(position.getType());
		positionDTO.setName(position.getName());
		positionDTO.setCity(position.getCity());
		positionDTO.setPerpleNum(position.getPerpleNum());
		positionDTO.setSeduction(position.getSeduction());
		positionDTO.setDescription(position.getDescription());
		positionDTO.setEducation(position.getEducation());
		positionDTO.setAddress(position.getAddress());
		positionDTO.setWorkType(position.getWorkType());
		positionDTO.setInternshipTime(position.getInternshipTime());
		positionDTO.setPerDiem(position.getPerDiem());
		positionDTO.setWorkTime(position.getWorkTime());
		positionDTO.setCorrectionChance(position.getCorrectionChance());
		positionDTO.setDeadline(DateUtils.longToString(position.getDeadline(), null));
		positionDTO.setStatus(PositionStatusEnum.getValueById(position.getStatus()));
		positionDTO.setCompanyName(company.getName());
		positionDTO.setCompanyLogo(company.getLogo());
		positionDTO.setCompanyField(company.getField());
		positionDTO.setCompanyScale(company.getScale());
		if(jobSeekerId != null) {
			positionDTO.setIsCollection(jobSeekerService.isCollectionPosition(position.getId(), jobSeekerId));
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
}
