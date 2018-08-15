package com.yzhh.backstage.api.service.impl;

import java.io.File;
import java.io.InputStream;
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
import com.yzhh.backstage.api.dao.ICompanyJobSeekerDAO;
import com.yzhh.backstage.api.dao.IDeliveryResumeDAO;
import com.yzhh.backstage.api.dao.IEducationalBackgroundDAO;
import com.yzhh.backstage.api.dao.IInternshipExperienceDAO;
import com.yzhh.backstage.api.dao.IInterviewDAO;
import com.yzhh.backstage.api.dao.IJobSeekerDAO;
import com.yzhh.backstage.api.dao.IPositionDAO;
import com.yzhh.backstage.api.dao.IProjectExperienceDAO;
import com.yzhh.backstage.api.dao.IResumeDAO;
import com.yzhh.backstage.api.dao.ISelfEvaluationDAO;
import com.yzhh.backstage.api.dao.ISkillHobbyDAO;
import com.yzhh.backstage.api.dao.IWorksShowDAO;
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
import com.yzhh.backstage.api.entity.Company;
import com.yzhh.backstage.api.entity.DeliveryResume;
import com.yzhh.backstage.api.entity.EducationalBackground;
import com.yzhh.backstage.api.entity.EducationalBackgroundExample;
import com.yzhh.backstage.api.entity.InternshipExperience;
import com.yzhh.backstage.api.entity.InternshipExperienceExample;
import com.yzhh.backstage.api.entity.Interview;
import com.yzhh.backstage.api.entity.JobSeeker;
import com.yzhh.backstage.api.entity.Position;
import com.yzhh.backstage.api.entity.ProjectExperience;
import com.yzhh.backstage.api.entity.ProjectExperienceExample;
import com.yzhh.backstage.api.entity.Resume;
import com.yzhh.backstage.api.entity.ResumeExample;
import com.yzhh.backstage.api.entity.SelfEvaluation;
import com.yzhh.backstage.api.entity.SelfEvaluationExample;
import com.yzhh.backstage.api.entity.SkillHobby;
import com.yzhh.backstage.api.entity.SkillHobbyExample;
import com.yzhh.backstage.api.entity.WorksShow;
import com.yzhh.backstage.api.entity.WorksShowExample;
import com.yzhh.backstage.api.enums.DeliveryResumeStatusEnum;
import com.yzhh.backstage.api.enums.IsDefaultEnum;
import com.yzhh.backstage.api.enums.IsDeleteEnum;
import com.yzhh.backstage.api.service.IAccountService;
import com.yzhh.backstage.api.service.IJobSeekerService;
import com.yzhh.backstage.api.service.IResumeService;
import com.yzhh.backstage.api.util.CollectionUtils;
import com.yzhh.backstage.api.util.DateUtils;
import com.yzhh.backstage.api.util.PackWord;
import com.yzhh.backstage.api.util.eamil.EmailUtil;
import com.yzhh.backstage.api.util.pdf.ExportPDF;

@Service
public class ResumeServiceImpl implements IResumeService {

	@Autowired
	private IResumeDAO resumeDAO;
	@Autowired
	private IDeliveryResumeDAO deliveryResumeDAO;
	@Autowired
	private IInterviewDAO interviewDAO;
	@Autowired
	private IEducationalBackgroundDAO educationalBackgroundDAO;
	@Autowired
	private IInternshipExperienceDAO internshipExperienceDAO;
	@Autowired
	private IProjectExperienceDAO projectExperienceDAO;
	@Autowired
	private ISkillHobbyDAO skillHobbyDAO;
	@Autowired
	private IWorksShowDAO worksShowDAO;
	@Autowired
	private ISelfEvaluationDAO selfEvaluationDAO;
	@Autowired
	private IJobSeekerDAO jobSeekerDAO;
	@Autowired
	private IPositionDAO positionDAO;
	@Autowired
	private ICompanyDAO companyDAO;
	@Autowired
	private ICompanyJobSeekerDAO companyJobSeekerDAO;

	@Autowired
	private IJobSeekerService jobSeekerService;
	@Autowired
	private IAccountService accountService;

