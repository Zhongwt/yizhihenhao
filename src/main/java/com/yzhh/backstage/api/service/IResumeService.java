package com.yzhh.backstage.api.service;

import java.util.List;

import com.yzhh.backstage.api.dto.PageDTO;
import com.yzhh.backstage.api.dto.resume.AddInterviewDTO;
import com.yzhh.backstage.api.dto.resume.PageResumeDTO;
import com.yzhh.backstage.api.dto.resume.ResumeSearchDTO;

public interface IResumeService {

	//分页查询求职情况
	public PageDTO<PageResumeDTO> queryPage(ResumeSearchDTO resumeSearchDTO,Long page,Integer size);
	//批量修改简历状态
	public void updateStatus(List<Long> ids,Integer status);
	//面试邀请
	public void interviewInvitation(AddInterviewDTO addInterviewDTO);
	//获取名字
	public String getName(List<Long> ids);
}
