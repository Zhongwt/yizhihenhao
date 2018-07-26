package com.yzhh.backstage.api.service;

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

public interface ICompanyService {

	// 查询企业列表
	public PageDTO<CompanyDTO> queryByPage(CompanySearchDTO companySearchDTO, Long page, Integer size);

	// 保存企业
	public void addCompany(AddCompanyDTO addCompanyDTO);

	// 查询企业
	public CompanyDTO findById(Long id);

	// 更新企业基础信息
	public void update(CompanyDTO companyDTO);

	// 更新企业描述
	public void updateDescription(Long companyId, DescriptionDTO descriptionDTO);

	// 更新企业密码
	public void updatePassword(Long companyId, UpdatePasswordDTO updatePasswordDTO);

	// 审核通过企业
	public void passCompany(Long id);

	// 移除企业
	public void removeCompany(Long id);

	// 企业登录
	public UserDTO login(LoginDTO loginDTO);

	// 获取公司统计
	public StatisticsDTO getCompanyStatistics(Long companyId);

	// 获取通知列表
	public PageDTO<CompanyNoticeDTO> getNoticeList(Long companyId, Long page, Integer size);

	// 获取公司为读取通知数量
	public Long getCompanyNoticeCount(Long companyId);

	// 忘记密码
	public void forgetPassword(ForgetPasswordDTO forgetPasswordDTO);

	// 注册公司
	public void registerCompany(RegisterCompany registerCompany);

	// 申请入住
	public void applyCompany(Long companyId, ApplyCompany applyCompany);

	// 公司申述
	public void statmentCompany(Long companyId, StatementDTO statementDTO);

	// 更新企业基础信息
	public void update(Long companyId, UpdateCompanyDTO updateCompanyDTO);

}
