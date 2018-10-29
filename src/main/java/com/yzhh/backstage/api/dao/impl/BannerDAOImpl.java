package com.yzhh.backstage.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.yzhh.backstage.api.dao.IBannerDAO;
import com.yzhh.backstage.api.entity.Banner;
import com.yzhh.backstage.api.entity.BannerExample;

@Repository("bannerDAO")
public class BannerDAOImpl extends DAOImpl<Banner, BannerExample> implements IBannerDAO {

}
