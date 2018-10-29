package com.yzhh.backstage.api.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yzhh.backstage.api.dao.IBannerDAO;
import com.yzhh.backstage.api.entity.Banner;
import com.yzhh.backstage.api.entity.BannerExample;
import com.yzhh.backstage.api.service.IBannerService;

@Service
public class BannerServiceImpl implements IBannerService {
	
	@Autowired
	private IBannerDAO bannerDAO;

	@Override
	public List<Banner> list() {
		BannerExample example = new BannerExample();
		List<Banner> list = bannerDAO.selectByExample(example);
		return list;
	}

	@Override
	public void save(Banner banner) {
		banner.setLastAccess(new Date().getTime());
		if(banner.getId() == null) {
			bannerDAO.insertSelective(banner);
		}else {
			bannerDAO.updateByPrimaryKeySelective(banner);
		}
	}

	@Override
	public void delete(Long id) {
		bannerDAO.deleteByPrimaryKey(id);
	}
	
	

}
