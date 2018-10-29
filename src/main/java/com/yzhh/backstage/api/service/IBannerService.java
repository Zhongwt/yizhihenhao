package com.yzhh.backstage.api.service;

import java.util.List;

import com.yzhh.backstage.api.entity.Banner;

public interface IBannerService {

	public List<Banner> list();
	
	public void save(Banner banner);
	
	public void delete(Long id);
}