	@Override
	public PageDTO<PageResumeDTO> queryPage(ResumeSearchDTO resumeSearchDTO, Long page, Integer size) {

		if (page == null) {
			page = 1L;
		}

		if (size == null) {
			size = 10;
		}

		Map<String, Object> params = new HashMap<>();

		params.put("starNum", (page - 1) * size);
		params.put("size", size);

		if (resumeSearchDTO == null) {
			resumeSearchDTO = new ResumeSearchDTO();
		}
		if (!StringUtils.isEmpty(resumeSearchDTO.getName())) {
			params.put("name", resumeSearchDTO.getName());
		}
		if (!StringUtils.isEmpty(resumeSearchDTO.getType())) {
			params.put("type", resumeSearchDTO.getType());
		}
		if (!StringUtils.isEmpty(resumeSearchDTO.getSearchKey())) {
			params.put("searchKey", resumeSearchDTO.getSearchKey());
		}
		if (!StringUtils.isEmpty(resumeSearchDTO.getCity())) {
			params.put("city", resumeSearchDTO.getCity());
		}
		if (!StringUtils.isEmpty(resumeSearchDTO.getInternshipTime())) {
			params.put("internshipTime", resumeSearchDTO.getInternshipTime());
		}
		if (resumeSearchDTO.getWorkDay() != null) {
			params.put("workDay", resumeSearchDTO.getWorkDay());
		}
		if (!StringUtils.isEmpty(resumeSearchDTO.getArrayDay())) {
			params.put("arrayDay", DateUtils.stringToLong(resumeSearchDTO.getArrayDay(), null));
		}
		if (!StringUtils.isEmpty(resumeSearchDTO.getEducation())) {
			params.put("education", resumeSearchDTO.getEducation());
		}
		if (!StringUtils.isEmpty(resumeSearchDTO.getGraduationTime())) {
			params.put("graduationTime", resumeSearchDTO.getGraduationTime());
		}
		if (!StringUtils.isEmpty(resumeSearchDTO.getGraduationSchool())) {
			params.put("graduationSchool", resumeSearchDTO.getGraduationSchool());
		}
		if (!StringUtils.isEmpty(resumeSearchDTO.getSex())) {
			params.put("sex", resumeSearchDTO.getSex());
		}
		if (!StringUtils.isEmpty(resumeSearchDTO.getStatus())) {
			params.put("status", resumeSearchDTO.getStatus());
		}
		if (resumeSearchDTO.getCompanyId() != null) {
			params.put("companyId", resumeSearchDTO.getCompanyId());
		}
		if (resumeSearchDTO.getPositionId() != null) {
			params.put("positionId", resumeSearchDTO.getPositionId());
		}
		if (resumeSearchDTO.getJobSeekerId() != null) {
			params.put("jobSeekerId", resumeSearchDTO.getJobSeekerId());
		}

		List<PageResumeDTO> list = resumeDAO.queryByPage(params);
		Long count = resumeDAO.countByPage(params);

		return new PageDTO<>(count, list, page, size);
	}

	private DeliveryResume checkDeliveryResume(Long id) {
		DeliveryResume deliveryResume = deliveryResumeDAO.selectByPrimaryKey(id);
		if (deliveryResume == null) {
			throw new BizException("未找到简历，请刷新");
		}
		return deliveryResume;
	}

	@Override
	public void updateStatus(List<Long> ids, Integer status) {
		if (CollectionUtils.isNotEmpty(ids)) {
			for (Long id : ids) {
				checkDeliveryResume(id);
			}
			Long lastAccess = new Date().getTime();
			DeliveryResume deliveryResume = new DeliveryResume();
			for (Long id : ids) {
				deliveryResume.setId(id);
				deliveryResume.setLastAccess(lastAccess);
				deliveryResume.setStatus(status);
				deliveryResumeDAO.updateByPrimaryKeySelective(deliveryResume);
			}
		}
	}

	@Override
	public void interviewInvitation(AddInterviewDTO addInterviewDTO) {

		DeliveryResume deliveryResume = checkDeliveryResume(addInterviewDTO.getDeliveryResumeId());
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

		Resume resume = resumeDAO.selectByPrimaryKey(deliveryResume.getResumeId());
		JobSeeker jobSeeker = jobSeekerDAO.selectByPrimaryKey(resume.getJobSeekerId());
		Position position = positionDAO.selectByPrimaryKey(deliveryResume.getPositionId());
		Company company = companyDAO.selectByPrimaryKey(position.getCompanyId());

		StringBuffer html = new StringBuffer();
		html.append("<div style=\"margin:20px 0;padding:20px 15px;\">");
		html.append(jobSeeker.getName() + "，您好~");
		html.append("<br> 你的简历已经通过筛选，很高兴邀请你参加面试。");
		html.append("<br>");
		html.append("<br> ");
		html.append("<strong>面试公司：" + company.getName() + "</strong>");
		html.append("<br> ");
		html.append("<strong>面试职位：" + position.getName() + "</strong>");
		html.append("<br> ");
		html.append(
				"<strong>面试时间：<span style=\"border-bottom: 1px dashed rgb(204, 204, 204); position: relative;\" t=\"5\" times=\" 09:30\">"
						+ addInterviewDTO.getInterviewTime().substring(0, 10) + "</span> "
						+ addInterviewDTO.getInterviewTime().substring(11, 16) + "</strong>");
		html.append("<br> ");
		html.append("<strong>面试地点：" + addInterviewDTO.getAddress() + "</strong>");
		html.append("<br> ");
		html.append("<strong>联系人：" + addInterviewDTO.getContacts() + "</strong>");
		html.append("<br> ");
		html.append("<strong>联系电话：" + addInterviewDTO.getPhone());
		html.append("<br>");
		html.append("<strong>备注：" + addInterviewDTO.getNote());
		html.append("<br>");
		html.append("<br>");
		html.append("<div style=\"MARGIN-BOTTOM: 10px; HEIGHT: 30px; TEXT-ALIGN: right; MARGIN-TOP: 10px\">");
		html.append("<span style=\"FONT-SIZE: 10pt; COLOR: #c0c0c0\"> </span>");
		html.append("</div> ");
		html.append("</div> ");

		EmailUtil.sendMail(jobSeeker.getEmail(), "面试邀请", html.toString());
	}

	@Override
	public String getName(List<Long> ids) {
		Map<String, Object> params = new HashMap<>();
		params.put("ids", ids);
		List<String> names = resumeDAO.getName(params);
		StringBuffer str = new StringBuffer();
		if (CollectionUtils.isNotEmpty(names)) {
			for (String name : names) {
				str.append(name).append(",");
			}
		}
		return str.toString().substring(0, str.length() - 1);
	}

