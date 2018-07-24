package com.yzhh.backstage.api.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yzhh.backstage.api.commons.BizException;
import com.yzhh.backstage.api.constans.Constants;
import com.yzhh.backstage.api.dao.IAccountDAO;
import com.yzhh.backstage.api.dao.ICompanyDAO;
import com.yzhh.backstage.api.dao.ICompanyNoticeDAO;
import com.yzhh.backstage.api.dao.IInterviewDAO;
import com.yzhh.backstage.api.dao.IPositionDAO;
import com.yzhh.backstage.api.dao.IResumeDAO;
import com.yzhh.backstage.api.dto.ForgetPasswordDTO;
import com.yzhh.backstage.api.dto.LoginDTO;
import com.yzhh.backstage.api.dto.PageDTO;
import com.yzhh.backstage.api.dto.UpdatePasswordDTO;
import com.yzhh.backstage.api.dto.UserDTO;
import com.yzhh.backstage.api.dto.company.AddCompanyDTO;
import com.yzhh.backstage.api.dto.company.CompanyDTO;
import com.yzhh.backstage.api.dto.company.CompanyNoticeDTO;
import com.yzhh.backstage.api.dto.company.CompanySearchDTO;
import com.yzhh.backstage.api.dto.company.DescriptionDTO;
import com.yzhh.backstage.api.dto.company.StatisticsDTO;
import com.yzhh.backstage.api.entity.Account;
import com.yzhh.backstage.api.entity.Company;
import com.yzhh.backstage.api.entity.CompanyExample;
import com.yzhh.backstage.api.entity.CompanyNotice;
import com.yzhh.backstage.api.entity.CompanyNoticeExample;
import com.yzhh.backstage.api.enums.AccountTypeEnum;
import com.yzhh.backstage.api.enums.CompanyStatusEnum;
import com.yzhh.backstage.api.enums.IsReadEnum;
import com.yzhh.backstage.api.enums.PositionStatusEnum;
import com.yzhh.backstage.api.enums.RoleEnum;
import com.yzhh.backstage.api.error.CommonError;
import com.yzhh.backstage.api.service.ICompanyService;
import com.yzhh.backstage.api.util.CollectionUtils;
import com.yzhh.backstage.api.util.DateUtils;
import com.yzhh.backstage.api.util.MD5;
import com.yzhh.backstage.api.util.RedisUtil;

@Service
public class CompanyServiceImpl implements ICompanyService {

	@Autowired
	private ICompanyDAO companyDAO;
	@Autowired
	private ICompanyNoticeDAO companyNoticeDAO;
	@Autowired
	private IResumeDAO resumeDAO;
	@Autowired
	private IInterviewDAO interviewDAO;
	@Autowired
	private IPositionDAO positionDAO;
	@Autowired
	private IAccountDAO accountDAO;
	@Autowired
	private RedisUtil redisUtil;

	@Override
	public PageDTO<CompanyDTO> queryByPage(CompanySearchDTO companySearchDTO, Long page, Integer size) {

		if(companySearchDTO == null) {
			companySearchDTO = new  CompanySearchDTO();
		}
		
		if (page == null) {
			page = 1L;
		}
		if (size == null) {
			size = 10;
		}
		List<CompanyDTO> list = new ArrayList<>();

		Map<String, Object> params = new HashedMap<>();
		if (!StringUtils.isEmpty(companySearchDTO.getSearchKey())) {
			params.put("searchKey", companySearchDTO.getSearchKey());
		}
		if (companySearchDTO.getStatus() != null) {
			params.put("status", companySearchDTO.getStatus());
		}
		params.put("starNum", (page - 1) * size);
		params.put("size", size);

		List<Company> companyList = companyDAO.queryByPage(params);
		Long count = companyDAO.countByPage(params);

		if (CollectionUtils.isNotEmpty(companyList)) {
			for (Company company : companyList) {
				CompanyDTO companyDTO = new CompanyDTO();
				companyDTO.setId(company.getId());
				companyDTO.setCity(company.getCity());
				companyDTO.setName(company.getName());
				companyDTO.setJoinDate(DateUtils.longToString(company.getJoinDate(), null));
				companyDTO.setStatus(CompanyStatusEnum.getValueById(company.getStatus()));
				companyDTO.setLogo(company.getLogo());
				list.add(companyDTO);
			}
		}

		PageDTO<CompanyDTO> p = new PageDTO<>();
		p.setCount(count);
		p.setList(list);
		p.setPage(page);
		p.setSize(size);

		return p;
	}

