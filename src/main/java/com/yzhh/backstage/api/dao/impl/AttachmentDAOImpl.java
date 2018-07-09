package com.yzhh.backstage.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.yzhh.backstage.api.dao.IAttachmentDAO;
import com.yzhh.backstage.api.entity.Attachment;
import com.yzhh.backstage.api.entity.AttachmentExample;

@Repository("attachmentDAO")
public class AttachmentDAOImpl extends DAOImpl<Attachment, AttachmentExample> implements IAttachmentDAO {

}