	@Override
	public List<ResumePoorDTO> getJobSeekerResumePoorList(Long jobSeekerId) {

		JobSeeker jobSeeker = jobSeekerService.checkJobSeeker(jobSeekerId);

		List<ResumePoorDTO> list = new ArrayList<>();
		ResumeExample example = new ResumeExample();
		example.createCriteria().andJobSeekerIdEqualTo(jobSeekerId).andIsDeleteEqualTo(IsDeleteEnum.nomal.getId());
		List<Resume> resumes = resumeDAO.selectByExample(example);
		if (CollectionUtils.isNotEmpty(resumes)) {
			for (Resume resume : resumes) {
				ResumePoorDTO resumePoorDTO = new ResumePoorDTO();
				resumePoorDTO.setId(resume.getId());
				resumePoorDTO.setPicUrl(jobSeeker.getImageUrl());
				resumePoorDTO.setIsDefault(resume.getIsDefault());
				resumePoorDTO.setName(resume.getName());
				resumePoorDTO.setJobSeekerName(jobSeeker.getName());
				resumePoorDTO.setIntegrity(resume.getIntegrity() + "%");
				resumePoorDTO.setWishCity(resume.getWishCity());
				resumePoorDTO.setWorkDay(resume.getWorkDay());
				resumePoorDTO.setWishPositionName(resume.getWishPositionName());
				resumePoorDTO.setUpdateTime(DateUtils.longToString(resume.getLastAccess(), DateUtils.yymmddhhmmss));
				list.add(resumePoorDTO);
			}
		}

		return list;
	}

	@Override
	public void setResumeDefault(Long resumeId) {

		Resume resume = this.checkResume(resumeId);

		Resume defaultResume = resumeDAO.getJobSeekerDefaultResume(resume.getJobSeekerId());

		Resume newResume = new Resume();
		newResume.setLastAccess(new Date().getTime());

		// 先将默认置为不默认
		newResume.setId(defaultResume.getId());
		newResume.setIsDefault(IsDefaultEnum.nomal.getId());
		resumeDAO.updateByPrimaryKeySelective(newResume);

		// 将不默认置为默认
		newResume.setId(resume.getId());
		newResume.setIsDefault(IsDefaultEnum.is_default.getId());
		resumeDAO.updateByPrimaryKeySelective(newResume);
	}

	@Override
	public Resume checkResume(Long resumeId) {

		Resume resume = resumeDAO.selectByPrimaryKey(resumeId);
		if (resume == null) {
			throw new BizException("简历未找到");
		}

		return resume;
	}

	@Override
	public void deleteResume(Long resumeId) {
		this.checkResume(resumeId);
		Resume newResume = new Resume();
		newResume.setId(resumeId);
		newResume.setIsDelete(IsDeleteEnum.delete.getId());
		newResume.setLastAccess(new Date().getTime());
		resumeDAO.updateByPrimaryKeySelective(newResume);
	}

	@Override
	public ResumeDTO findById(Long resumeId) {

		Resume resume = this.checkResume(resumeId);

		JobSeeker jobSeeker = jobSeekerService.checkJobSeeker(resume.getJobSeekerId());

		ResumeDTO resumeDTO = new ResumeDTO();
		List<EducationalBackgroundDTO> educationalBackgroundList = this.getEducationalBackground(resumeId);
		List<InternshipExperienceDTO> internshipExperienceList = this.getInternshipExperience(resumeId);
		List<ProjectExperienceDTO> projectExperienceList = this.getProjectExperience(resumeId);
		List<SkillHobbyDTO> skillHobbyList = this.getSkillHobby(resumeId);
		List<WorksShowDTO> worksShowList = this.getWorksShow(resumeId);
		List<SelfEvaluationDTO> selfEvaluationList = this.getSelfEvaluation(resumeId);

		resumeDTO.setId(resume.getId());
		resumeDTO.setCity(jobSeeker.getCity());
		resumeDTO.setEducation(jobSeeker.getEducation());
		resumeDTO.setImageUrl(jobSeeker.getImageUrl());
		resumeDTO.setBirthday(jobSeeker.getBirthday());
		resumeDTO.setGraduationSchool(jobSeeker.getGraduationSchool());
		resumeDTO.setPhone(jobSeeker.getPhone());
		resumeDTO.setEmail(jobSeeker.getEmail());
		resumeDTO.setJobSeekerId(jobSeeker.getId());
		resumeDTO.setJobSeekerName(jobSeeker.getName());
		resumeDTO.setSex(jobSeeker.getSex());
		resumeDTO.setName(resume.getName());
		resumeDTO.setIsDefault(resume.getIsDefault());
		resumeDTO.setWishPositionName(resume.getWishPositionName());
		resumeDTO.setWishCity(resume.getWishCity());
		resumeDTO.setPerDiem(resume.getPerDiem());
		resumeDTO.setWorkDay(resume.getWorkDay());
		resumeDTO.setWorkType(resume.getWorkType());
		resumeDTO.setInternshipTime(resume.getInternshipTime());
		resumeDTO.setArrivalDay(DateUtils.longToString(resume.getArrivalDay(), null));
		resumeDTO.setEducationalBackgroundList(educationalBackgroundList);
		resumeDTO.setInternshipExperienceList(internshipExperienceList);
		resumeDTO.setProjectExperienceList(projectExperienceList);
		resumeDTO.setSkillHobbyList(skillHobbyList);
		resumeDTO.setWorksShowList(worksShowList);
		resumeDTO.setSelfEvaluationList(selfEvaluationList);

		return resumeDTO;
	}

