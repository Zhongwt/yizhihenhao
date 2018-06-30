package com.yzhh.backstage.api.service;

import java.util.List;

import com.yzhh.backstage.api.dto.PageDTO;
import com.yzhh.backstage.api.dto.position.PositionDTO;
import com.yzhh.backstage.api.dto.position.SearchPositionDTO;

public interface IPositionService {

	//保存职位
	public void saveOrUpdatePosition(PositionDTO positionDTO);
	//职位列表
	public PageDTO<PositionDTO> list(SearchPositionDTO searchPositionDTO,Long page,Integer size);
	//批量下线
	public void downLine(List<Long> ids);
	//职位详情
	public PositionDTO findById(Long id);
	//通过职位
	public void passPosition(Long id);
	
	//用于获取职位名字
	public String getName(List<Long> ids);
	
}