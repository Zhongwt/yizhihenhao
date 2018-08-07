package com.yzhh.backstage.api.service;

import java.io.InputStream;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import com.yzhh.backstage.api.dto.PageDTO;
import com.yzhh.backstage.api.dto.resume.AddInterviewDTO;
import com.yzhh.backstage.api.dto.resume.EducationalBackgroundDTO;
import com.yzhh.backstage.api.dto.resume.InternshipExpectationDTO;
import com.yzhh.backstage.api.dto.resume.InternshipExperienceDTO;
import com.yzhh.backstage.api.dto.resume.PageResumeDTO;
import com.yzhh.backstage.api.dto.resume.ProjectExperienceDTO;
import com.yzhh.backstage.api.dto.resume.ResumeDTO;
import com.yzhh.backstage.api.dto.resume.ResumeLibDTO;
import com.yzhh.backstage.api.dto.resume.ResumePoorDTO;
import com.yzhh.backstage.api.dto.resume.ResumeSearchDTO;
import com.yzhh.backstage.api.dto.resume.SelfEvaluationDTO;
import com.yzhh.backstage.api.dto.resume.SkillHobbyDTO;
import com.yzhh.backstage.api.dto.resume.WorksShowDTO;
import com.yzhh.backstage.api.entity.Resume;

public interface IResumeService {

	// 分页查询求职情况
	public PageDTO<PageResumeDTO> queryPage(ResumeSearchDTO resumeSearchDTO, Long page, Integer size);

	// 批量修改简历状态
	public void updateStatus(List<Long> ids, Integer status);

	// 面试邀请
	public void interviewInvitation(AddInterviewDTO addInterviewDTO);

	// 获取名字
	public String getName(List<Long> ids);

	// 通过用户id获取用户职位列表
	public List<ResumePoorDTO> getJobSeekerResumePoorList(Long jobSeekerId);

	// 将简历设置成默认
	public void setResumeDefault(Long resumeId);

	// 校验简历是否存在
	public Resume checkResume(Long resumeId);

	// 删除简历
	public void deleteResume(Long resumeId);

	// 简历详情
	public ResumeDTO findById(Long resumeId);

	// 修改简历名称
	public void modifyResumeName(Long resumeId, String name);

	// 新增简历
	public void add(Long jobSeekerId);

	// 保存实习期望
	public void saveInternshipExpectation(Long resumeId, InternshipExpectationDTO internshipExpectationDTO);

	// 保存实习经历
	public void saveInternshipExperience(Long resumeId, InternshipExperienceDTO internshipExperienceDTO);

	// 保存教育背景
	public void saveEducationalBackground(Long resumeId, EducationalBackgroundDTO educationalBackgroundDTO);

	// 保存项目经历
	public void saveProjectExperience(Long resumeId, ProjectExperienceDTO projectExperienceDTO);

	// 保存技能爱好
	public void saveSkillHobby(Long resumeId, SkillHobbyDTO skillHobbyDTO);

	// 保存作品展示
	public void saveWorksShow(Long resumeId, WorksShowDTO worksShowDTO);

	// 保存自我评价
	public void saveSelfEvaluation(Long resumeId, SelfEvaluationDTO selfEvaluationDTO);

	// 接受面试
	public void acceptInterview(Long deliveryResumeId);

	// 删除实习
	public void deleteInternshipExperience(Long internshipExperienceId);

	// 删除教育背景
	public void deleteEducationalBackground(Long educationalBackgroundId);

	// 删除项目经历
	public void deleteProjectExperience(Long projectExperienceId);

	// 删除技能爱好
	public void deleteSkillHobby(Long skillHobbyId);

	// 删除作品展示
	public void deleteWorksShow(Long worksShowId);

	// 删除自我评价
	public void deleteSelfEvaluation(Long selfEvaluationId);

	// 公司获取投递过来的简历
	public ResumeDTO conmpanyGetDeliveryResume(Long deliveryResumeId);

	// 简历库列表
	public PageDTO<ResumeLibDTO> resumeLibList(ResumeSearchDTO resumeSearchDTO, Long page, Integer size);

	// 公司获取投递过来的简历
	public ResumeDTO conmpanyGetResume(Long companyId, Long resumeId);

	// 下载单个简历
	public XWPFDocument downloadResume(Long companyId, Long resumeId);

	// 下载单个简历
	public InputStream downloadResumes(Long companyId, List<Long> resumeId);
}