	private List<EducationalBackgroundDTO> getEducationalBackground(Long resumeId) {
		List<EducationalBackgroundDTO> list = new ArrayList<>();
		EducationalBackgroundExample example = new EducationalBackgroundExample();
		example.createCriteria().andResumeIdEqualTo(resumeId);
		List<EducationalBackground> entityList = educationalBackgroundDAO.selectByExample(example);
		if (CollectionUtils.isNotEmpty(entityList)) {
			for (EducationalBackground entity : entityList) {
				EducationalBackgroundDTO dto = new EducationalBackgroundDTO();
				dto.setId(entity.getId());
				dto.setStartTime(DateUtils.longToString(entity.getStartTime(), null));
				dto.setEndTime(DateUtils.longToString(entity.getEndTime(), null));
				dto.setEducation(entity.getEduation());
				dto.setSchool(entity.getSchool());
				dto.setCity(entity.getCity());
				dto.setMajor(entity.getMajor());
				dto.setMajorType(entity.getMajorType());
				dto.setMajorCourses(entity.getMajorCourses());
				dto.setRanking(entity.getRanking());
				dto.setHonor(entity.getHonor());
				list.add(dto);
			}
		}

		return list;
	}

	private List<InternshipExperienceDTO> getInternshipExperience(Long resumeId) {
		List<InternshipExperienceDTO> list = new ArrayList<>();
		InternshipExperienceExample example = new InternshipExperienceExample();
		example.createCriteria().andResumeIdEqualTo(resumeId);
		List<InternshipExperience> entityList = internshipExperienceDAO.selectByExample(example);
		if (CollectionUtils.isNotEmpty(entityList)) {
			for (InternshipExperience entity : entityList) {
				InternshipExperienceDTO dto = new InternshipExperienceDTO();
				dto.setId(entity.getId());
				dto.setCompanyName(entity.getCompanyName());
				dto.setCity(entity.getCity());
				dto.setStartTime(DateUtils.longToString(entity.getStartTime(), null));
				dto.setEndTime(DateUtils.longToString(entity.getEndTime(), null));
				dto.setDutyName(entity.getDutyName());
				dto.setDescription(entity.getDescription());
				list.add(dto);
			}
		}
		return list;
	}

	private List<ProjectExperienceDTO> getProjectExperience(Long resumeId) {
		List<ProjectExperienceDTO> list = new ArrayList<>();
		ProjectExperienceExample example = new ProjectExperienceExample();
		example.createCriteria().andResumeIdEqualTo(resumeId);
		List<ProjectExperience> entityList = projectExperienceDAO.selectByExample(example);
		if (CollectionUtils.isNotEmpty(entityList)) {
			for (ProjectExperience entity : entityList) {
				ProjectExperienceDTO dto = new ProjectExperienceDTO();
				dto.setId(entity.getId());
				dto.setCity(entity.getCity());
				dto.setStartTime(DateUtils.longToString(entity.getStartTime(), null));
				dto.setEndTime(DateUtils.longToString(entity.getEndTime(), null));
				dto.setDescription(entity.getDescription());
				dto.setProjectName(entity.getProjectName());
				dto.setRole(entity.getRole());
				list.add(dto);
			}
		}
		return list;
	}

	private List<SkillHobbyDTO> getSkillHobby(Long resumeId) {
		List<SkillHobbyDTO> list = new ArrayList<>();
		SkillHobbyExample example = new SkillHobbyExample();
		example.createCriteria().andResumeIdEqualTo(resumeId);
		List<SkillHobby> entityList = skillHobbyDAO.selectByExample(example);
		if (CollectionUtils.isNotEmpty(entityList)) {
			for (SkillHobby entity : entityList) {
				SkillHobbyDTO dto = new SkillHobbyDTO();
				dto.setId(entity.getId());
				dto.setName(entity.getName());
				dto.setLevel(entity.getLevel());
				list.add(dto);
			}
		}
		return list;
	}

	private List<WorksShowDTO> getWorksShow(Long resumeId) {
		List<WorksShowDTO> list = new ArrayList<>();
		WorksShowExample example = new WorksShowExample();
		example.createCriteria().andResumeIdEqualTo(resumeId);
		List<WorksShow> entityList = worksShowDAO.selectByExample(example);
		if (CollectionUtils.isNotEmpty(entityList)) {
			for (WorksShow entity : entityList) {
				WorksShowDTO dto = new WorksShowDTO();
				dto.setId(entity.getId());
				dto.setWorksUrl(entity.getWorksUrl());
				dto.setDescription(entity.getDescription());
				list.add(dto);
			}
		}
		return list;
	}

	private List<SelfEvaluationDTO> getSelfEvaluation(Long resumeId) {
		List<SelfEvaluationDTO> list = new ArrayList<>();
		SelfEvaluationExample example = new SelfEvaluationExample();
		example.createCriteria().andResumeIdEqualTo(resumeId);
		List<SelfEvaluation> entityList = selfEvaluationDAO.selectByExample(example);
		if (CollectionUtils.isNotEmpty(entityList)) {
			for (SelfEvaluation entity : entityList) {
				SelfEvaluationDTO dto = new SelfEvaluationDTO();
				dto.setId(entity.getId());
				dto.setDescription(entity.getDescription());
				list.add(dto);
			}
		}
		return list;
	}

	@Override
	public void saveInternshipExpectation(Long resumeId, InternshipExpectationDTO internshipExpectationDTO) {
		this.checkResume(resumeId);
		Resume resume = new Resume();
		resume.setId(resumeId);
		resume.setLastAccess(new Date().getTime());
		resume.setWishPositionName(internshipExpectationDTO.getWishPositionName());
		resume.setWishCity(internshipExpectationDTO.getWishCity());
		resume.setWorkDay(internshipExpectationDTO.getWorkDay());
		resume.setInternshipTime(internshipExpectationDTO.getInternshipTime());
		resume.setPerDiem(internshipExpectationDTO.getPerDiem());
		resume.setArrivalDay(DateUtils.stringToLong(internshipExpectationDTO.getArrivalDay(), null));
		resumeDAO.updateByPrimaryKeySelective(resume);

		// 更新简历完善度
		this.calculationResumePerfection(resumeId);
	}

