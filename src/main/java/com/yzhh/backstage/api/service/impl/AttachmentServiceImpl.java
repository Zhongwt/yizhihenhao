package com.yzhh.backstage.api.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yzhh.backstage.api.dao.IAttachmentDAO;
import com.yzhh.backstage.api.entity.Attachment;
import com.yzhh.backstage.api.service.IAttachmentService;

@Service
public class AttachmentServiceImpl implements IAttachmentService{

	@Autowired
	private IAttachmentDAO attachmentDAO;
	
	@Override
	public Long addAttachment(String fileName) {
		
		Attachment attachment = new Attachment();
		attachment.setName(fileName);
		attachment.setLastAccess(new Date().getTime());
		attachmentDAO.insertSelective(attachment);
		
		return attachment.getId();
	}

}
