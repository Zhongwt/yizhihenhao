package com.yzhh.backstage.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.yzhh.backstage.api.dao.IInterviewDAO;
import com.yzhh.backstage.api.entity.Interview;
import com.yzhh.backstage.api.entity.InterviewExample;

@Repository("interviewDAO")
public class InterviewDAOImpl extends DAOImpl<Interview, InterviewExample> implements IInterviewDAO {


}