	@Override
	public void saveInternshipExperience(Long resumeId, InternshipExperienceDTO internshipExperienceDTO) {
		this.checkResume(resumeId);
		InternshipExperience internshipExperience = new InternshipExperience();
		if (internshipExperienceDTO.getId() != null) {
			InternshipExperience i = internshipExperienceDAO.selectByPrimaryKey(internshipExperienceDTO.getId());
			if (i == null) {
				throw new BizException("未找到当前实习经历");
			}
			internshipExperience.setId(internshipExperienceDTO.getId());
		}
		internshipExperience.setLastAccess(new Date().getTime());
		internshipExperience.setResumeId(resumeId);
		internshipExperience.setCompanyName(internshipExperienceDTO.getCompanyName());
		internshipExperience.setCity(internshipExperienceDTO.getCity());
		internshipExperience.setDutyName(internshipExperienceDTO.getDutyName());
		internshipExperience.setDescription(internshipExperienceDTO.getDescription());
		internshipExperience.setStartTime(DateUtils.stringToLong(internshipExperienceDTO.getStartTime(), null));
		internshipExperience.setEndTime(DateUtils.stringToLong(internshipExperienceDTO.getEndTime(), null));

		if (internshipExperienceDTO.getId() != null) {
			internshipExperienceDAO.updateByPrimaryKeySelective(internshipExperience);
		} else {
			internshipExperienceDAO.insertSelective(internshipExperience);
		}
		// 更新简历完善度
		this.calculationResumePerfection(resumeId);
	}

	@Override
	public void saveEducationalBackground(Long resumeId, EducationalBackgroundDTO dto) {
		this.checkResume(resumeId);
		EducationalBackground entity = new EducationalBackground();
		if (dto.getId() != null) {
			EducationalBackground i = educationalBackgroundDAO.selectByPrimaryKey(dto.getId());
			if (i == null) {
				throw new BizException("未找到当前教育背景");
			}
			entity.setId(dto.getId());
		}
		entity.setLastAccess(new Date().getTime());
		entity.setResumeId(resumeId);
		entity.setStartTime(DateUtils.stringToLong(dto.getStartTime(), null));
		entity.setEndTime(DateUtils.stringToLong(dto.getEndTime(), null));
		entity.setEduation(dto.getEducation());
		entity.setSchool(dto.getSchool());
		entity.setCity(dto.getCity());
		entity.setMajor(dto.getMajor());
		entity.setMajorType(dto.getMajorType());
		entity.setMajorCourses(dto.getMajorCourses());
		entity.setRanking(dto.getRanking());
		entity.setHonor(dto.getHonor());

		if (dto.getId() != null) {
			educationalBackgroundDAO.updateByPrimaryKeySelective(entity);
		} else {
			educationalBackgroundDAO.insertSelective(entity);
		}
		// 更新简历完善度
		this.calculationResumePerfection(resumeId);
	}

	@Override
	public void saveProjectExperience(Long resumeId, ProjectExperienceDTO dto) {
		this.checkResume(resumeId);
		ProjectExperience entity = new ProjectExperience();
		if (dto.getId() != null) {
			ProjectExperience i = projectExperienceDAO.selectByPrimaryKey(dto.getId());
			if (i == null) {
				throw new BizException("未找到当前项目经历");
			}
			entity.setId(dto.getId());
		}
		entity.setLastAccess(new Date().getTime());
		entity.setResumeId(resumeId);
		entity.setStartTime(DateUtils.stringToLong(dto.getStartTime(), null));
		entity.setEndTime(DateUtils.stringToLong(dto.getEndTime(), null));
		entity.setCity(dto.getCity());
		entity.setProjectName(dto.getProjectName());
		entity.setRole(dto.getRole());
		entity.setDescription(dto.getDescription());

		if (dto.getId() != null) {
			projectExperienceDAO.updateByPrimaryKeySelective(entity);
		} else {
			projectExperienceDAO.insertSelective(entity);
		}
		// 更新简历完善度
		this.calculationResumePerfection(resumeId);
	}

	@Override
	public void saveSkillHobby(Long resumeId, SkillHobbyDTO dto) {
		this.checkResume(resumeId);
		SkillHobby entity = new SkillHobby();
		if (dto.getId() != null) {
			SkillHobby i = skillHobbyDAO.selectByPrimaryKey(dto.getId());
			if (i == null) {
				throw new BizException("未找到当前能力和爱好");
			}
			entity.setId(dto.getId());
		}
		entity.setLastAccess(new Date().getTime());
		entity.setResumeId(resumeId);
		entity.setName(dto.getName());
		entity.setLevel(dto.getLevel());

		if (dto.getId() != null) {
			skillHobbyDAO.updateByPrimaryKeySelective(entity);
		} else {
			skillHobbyDAO.insertSelective(entity);
		}
		// 更新简历完善度
		this.calculationResumePerfection(resumeId);
	}

