package com.yzhh.backstage.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.yzhh.backstage.api.dao.ISelfEvaluationDAO;
import com.yzhh.backstage.api.entity.SelfEvaluation;
import com.yzhh.backstage.api.entity.SelfEvaluationExample;

@Repository("selfEvaluationDAO")
public class SelfEvaluationDAOImpl extends DAOImpl<SelfEvaluation, SelfEvaluationExample> implements ISelfEvaluationDAO {


}