	@Override
	public void addCompany(AddCompanyDTO addCompanyDTO) {
		
		CompanyExample example = new CompanyExample();
		
		example.createCriteria().andNameEqualTo(addCompanyDTO.getName());
		List<Company> list = companyDAO.selectByExample(example);
		if(CollectionUtils.isNotEmpty(list)) {
			throw new BizException("公司名称已被注册");
		}
		
		example.createCriteria().andPhoneEqualTo(addCompanyDTO.getPhone());
		list = companyDAO.selectByExample(example);
		if(CollectionUtils.isNotEmpty(list)) {
			throw new BizException("手机号已被注册");
		}
		
		example = new CompanyExample();
		example.createCriteria().andEmailEqualTo(addCompanyDTO.getEmail());
		list = companyDAO.selectByExample(example);
		if(CollectionUtils.isNotEmpty(list)) {
			throw new BizException("Email已被注册");
		}
		
		Long date = new Date().getTime();
		
		Company company = new Company();
		company.setLastAccess(date);
		company.setPhone(addCompanyDTO.getPhone());
		company.setName(addCompanyDTO.getName());
		company.setEmail(addCompanyDTO.getEmail());
		company.setJoinDate(date);
		company.setStatus(CompanyStatusEnum.pending.getId());
		company.setAttachment(addCompanyDTO.getAttachment());
		company.setCity(addCompanyDTO.getCity());
		company.setAddress(addCompanyDTO.getAddress());
		company.setDescription(addCompanyDTO.getDescription());
		company.setField(addCompanyDTO.getField());
		company.setScale(addCompanyDTO.getScale());
		company.setWebsite(addCompanyDTO.getWebsite());
		company.setNote(addCompanyDTO.getNote());
		company.setPassword(MD5.getMD5(addCompanyDTO.getPassword()));
		company.setLogo(addCompanyDTO.getLogo());
		company.setCompanyType(addCompanyDTO.getCompanyType());
		company.setRegistrationNumber(addCompanyDTO.getRegistrationNumber());
		company.setEstablishTime(addCompanyDTO.getEstablishTime());
		company.setRegisteredCapital(addCompanyDTO.getRegisteredCapital());
		company.setProvince(addCompanyDTO.getProvince());
		company.setArea(addCompanyDTO.getArea());
		
		companyDAO.insertSelective(company);
		
		Account account = new Account();
		account.setLastAccess(date);
		account.setType(AccountTypeEnum.company.getId());
		account.setRelationId(company.getId());
		account.setBalance(0d);
		account.setCapital(0d);
		account.setLargess(0d);
		accountDAO.insertSelective(account);
	}