	@Override
	public void saveWorksShow(Long resumeId, WorksShowDTO dto) {
		this.checkResume(resumeId);
		WorksShow entity = new WorksShow();
		if (dto.getId() != null) {
			WorksShow i = worksShowDAO.selectByPrimaryKey(dto.getId());
			if (i == null) {
				throw new BizException("未找到当前作品展示");
			}
			entity.setId(dto.getId());
		}
		entity.setLastAccess(new Date().getTime());
		entity.setResumeId(resumeId);
		entity.setWorksUrl(dto.getWorksUrl());
		entity.setDescription(dto.getDescription());

		if (dto.getId() != null) {
			worksShowDAO.updateByPrimaryKeySelective(entity);
		} else {
			worksShowDAO.insertSelective(entity);
		}
		// 更新简历完善度
		this.calculationResumePerfection(resumeId);
	}

	@Override
	public void saveSelfEvaluation(Long resumeId, SelfEvaluationDTO dto) {
		this.checkResume(resumeId);
		SelfEvaluation entity = new SelfEvaluation();
		if (dto.getId() != null) {
			SelfEvaluation i = selfEvaluationDAO.selectByPrimaryKey(dto.getId());
			if (i == null) {
				throw new BizException("未找到当前自我评价");
			}
			entity.setId(dto.getId());
		}
		entity.setLastAccess(new Date().getTime());
		entity.setResumeId(resumeId);
		entity.setDescription(dto.getDescription());

		if (dto.getId() != null) {
			selfEvaluationDAO.updateByPrimaryKeySelective(entity);
		} else {
			selfEvaluationDAO.insertSelective(entity);
		}
		// 更新简历完善度
		this.calculationResumePerfection(resumeId);
	}

	public int calculationResumePerfection(Long resumeId) {

		Resume resume = this.checkResume(resumeId);
		JobSeeker jobSeeker = jobSeekerService.checkJobSeeker(resume.getJobSeekerId());

		// 用户名 是 5
		// 区域地址 是 5
		// 学校 是 5
		// 学历 是 5
		// 手机 是 5
		// 电子邮箱 是 5
		// 实习期望 是 10
		// 教育背景 是 10
		// 实习经历 是 20
		// 项目经历 是 15
		// 技能爱好 是 5
		// 作品展示 是 5
		// 自我评价 是 5
		int perfection = 0;
		if (!StringUtils.isEmpty(jobSeeker.getName())) {
			perfection += 5;
		}
		if (!StringUtils.isEmpty(jobSeeker.getCity())) {
			perfection += 5;
		}
		if (!StringUtils.isEmpty(jobSeeker.getGraduationSchool())) {
			perfection += 5;
		}
		if (!StringUtils.isEmpty(jobSeeker.getEducation())) {
			perfection += 5;
		}
		if (!StringUtils.isEmpty(jobSeeker.getPhone())) {
			perfection += 5;
		}
		if (!StringUtils.isEmpty(jobSeeker.getEmail())) {
			perfection += 5;
		}
		if (!StringUtils.isEmpty(resume.getWishPositionName())) {
			perfection += 10;
		}
		EducationalBackgroundExample educationalBackgroundExample = new EducationalBackgroundExample();
		educationalBackgroundExample.createCriteria().andResumeIdEqualTo(resumeId);
		Long count = educationalBackgroundDAO.countByExample(educationalBackgroundExample);
		if (count != null && count > 0) {
			perfection += 10;
		}

		InternshipExperienceExample internshipExperienceExample = new InternshipExperienceExample();
		internshipExperienceExample.createCriteria().andResumeIdEqualTo(resumeId);
		count = internshipExperienceDAO.countByExample(internshipExperienceExample);
		if (count != null && count > 0) {
			perfection += 20;
		}

		ProjectExperienceExample projectExperienceExample = new ProjectExperienceExample();
		projectExperienceExample.createCriteria().andResumeIdEqualTo(resumeId);
		count = projectExperienceDAO.countByExample(projectExperienceExample);
		if (count != null && count > 0) {
			perfection += 15;
		}

		SkillHobbyExample skillHobbyExample = new SkillHobbyExample();
		skillHobbyExample.createCriteria().andResumeIdEqualTo(resumeId);
		count = skillHobbyDAO.countByExample(skillHobbyExample);
		if (count != null && count > 0) {
			perfection += 5;
		}

		WorksShowExample worksShowExample = new WorksShowExample();
		worksShowExample.createCriteria().andResumeIdEqualTo(resumeId);
		count = worksShowDAO.countByExample(worksShowExample);
		if (count != null && count > 0) {
			perfection += 5;
		}

		SelfEvaluationExample selfEvaluationExample = new SelfEvaluationExample();
		selfEvaluationExample.createCriteria().andResumeIdEqualTo(resumeId);
		count = selfEvaluationDAO.countByExample(selfEvaluationExample);
		if (count != null && count > 0) {
			perfection += 5;
		}

		Resume newResume = new Resume();
		newResume.setId(resumeId);
		newResume.setLastAccess(new Date().getTime());
		newResume.setIntegrity(perfection);
		resumeDAO.updateByPrimaryKeySelective(newResume);

		return perfection;
	}

	@Override
	public void add(Long jobSeekerId) {
		ResumeExample example = new ResumeExample();
		example.createCriteria().andJobSeekerIdEqualTo(jobSeekerId).andIsDeleteEqualTo(IsDeleteEnum.nomal.getId());
		Long count = resumeDAO.countByExample(example);

		Resume resume = new Resume();
		resume.setJobSeekerId(jobSeekerId);
		resume.setLastAccess(new Date().getTime());
		resume.setIsDelete(IsDeleteEnum.nomal.getId());
		resume.setIsDefault(
				count != null && count > 0 ? IsDefaultEnum.nomal.getId() : IsDefaultEnum.is_default.getId());
		resume.setIntegrity(0);
		resumeDAO.insertSelective(resume);

		Long resumeId = resume.getId();
		resume = new Resume();
		resume.setId(resumeId);
		resume.setIntegrity(this.calculationResumePerfection(resumeId));
		resumeDAO.updateByPrimaryKeySelective(resume);
	}

