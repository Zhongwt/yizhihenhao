package com.yzhh.backstage.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.yzhh.backstage.api.dao.IInternshipExperienceDAO;
import com.yzhh.backstage.api.entity.InternshipExperience;
import com.yzhh.backstage.api.entity.InternshipExperienceExample;

@Repository("internshipExperienceDAO")
public class InternshipExperienceDAOImpl extends DAOImpl<InternshipExperience, InternshipExperienceExample> implements IInternshipExperienceDAO {


}
