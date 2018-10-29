package com.yzhh.backstage.api.dao.mapper;

import com.yzhh.backstage.api.entity.Banner;
import com.yzhh.backstage.api.entity.BannerExample;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BannerMapper extends BaseMapper<Banner,BannerExample>{
}