	@Override
	public void modifyResumeName(Long resumeId, String name) {
		this.checkResume(resumeId);
		Resume resume = new Resume();
		resume.setId(resumeId);
		resume.setName(name);
		resume.setLastAccess(new Date().getTime());
		resumeDAO.updateByPrimaryKeySelective(resume);
	}

	@Override
	public void acceptInterview(Long deliveryResumeId) {

		DeliveryResume deliveryResume = deliveryResumeDAO.selectByPrimaryKey(deliveryResumeId);
		if (deliveryResume == null) {
			throw new BizException("未找到投递信息");
		}

		DeliveryResume newDeliveryResume = new DeliveryResume();
		newDeliveryResume.setId(deliveryResumeId);
		newDeliveryResume.setStatus(DeliveryResumeStatusEnum.accept.getId());
		deliveryResumeDAO.updateByPrimaryKeySelective(newDeliveryResume);
	}

	@Override
	public void deleteInternshipExperience(Long internshipExperienceId) {
		InternshipExperience entity = internshipExperienceDAO.selectByPrimaryKey(internshipExperienceId);
		Long resumeId = 0L;
		if (entity != null) {
			entity.getResumeId();
		}
		internshipExperienceDAO.deleteByPrimaryKey(internshipExperienceId);
		this.calculationResumePerfection(resumeId);
	}

	@Override
	public void deleteEducationalBackground(Long educationalBackgroundId) {
		EducationalBackground entity = educationalBackgroundDAO.selectByPrimaryKey(educationalBackgroundId);
		Long resumeId = 0L;
		if (entity != null) {
			entity.getResumeId();
		}
		educationalBackgroundDAO.deleteByPrimaryKey(educationalBackgroundId);
		this.calculationResumePerfection(resumeId);

	}

	@Override
	public void deleteProjectExperience(Long projectExperienceId) {
		ProjectExperience entity = projectExperienceDAO.selectByPrimaryKey(projectExperienceId);
		Long resumeId = 0L;
		if (entity != null) {
			entity.getResumeId();
		}
		projectExperienceDAO.deleteByPrimaryKey(projectExperienceId);
		this.calculationResumePerfection(resumeId);
	}

	@Override
	public void deleteSkillHobby(Long skillHobbyId) {
		SkillHobby entity = skillHobbyDAO.selectByPrimaryKey(skillHobbyId);
		Long resumeId = 0L;
		if (entity != null) {
			entity.getResumeId();
		}
		skillHobbyDAO.deleteByPrimaryKey(skillHobbyId);
		this.calculationResumePerfection(resumeId);
	}

	@Override
	public void deleteWorksShow(Long worksShowId) {
		WorksShow entity = worksShowDAO.selectByPrimaryKey(worksShowId);
		Long resumeId = 0L;
		if (entity != null) {
			entity.getResumeId();
		}
		worksShowDAO.deleteByPrimaryKey(worksShowId);
		this.calculationResumePerfection(resumeId);
	}

	@Override
	public void deleteSelfEvaluation(Long selfEvaluationId) {
		SelfEvaluation entity = selfEvaluationDAO.selectByPrimaryKey(selfEvaluationId);
		Long resumeId = 0L;
		if (entity != null) {
			entity.getResumeId();
		}
		selfEvaluationDAO.deleteByPrimaryKey(selfEvaluationId);
		this.calculationResumePerfection(resumeId);
	}

	@Override
	public ResumeDTO conmpanyGetDeliveryResume(Long deliveryResumeId) {

		DeliveryResume deliveryResume = deliveryResumeDAO.selectByPrimaryKey(deliveryResumeId);

		if (deliveryResume == null) {
			throw new BizException("未找到投递信息");
		}

		ResumeDTO resumeDTO = this.findById(deliveryResume.getResumeId());

		Position position = positionDAO.selectByPrimaryKey(deliveryResume.getPositionId());

		Interview interview = interviewDAO.getInterviewByDeliveryResumeId(deliveryResumeId);

		resumeDTO.setPositionId(position.getId());
		resumeDTO.setPositionName(position.getName());
		resumeDTO.setDeliveryStatus(DeliveryResumeStatusEnum.getValueById(deliveryResume.getStatus()));
		if (interview != null) {
			resumeDTO.setInternshipTime(DateUtils.longToString(interview.getInterviewTime(), null));
			resumeDTO.setInterviewAddress(interview.getAddress());
			resumeDTO.setInterviewContract(interview.getContacts());
			resumeDTO.setInterviewPhone(interview.getPhone());
			resumeDTO.setInterviewNote(interview.getNote());
		}

		return resumeDTO;
	}

