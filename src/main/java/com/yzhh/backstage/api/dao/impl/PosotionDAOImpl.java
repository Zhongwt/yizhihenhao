package com.yzhh.backstage.api.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.yzhh.backstage.api.dao.IPositionDAO;
import com.yzhh.backstage.api.dao.mapper.custom.PositionMapper2;
import com.yzhh.backstage.api.entity.Position;
import com.yzhh.backstage.api.entity.PositionExample;

@Repository("positionDAO")
public class PosotionDAOImpl extends DAOImpl<Position, PositionExample> implements IPositionDAO {

	@Autowired
	private PositionMapper2 mapper2;
	
	@Override
	public List<Position> queryByPage(Map<String, Object> params) {
		return mapper2.queryByPage(params);
	}

	@Override
	public Long countByPage(Map<String, Object> params) {
		return mapper2.countByPage(params);
	}

	@Override
	public List<String> getCityForPosition() {
		return mapper2.getCityForPosition();
	}
}
