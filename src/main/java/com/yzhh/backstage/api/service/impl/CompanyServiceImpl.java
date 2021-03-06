package com.yzhh.backstage.api.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yzhh.backstage.api.commons.BizException;
import com.yzhh.backstage.api.constans.Constants;
import com.yzhh.backstage.api.dao.IAccountDAO;
import com.yzhh.backstage.api.dao.ICompanyDAO;
import com.yzhh.backstage.api.dao.ICompanyJobSeekerDAO;
import com.yzhh.backstage.api.dao.ICompanyNoticeDAO;
import com.yzhh.backstage.api.dao.IDeliveryResumeDAO;
import com.yzhh.backstage.api.dao.IInterviewDAO;
import com.yzhh.backstage.api.dao.IJobSeekerDAO;
import com.yzhh.backstage.api.dao.IPositionDAO;
import com.yzhh.backstage.api.dao.IResumeDAO;
import com.yzhh.backstage.api.dto.AuditDTO;
import com.yzhh.backstage.api.dto.ForgetPasswordDTO;
import com.yzhh.backstage.api.dto.LoginDTO;
import com.yzhh.backstage.api.dto.PageDTO;
import com.yzhh.backstage.api.dto.UpdatePasswordDTO;
import com.yzhh.backstage.api.dto.UserDTO;
import com.yzhh.backstage.api.dto.company.AddCompanyDTO;
import com.yzhh.backstage.api.dto.company.ApplyCompany;
import com.yzhh.backstage.api.dto.company.CompanyDTO;
import com.yzhh.backstage.api.dto.company.CompanyNoticeDTO;
import com.yzhh.backstage.api.dto.company.CompanySearchDTO;
import com.yzhh.backstage.api.dto.company.DescriptionDTO;
import com.yzhh.backstage.api.dto.company.RegisterCompany;
import com.yzhh.backstage.api.dto.company.StatementDTO;
import com.yzhh.backstage.api.dto.company.StatisticsDTO;
import com.yzhh.backstage.api.dto.company.UpdateCompanyDTO;
import com.yzhh.backstage.api.entity.Account;
import com.yzhh.backstage.api.entity.Company;
import com.yzhh.backstage.api.entity.CompanyExample;
import com.yzhh.backstage.api.entity.CompanyJobSeeker;
import com.yzhh.backstage.api.entity.CompanyJobSeekerExample;
import com.yzhh.backstage.api.entity.CompanyNotice;
import com.yzhh.backstage.api.entity.CompanyNoticeExample;
import com.yzhh.backstage.api.entity.DeliveryResume;
import com.yzhh.backstage.api.entity.JobSeeker;
import com.yzhh.backstage.api.entity.Resume;
import com.yzhh.backstage.api.entity.ResumeExample;
import com.yzhh.backstage.api.enums.AccountSettingEnum;
import com.yzhh.backstage.api.enums.AccountTypeEnum;
import com.yzhh.backstage.api.enums.CompanyIdentificationEnum;
import com.yzhh.backstage.api.enums.CompanyStatusEnum;
import com.yzhh.backstage.api.enums.IsReadEnum;
import com.yzhh.backstage.api.enums.PositionStatusEnum;
import com.yzhh.backstage.api.enums.RoleEnum;
import com.yzhh.backstage.api.error.CommonError;
import com.yzhh.backstage.api.service.IAccountService;
import com.yzhh.backstage.api.service.ICompanyService;
import com.yzhh.backstage.api.util.CollectionUtils;
import com.yzhh.backstage.api.util.DateUtils;
import com.yzhh.backstage.api.util.MD5;
import com.yzhh.backstage.api.util.RedisUtil;
import com.yzhh.backstage.api.util.camera.SensitiveString;

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
	private IJobSeekerDAO jobSeekerDAO;
	@Autowired
	private ICompanyJobSeekerDAO companyJobSeekerDAO;
	@Autowired
	private IDeliveryResumeDAO deliveryResumeDAO;
	@Autowired
	private RedisUtil redisUtil;
	@Autowired
	private SensitiveString sensitiveString;
	
	@Autowired
	private IAccountService accountService;

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
				companyDTO.setStatus(company.getStatus() == null ? "" :CompanyStatusEnum.getValueById(company.getStatus()));
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
		companyDTO.setStatus(company.getStatus()+"");
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
		companyDTO.setOptionNote(company.getOptionNote());
		
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
	public void passCompany(AuditDTO auditDTO) {
		Company company = checkCompany(auditDTO.getId());
		Company newCompany = new Company();
		newCompany.setId(auditDTO.getId());
		newCompany.setLastAccess(new Date().getTime());
		if("通过".equals(auditDTO.getStatus())) {
			newCompany.setStatus(CompanyStatusEnum.audited.getId());
		}else{
			newCompany.setStatus(CompanyStatusEnum.reject.getId());
		}
		newCompany.setOptionNote(auditDTO.getNote());
		companyDAO.updateByPrimaryKeySelective(newCompany);
		company.setStatus(CompanyStatusEnum.audited.getId());
		this.updateLogin(company);
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
			.andPhoneEqualTo(loginDTO.getUsername());
		List<Company> list = companyDAO.selectByExample(example);
		
		if(CollectionUtils.isNotEmpty(list)) {
			if(list.get(0).getStatus() != null && list.get(0).getStatus() == CompanyStatusEnum.remove.getId()) {
				throw new BizException("企业已被删除，请联系管理员");
			}
			if(password.equals(list.get(0).getPassword())) {
				
				UserDTO companyDTO = this.updateLogin(list.get(0));
				
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
		
		Long simpleTreatmentPercentage = 0L;
		if(aleadyResumeCount != 0 && acceptResumeCount != 0) {
			simpleTreatmentPercentage = aleadyResumeCount/acceptResumeCount; //简历处理率
		}
		
		
		params.put("status", "未查看");
		Long notlookResumeCount = resumeDAO.countResume(params);  ;            //为未查看简历
		
		params.put("status", PositionStatusEnum.audited.getId());
		params.put("deadLineStar", dataTime);
		Long underwayPositionCount = positionDAO.countByPage(params);         //在招职位
		
		params.put("deadLineEnd", dataTime);
		params.put("deadLineStar", null);
		Long alreadyExpiredPositionCount = positionDAO.countByPage(params);   //已经过期职位
		
		params.put("deadLineStar", (dataTime - DateUtils.one_hour * 2));
		Long overduePositionCount = positionDAO.countByPage(params);          //即将过期
		
		params.put("deadLineStar", null);
		params.put("status", null);
		params.put("interviewTimeEnd", dataTime);
		Long interviewCount = interviewDAO.countInterviewCount(params);                        //面试数量
		
		params.put("interviewTimeEnd", null);
		params.put("interviewTimeStar", dataTime);
		Long willInterviewCount = interviewDAO.countInterviewCount(params);  
		
		System.out.println(DateUtils.longToString(dataTime, DateUtils.YYYYMMdd)+" 23:59:59");
		Long endTime = DateUtils.stringToLong(DateUtils.longToString(dataTime, DateUtils.YYYYMMdd)+" 23:59:59", DateUtils.yymmddhhmmss);
		params.put("interviewTimeEnd", endTime);
		Long todayInterviewCount = interviewDAO.countInterviewCount(params);  
		
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
		statisticsDTO.setWillInterviewCount(willInterviewCount);
		statisticsDTO.setTodayInterviewCount(todayInterviewCount);
		
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

	@Override
	public void registerCompany(RegisterCompany registerCompany) {

		CompanyExample example = new CompanyExample();
		example.createCriteria().andPhoneEqualTo(registerCompany.getPhone()).andStatusNotEqualTo(CompanyStatusEnum.remove.getId());
		List<Company> list = companyDAO.selectByExample(example);
		if(CollectionUtils.isNotEmpty(list)) {
			throw new BizException("手机号已被注册");
		}
		
		Long lastAccess = new Date().getTime();
		Company company = new Company();
		company.setPhone(registerCompany.getPhone());
		company.setPassword(MD5.getMD5(registerCompany.getPassword()));
		company.setLastAccess(lastAccess);
		company.setJoinDate(lastAccess);
		company.setIdentification(CompanyIdentificationEnum.Uncertified.getId());
		
		companyDAO.insertSelective(company);
		
		Account account = new Account();
		account.setLastAccess(lastAccess);
		account.setType(AccountTypeEnum.company.getId());
		account.setRelationId(company.getId());
		account.setBalance(0d);
		account.setCapital(0d);
		account.setLargess(0d);
		accountDAO.insertSelective(account);

	}

	@Override
	public void applyCompany(Long companyId, ApplyCompany applyCompany) {
		
		Company oldCompany = checkCompany(companyId);
		if(oldCompany.getStatus() != null && oldCompany.getStatus().intValue() != CompanyStatusEnum.reject.getId()) {
			throw new BizException("公司名称正在审核中");
		}
		Company company = new Company();
		
		CompanyExample example = new CompanyExample();
		example.createCriteria().andPhoneEqualTo(applyCompany.getName()).andStatusNotEqualTo(CompanyStatusEnum.remove.getId());
		List<Company> list = companyDAO.selectByExample(example);
		if(CollectionUtils.isNotEmpty(list) && list.get(0).getId().longValue() != companyId) {
			throw new BizException("公司名称已被注册");
		}
		example.createCriteria().andPhoneEqualTo(applyCompany.getEmail()).andStatusNotEqualTo(CompanyStatusEnum.remove.getId());
		list = companyDAO.selectByExample(example);
		if(CollectionUtils.isNotEmpty(list) && list.get(0).getId().longValue() != companyId) {
			throw new BizException("公司邮箱已被注册");
		}
		
		if(sensitiveString.comfirmSensitiveString(applyCompany.getName())) {
			throw new BizException("公司名称含有敏感词语");
		}
		if(sensitiveString.comfirmSensitiveString(applyCompany.getCompanyType())) {
			throw new BizException("公司类型含有敏感词语");
		}
		if(sensitiveString.comfirmSensitiveString(applyCompany.getRegistrationNumber())) {
			throw new BizException("公司注册号含有敏感词语");
		}
		if(sensitiveString.comfirmSensitiveString(applyCompany.getRegisteredCapital())) {
			throw new BizException("公司注册资本含有敏感词语");
		}
		
		company.setId(companyId);
		company.setLastAccess(new Date().getTime());
		company.setName(applyCompany.getName());
		company.setEmail(applyCompany.getEmail());
		company.setCompanyType(applyCompany.getCompanyType());
		company.setRegistrationNumber(applyCompany.getRegistrationNumber());
		company.setEstablishTime(applyCompany.getEstablishTime() == null ? "" : applyCompany.getEstablishTime().substring(0,10));
		company.setRegisteredCapital(applyCompany.getRegisteredCapital());
		company.setAttachment(applyCompany.getAttachment());
		company.setStatus(CompanyStatusEnum.pending.getId());
		
		companyDAO.updateByPrimaryKeySelective(company);
		
		//刷新缓存
		company = companyDAO.selectByPrimaryKey(companyId);
		updateLogin(company);
	}
	
	private UserDTO updateLogin(Company company) {
		UserDTO companyDTO = new UserDTO();
		companyDTO.setId(company.getId());
		companyDTO.setName(company.getName());
		companyDTO.setRole(RoleEnum.company.getId());
		companyDTO.setEmail(company.getEmail());
		companyDTO.setPhone(company.getPhone());
		
		companyDTO.setStatus(company.getStatus()+"");
		companyDTO.setNickName(company.getNickName());
		
		redisUtil.set(Constants.COMPANY_LOGIN +companyDTO.getId(), companyDTO,Constants.TWO_HOUR);
		
		return companyDTO;
	}

	@Override
	public void statmentCompany(Long companyId, StatementDTO statementDTO) {
		this.checkCompany(companyId);
		
		CompanyExample example = new CompanyExample();
		example.createCriteria().andPhoneEqualTo(statementDTO.getEmail());
		List<Company> list = companyDAO.selectByExample(example);
		if(CollectionUtils.isNotEmpty(list) && list.get(0).getId().longValue() != companyId) {
			throw new BizException("公司邮箱已被注册");
		}
		
		if(sensitiveString.comfirmSensitiveString(statementDTO.getOptionNote())) {
			throw new BizException("申述理由含有敏感词语");
		}
		
		Company company = new Company();
		company.setId(companyId);
		company.setLastAccess(new Date().getTime());
		company.setEmail(statementDTO.getEmail());
		company.setOptionNote(statementDTO.getOptionNote());
		company.setAttachment(statementDTO.getAttachment());
		company.setStatus(CompanyStatusEnum.pending.getId());
		companyDAO.updateByPrimaryKeySelective(company);
		
		company = companyDAO.selectByPrimaryKey(companyId);
		this.updateLogin(company);
	}

	@Override
	public void update(Long companyId, UpdateCompanyDTO updateCompanyDTO) {

		this.checkCompany(companyId);
		
		if(sensitiveString.comfirmSensitiveString(updateCompanyDTO.getNickName())) {
			throw new BizException("公司昵称含有敏感词语");
		}
		if(sensitiveString.comfirmSensitiveString(updateCompanyDTO.getNote())) {
			throw new BizException("公司备注含有敏感词语");
		}
		if(sensitiveString.comfirmSensitiveString(updateCompanyDTO.getWebsite())) {
			throw new BizException("公司网站含有敏感词语");
		}
		if(sensitiveString.comfirmSensitiveString(updateCompanyDTO.getAddress())) {
			throw new BizException("公司地址含有敏感词语");
		}
		
		Company company = new Company();
		company.setId(companyId);
		company.setLastAccess(new Date().getTime());
		company.setNickName(updateCompanyDTO.getNickName());
		company.setNote(updateCompanyDTO.getNote());
		company.setWebsite(updateCompanyDTO.getWebsite());
		company.setField(updateCompanyDTO.getField());
		company.setScale(updateCompanyDTO.getScale());
		company.setCity(updateCompanyDTO.getCity());
		company.setProvince(updateCompanyDTO.getProvince());
		company.setArea(updateCompanyDTO.getArea());
		company.setDescription(updateCompanyDTO.getDescription());
		company.setLogo(updateCompanyDTO.getLogo());
		company.setAddress(updateCompanyDTO.getAddress());
		
		companyDAO.updateByPrimaryKeySelective(company);
		
		company = companyDAO.selectByPrimaryKey(companyId);
		this.updateLogin(company);
	}

	@Override
	public Map<String,Object> resumeShowPay(Long companyId, List<Long> resumeIds) {
		
		Map<String,Object> map = new HashMap<>();
		
		if(CollectionUtils.isEmpty(resumeIds)) {
			throw new BizException("请选择文件");
		}
		
		ResumeExample resumeExample = new ResumeExample();
		
		resumeExample.createCriteria().andIdIn(resumeIds);
		
		List<Resume> resumeList = resumeDAO.selectByExample(resumeExample);
		
		if(CollectionUtils.isEmpty(resumeList)) {
			throw new BizException("未找到简历");
		}
		
		Map<Long,Long> resumeIdJobSeekerIdMap = new HashMap<>();
		Map<Long,Resume> resumeMap = new HashMap<>();
		Set<Long> jobSeekerIdSet = new HashSet<>();
		
		for(Resume resume : resumeList) {
			resumeIdJobSeekerIdMap.put(resume.getId(), resume.getJobSeekerId());
			jobSeekerIdSet.add(resume.getJobSeekerId());
			resumeMap.put(resume.getId(), resume);
		}
		
		CompanyJobSeekerExample companyJobSeekerExample = new CompanyJobSeekerExample();
		
		companyJobSeekerExample.createCriteria().andCompanyIdEqualTo(companyId).andJobSeekerIdIn(new ArrayList<>(jobSeekerIdSet));
		
		//剔除所有有关系的resume
		List<CompanyJobSeeker> companyJobSeekerList = companyJobSeekerDAO.selectByExample(companyJobSeekerExample);
		if(CollectionUtils.isNotEmpty(companyJobSeekerList)) {
			for(CompanyJobSeeker companyJobSeeker : companyJobSeekerList) {
				Map<Long,Long> newResumeIdJobSeekerIdMap = new HashMap<>();
				for(Long resumeId : resumeIdJobSeekerIdMap.keySet()) {
					if(resumeIdJobSeekerIdMap.get(resumeId).longValue() != companyJobSeeker.getJobSeekerId().longValue()) {
						newResumeIdJobSeekerIdMap.put(resumeId, resumeIdJobSeekerIdMap.get(resumeId));
					}
				}
				resumeIdJobSeekerIdMap = newResumeIdJobSeekerIdMap;
			}
		}
		
		resumeIds = new ArrayList<>(resumeIdJobSeekerIdMap.keySet());
		
		List<DeliveryResume> deliveryResumeList = deliveryResumeDAO.getResumeDeliveryCompany(companyId, resumeIds);
		if(CollectionUtils.isNotEmpty(deliveryResumeList)) {
			for(DeliveryResume deliveryResume : deliveryResumeList) {
				if(resumeIds.contains(deliveryResume.getResumeId())) {
					resumeIds.remove(deliveryResume.getResumeId());
				}
			}
		}
		
		//硕士及以上
		Double amt1 = accountService.getAmountSettingByType(AccountSettingEnum.education_master.getName());
		//本科
		Double amt2 = accountService.getAmountSettingByType(AccountSettingEnum.education_undergraduate.getName());
		//大专
		Double amt3 = accountService.getAmountSettingByType(AccountSettingEnum.education_specialty.getName());
		//高中
		Double amt4 = accountService.getAmountSettingByType(AccountSettingEnum.education_high_school.getName());
		
		Map<Long,JobSeeker> jobSeekerMap = new HashMap<>();
		
		Double amount = 0D;
		
		for(Long resumeId : resumeIds) {
			Resume resume = resumeMap.get(resumeId);
			JobSeeker jobSeeker = jobSeekerMap.get(resume.getJobSeekerId());
			
			if(jobSeeker == null) {
				
				jobSeeker = jobSeekerDAO.selectByPrimaryKey(resume.getJobSeekerId());
				jobSeekerMap.put(jobSeeker.getId(), jobSeeker);
				
				if(AccountSettingEnum.education_master.getName().equals(jobSeeker.getEducation())) {
					amount += amt1;
				}else if(AccountSettingEnum.education_undergraduate.getName().equals(jobSeeker.getEducation())) {
					amount += amt2;
				}else if(AccountSettingEnum.education_specialty.getName().equals(jobSeeker.getEducation())) {
					amount += amt3;
				}else if(AccountSettingEnum.education_high_school.getName().equals(jobSeeker.getEducation())) {
					amount += amt4;
				}
				
			}
		}
		
		map.put("amount", amount);
		map.put("jobSeekerIds", jobSeekerMap.keySet());
		
		return map;
	}

	@Override
	public void payForResume(Long companyId, List<Long> resumeIds) {
	
		Map<String,Object> map = this.resumeShowPay(companyId, resumeIds);
		
		Double amount = (Double)map.get("amount");
		
		@SuppressWarnings("unchecked")
		Set<Long> jobSeekerIds = (Set<Long>)map.get("jobSeekerIds");
		
		Account account = accountDAO.getAccountByRelationId(companyId, AccountTypeEnum.company.getId());
		
		if(account == null) {
			throw new BizException("未找到当前公司账户，请联系管理员");
		}
		
		if(account.getBalance().longValue() < amount.longValue()) {
			throw new BizException("用户金额不足以支付，请充值");
		}
		
		if(CollectionUtils.isNotEmpty(jobSeekerIds)) {
			CompanyJobSeeker companyJobSeeker = new CompanyJobSeeker();
			Long lastAccess = new Date().getTime();
			for(Long jobSeekerId : jobSeekerIds) {
				companyJobSeeker.setId(null);
				companyJobSeeker.setLastAccess(lastAccess);
				companyJobSeeker.setCompanyId(companyId);
				companyJobSeeker.setJobSeekerId(jobSeekerId);
				companyJobSeekerDAO.insertSelective(companyJobSeeker);
			}
		}
		
		//扣费
		accountDAO.consumptionWater(account, amount, "下载简历花费");
	}

}


