	@Override
	public CompanyDTO findById(Long id) {
		
		Company company = checkCompany(id);
		
		
		Account account = accountDAO.getAccountByRelationId(company.getId(), AccountTypeEnum.company.getId());
		Double balance = 0D;
		if(account != null) {
			balance = account.getBalance();
		}
		
		CompanyDTO companyDTO = new CompanyDTO();
		
		companyDTO.setId(company.getId());
		companyDTO.setName(company.getName());
		companyDTO.setCity(company.getCity());
		companyDTO.setJoinDate(DateUtils.longToString(company.getJoinDate(),null));
		companyDTO.setStatus(CompanyStatusEnum.getValueById(company.getStatus()));
		companyDTO.setAddress(company.getAddress());
		companyDTO.setField(company.getField());
		companyDTO.setScale(company.getScale());
		companyDTO.setWebsite(company.getWebsite());
		companyDTO.setEmail(company.getEmail());
		companyDTO.setNote(company.getNote());
		companyDTO.setDescription(company.getDescription());
		companyDTO.setPhone(company.getPhone());
		companyDTO.setAttachent(company.getAttachment());
		companyDTO.setLogo(company.getLogo());
		companyDTO.setCompanyType(company.getCompanyType());
		companyDTO.setRegistrationNumber(company.getRegistrationNumber());
		companyDTO.setEstablishTime(company.getEstablishTime());
		companyDTO.setRegisteredCapital(company.getRegisteredCapital());
		companyDTO.setLogo(company.getLogo());
		companyDTO.setCompanyType(company.getCompanyType());
		companyDTO.setRegistrationNumber(company.getRegistrationNumber());
		companyDTO.setEstablishTime(company.getEstablishTime());
		companyDTO.setRegisteredCapital(company.getRegisteredCapital());
		companyDTO.setProvince(company.getProvince());
		companyDTO.setArea(company.getArea());
		companyDTO.setBalance(balance);
		companyDTO.setNickName(company.getNickName());
		
		return companyDTO;
	}

	@Override
	public void update(CompanyDTO companyDTO) {
		
		Company company = checkCompany(companyDTO.getId());
		
		List<Company> list = null;
		CompanyExample example = null;
		if(!StringUtils.isEmpty(companyDTO.getName())) {
			example = new CompanyExample();
			example.createCriteria().andNameEqualTo(companyDTO.getName());
			list = companyDAO.selectByExample(example);
			if(CollectionUtils.isNotEmpty(list)) {
				if(list.get(0).getId().longValue() != companyDTO.getId().longValue()) {
					throw new BizException("公司名称已注册");
				}
			}
		}
		
		if(!StringUtils.isEmpty(companyDTO.getEmail())) {
			example = new CompanyExample();
			example.createCriteria().andEmailEqualTo(companyDTO.getEmail());
			list = companyDAO.selectByExample(example);
			if(CollectionUtils.isNotEmpty(list)) {
				if(list.get(0).getId().longValue() != companyDTO.getId().longValue()) {
					throw new BizException("Email已被注册");
				}
			}
		}
		
		Company newCompany = new Company();
		newCompany.setId(company.getId());
		newCompany.setLastAccess(new Date().getTime());
		newCompany.setName(companyDTO.getName());
		newCompany.setWebsite(companyDTO.getWebsite());
		newCompany.setCity(companyDTO.getCity());
		newCompany.setProvince(companyDTO.getProvince());
		newCompany.setArea(companyDTO.getArea());
		newCompany.setField(companyDTO.getField());
		newCompany.setScale(companyDTO.getScale());
		newCompany.setNote(companyDTO.getNote());
		newCompany.setDescription(companyDTO.getDescription());
		newCompany.setAddress(companyDTO.getAddress());
		newCompany.setEmail(companyDTO.getEmail());
		newCompany.setLogo(companyDTO.getLogo());
		//newCompany.setDescription(companyDTO.getDescription());
		
		companyDAO.updateByPrimaryKeySelective(newCompany);
	}
	
	private Company checkCompany(Long id) {
		Company company = companyDAO.selectByPrimaryKey(id);
		if(company == null) {
			throw new BizException("未找到企业，请刷新页面");
		}
		return company;
	}

	@Override
	public void passCompany(Long id) {
		checkCompany(id);
		Company newCompany = new Company();
		newCompany.setId(id);
		newCompany.setLastAccess(new Date().getTime());
		newCompany.setStatus(CompanyStatusEnum.audited.getId());
		companyDAO.updateByPrimaryKeySelective(newCompany);
	}

