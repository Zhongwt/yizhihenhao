package com.yzhh.backstage.api.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.yzhh.backstage.api.entity.Attachment;
import com.yzhh.backstage.api.entity.AttachmentExample;

@Mapper
public interface AttachmentMapper extends BaseMapper<Attachment, AttachmentExample>{
}