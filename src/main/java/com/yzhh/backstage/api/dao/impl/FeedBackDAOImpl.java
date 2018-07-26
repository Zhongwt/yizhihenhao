package com.yzhh.backstage.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.yzhh.backstage.api.dao.IFeedBackDAO;
import com.yzhh.backstage.api.entity.Feedback;
import com.yzhh.backstage.api.entity.FeedbackExample;

@Repository("feedBackDAO")
public class FeedBackDAOImpl extends DAOImpl<Feedback, FeedbackExample> implements IFeedBackDAO {


}