	@Override
	public void removeCompany(Long id) {
		checkCompany(id);
		Company newCompany = new Company();
		newCompany.setId(id);
		newCompany.setLastAccess(new Date().getTime());
		newCompany.setStatus(CompanyStatusEnum.remove.getId());
		companyDAO.updateByPrimaryKeySelective(newCompany);
	}

	@Override
	public UserDTO login(LoginDTO loginDTO) {
		
		String password = loginDTO.getPassword();
		password = MD5.getMD5(password);
		
		CompanyExample example = new CompanyExample();
		example.createCriteria()
			.andPhoneEqualTo(loginDTO.getUsername())
			.andStatusNotEqualTo(CompanyStatusEnum.remove.getId());
		List<Company> list = companyDAO.selectByExample(example);
		
		if(CollectionUtils.isNotEmpty(list)) {
			if(password.equals(list.get(0).getPassword())) {
				
				UserDTO companyDTO = new UserDTO();
				companyDTO.setId(list.get(0).getId());
				companyDTO.setName(list.get(0).getName());
				companyDTO.setRole(RoleEnum.company.getId());
				companyDTO.setEmail(list.get(0).getEmail());
				companyDTO.setPhone(list.get(0).getPhone());
				companyDTO.setStatus(CompanyStatusEnum.getValueById(list.get(0).getStatus()));
				
				redisUtil.set(Constants.COMPANY_LOGIN +companyDTO.getId(), companyDTO,Constants.TWO_HOUR);
				
				return companyDTO;
			}
		}
		throw new BizException(CommonError.USER_AUTH_ERROR);
	}

	@Override
	public StatisticsDTO getCompanyStatistics(Long companyId) {
		
		Long dataTime = new Date().getTime();
		
		Map<String,Object> params = new HashMap<>();
		params.put("companyId", companyId);
		
		Long acceptResumeCount = resumeDAO.countResume(params);
		
		Long releasePositionCount = positionDAO.countByPage(params);              //发布职位数量
		
		params.put("status", "已处理");
		Long aleadyResumeCount = resumeDAO.countResume(params);
		
		params.put("status", "待处理");
		Long pendingResumeCount = resumeDAO.countResume(params);            //多少待处理简历
		
		Long simpleTreatmentPercentage = aleadyResumeCount/acceptResumeCount; //简历处理率
		
		params.put("status", "未查看");
		Long notlookResumeCount = resumeDAO.countResume(params);  ;            //为未查看简历
		
		params.put("status", PositionStatusEnum.audited.getId());
		params.put("deadLineEnd", dataTime);
		Long underwayPositionCount = positionDAO.countByPage(params);         //在招职位
		
		params.put("deadLineEnd", null);
		params.put("deadLineStar", dataTime);
		Long alreadyExpiredPositionCount = positionDAO.countByPage(params);   //已经过期职位
		
		params.put("deadLineStar", (dataTime - DateUtils.one_hour * 2));
		Long overduePositionCount = positionDAO.countByPage(params);          //即将过期
		
		Long interviewCount = interviewDAO.countInterviewCount(companyId);                        //面试数量
		
		StatisticsDTO statisticsDTO = new StatisticsDTO();
		statisticsDTO.setPendingResumeCount(pendingResumeCount);
		statisticsDTO.setNotlookResumeCount(notlookResumeCount);
		statisticsDTO.setUnderwayPositionCount(underwayPositionCount);
		statisticsDTO.setOverduePositionCount(overduePositionCount);
		statisticsDTO.setAlreadyExpiredPositionCount(alreadyExpiredPositionCount);
		statisticsDTO.setSimpleTreatmentPercentage(simpleTreatmentPercentage);
		statisticsDTO.setAcceptResumeCount(acceptResumeCount);
		statisticsDTO.setReleasePositionCount(releasePositionCount);
		statisticsDTO.setInterviewCount(interviewCount);
		
		return statisticsDTO;
	}