	@Override
	public PageDTO<ResumeLibDTO> resumeLibList(ResumeSearchDTO resumeSearchDTO, Long page, Integer size) {

		Map<String, Object> params = new HashMap<String, Object>();

		if (page == null || page <= 0) {
			page = 1L;
		}
		if (size == null || size <= 0) {
			size = 10;
		}

		params.put("starNum", (page - 1) * size);
		params.put("size", size);

		if (resumeSearchDTO == null) {
			resumeSearchDTO = new ResumeSearchDTO();
		}
		if (!StringUtils.isEmpty(resumeSearchDTO.getName())) {
			params.put("name", resumeSearchDTO.getName());
		}
		if (!StringUtils.isEmpty(resumeSearchDTO.getType())) {
			params.put("type", resumeSearchDTO.getType());
		}
		if (!StringUtils.isEmpty(resumeSearchDTO.getSearchKey())) {
			params.put("searchKey", resumeSearchDTO.getSearchKey());
		}
		if (!StringUtils.isEmpty(resumeSearchDTO.getCity())) {
			params.put("city", resumeSearchDTO.getCity());
		}
		if (!StringUtils.isEmpty(resumeSearchDTO.getInternshipTime())) {
			params.put("internshipTime", resumeSearchDTO.getInternshipTime());
		}
		if (resumeSearchDTO.getWorkDay() != null) {
			params.put("workDay", resumeSearchDTO.getWorkDay());
		}
		if (!StringUtils.isEmpty(resumeSearchDTO.getArrayDay())) {
			params.put("arrayDay", DateUtils.stringToLong(resumeSearchDTO.getArrayDay(), null));
		}
		if (!StringUtils.isEmpty(resumeSearchDTO.getEducation())) {
			params.put("education", resumeSearchDTO.getEducation());
		}
		if (!StringUtils.isEmpty(resumeSearchDTO.getGraduationTime())) {
			params.put("graduationTime", resumeSearchDTO.getGraduationTime());
		}
		if (!StringUtils.isEmpty(resumeSearchDTO.getGraduationSchool())) {
			params.put("graduationSchool", resumeSearchDTO.getGraduationSchool());
		}
		if (!StringUtils.isEmpty(resumeSearchDTO.getSex())) {
			params.put("sex", resumeSearchDTO.getSex());
		}

		List<ResumeLibDTO> list = resumeDAO.queryLibByPage(params);
		Long count = resumeDAO.countLibByPage(params);

		return new PageDTO<>(count, list, page, size);
	}

	@Override
	public ResumeDTO conmpanyGetResume(Long companyId, Long resumeId) {

		ResumeDTO resumeDTO = this.findById(resumeId);

		Double amount = 0d;

		boolean flag = true;

		// 搜索简历是否投递过该公司，如果是，不需要付费，如果不是，需要付费
		List<DeliveryResume> deliveryResumes = deliveryResumeDAO.getDeliveryResume(companyId, resumeId);
		if (CollectionUtils.isNotEmpty(deliveryResumes)) {
			flag = false;
		}

		// 判断公司是否下载付费过这个人，如果是，不需要付费
		if (!companyJobSeekerDAO.comfirmIsPay(companyId, resumeDTO.getJobSeekerId())) {
			flag = false;
		}

		if (flag) {
			resumeDTO.setPhone(StringUtils.isEmpty(resumeDTO.getPhone()) ? ""
					: resumeDTO.getPhone().substring(0, 3) + "****" + resumeDTO.getPhone().substring(7, 11));
			resumeDTO.setPhone(StringUtils.isEmpty(resumeDTO.getEmail()) ? ""
					: resumeDTO.getEmail().substring(0, 1) + "**********" + resumeDTO.getEmail()
							.substring(resumeDTO.getEmail().indexOf("@"), resumeDTO.getEmail().length()));
			amount = accountService.getAmountSettingByType(resumeDTO.getEducation());
		}

		resumeDTO.setNeedPay(flag);
		resumeDTO.setAmount(amount);

		return resumeDTO;
	}

	@Override
	public String downloadResume(Long companyId, Long resumeId) {
		Resume resume = resumeDAO.selectByPrimaryKey(resumeId);

		// 判断是否已经付过费 true 付过了不管 false没付过
		boolean flag = companyJobSeekerDAO.comfirmIsPay(companyId, resume.getJobSeekerId());

		// 没有付过就查询是否是被投递
		if (!flag) {
			// 判断是否被投递给这家公司
			List<DeliveryResume> list = deliveryResumeDAO.getDeliveryResume(companyId, resumeId);
			flag = CollectionUtils.isNotEmpty(list) ? true : false;
		}

		if (!flag) {
			throw new BizException("简历未付费，下载失败");
		}
		
		JobSeeker jobSeeker = jobSeekerDAO.selectByPrimaryKey(resume.getJobSeekerId());

		String fileName = jobSeeker.getName()+"."+resume.getId() + "_" + resume.getLastAccess();

		String filePath = ExportPDF.path + fileName + ".pdf";

		// 下载
		File file = new File(filePath);

		// XWPFDocument doc;
		// 判断文件是否存在
		if (!file.exists()) {
			// 不存在，生成
			ResumeDTO resumeDTO = this.findById(resumeId);
			ExportPDF.exportPDF(resumeDTO, filePath);
			// 生成文件
		}

		return filePath;
	}

	@Override
	public InputStream downloadResumes(Long companyId, List<Long> resumeIds) {

		if (CollectionUtils.isEmpty(resumeIds)) {
			throw new BizException("参数异常，请选择简历");
		}

		// 生成word文件
		for (Long resumeId : resumeIds) {
			this.downloadResume(companyId, resumeId);
		}

		ResumeExample example = new ResumeExample();
		example.createCriteria().andIdIn(resumeIds);
		List<Resume> list = resumeDAO.selectByExample(example);

		List<File> files = new ArrayList<>();
		for (Resume resume : list) {
			JobSeeker jobSeeker = jobSeekerDAO.selectByPrimaryKey(resume.getJobSeekerId());
			files.add(
					new File(jobSeeker.getName() + "." + resume.getId() + "_" + resume.getLastAccess() + ".pdf"));
		}

		// 打包下载
		InputStream is = null;

		try {
			is = PackWord.downLoadFiles(files);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return is;
	}

}