	@Override
	public void updateDescription(Long companyId,DescriptionDTO descriptionDTO) {
		checkCompany(companyId);
		Company newCompany = new Company();
		newCompany.setId(companyId);
		newCompany.setLastAccess(new Date().getTime());
		newCompany.setDescription(descriptionDTO.getDescription());
		companyDAO.updateByPrimaryKeySelective(newCompany);
	}

	@Override
	public void updatePassword(Long companyId, UpdatePasswordDTO updatePasswordDTO) {
		Company company = checkCompany(companyId);
		
		if(!company.getPassword().equals(MD5.getMD5(updatePasswordDTO.getOldPassword()))) {
			throw new BizException("当前密码不正确");
		}
		Company newCompany = new Company();
		newCompany.setId(companyId);
		newCompany.setLastAccess(new Date().getTime());
		newCompany.setPassword(MD5.getMD5(updatePasswordDTO.getNewPassword()));
		companyDAO.updateByPrimaryKeySelective(newCompany);
	}

	@Override
	public PageDTO<CompanyNoticeDTO> getNoticeList(Long companyId, Long page, Integer size) {
		
		if (page == null) {
			page = 1L;
		}
		if (size == null) {
			size = 10;
		}
		List<CompanyNoticeDTO> list = new ArrayList<>();
		CompanyNotice notice = new CompanyNotice();

		Map<String, Object> params = new HashedMap<>();
		params.put("companyId", companyId);
		params.put("starNum", (page - 1) * size);
		params.put("size", size);
		
		List<CompanyNotice> companyNotices = companyNoticeDAO.queryByPage(params);
		Long count = companyNoticeDAO.countByPage(params);
		
		if(CollectionUtils.isNotEmpty(companyNotices)) {
			for(CompanyNotice companyNotice : companyNotices) {
				CompanyNoticeDTO dto = new CompanyNoticeDTO();
				dto.setId(companyNotice.getId());
				dto.setIsRead(IsReadEnum.getValueById(companyNotice.getIsRead()));
				dto.setMessage(companyNotice.getMessage());
				dto.setUpdateTime(DateUtils.longToString(companyNotice.getLastAccess(), null));
				list.add(dto);
				
				if(companyNotice.getIsRead().intValue() == IsReadEnum.not_read.getId()) {
					notice.setId(companyNotice.getId());
					notice.setIsRead(IsReadEnum.read.getId());
					companyNoticeDAO.updateByPrimaryKeySelective(notice);
				}
			}
		}
		
		return new PageDTO<>(count, list, page, size);
	}

	@Override
	public Long getCompanyNoticeCount(Long companyId) {
		
		CompanyNoticeExample example = new CompanyNoticeExample();
		example.createCriteria()
			.andCompanyIdEqualTo(companyId)
			.andIsReadEqualTo(IsReadEnum.not_read.getId());
		return companyNoticeDAO.countByExample(example);
	}

	@Override
	public void forgetPassword(ForgetPasswordDTO forgetPasswordDTO) {
		
		CompanyExample companyExample = new CompanyExample();
		companyExample.createCriteria().andPhoneEqualTo(forgetPasswordDTO.getPhone());
		List<Company> list = companyDAO.selectByExample(companyExample);
		if(CollectionUtils.isEmpty(list)) {
			throw new BizException("该手机号未注册公司");
		}
		
		String code = (String)redisUtil.get(Constants.phone_verification_code+forgetPasswordDTO.getPhone());
		if(StringUtils.isEmpty(code)) {
			throw new BizException("请先发送手机验证码");
		}
		
		if(!forgetPasswordDTO.getCode().equals(code)) {
			throw new BizException("手机验证码不正确");
		}
		Company company = new Company();
		company.setId(list.get(0).getId());
		company.setLastAccess(new Date().getTime());
		company.setPassword(MD5.getMD5(forgetPasswordDTO.getNewPassword()));
		companyDAO.updateByPrimaryKeySelective(company);
	}

}


























