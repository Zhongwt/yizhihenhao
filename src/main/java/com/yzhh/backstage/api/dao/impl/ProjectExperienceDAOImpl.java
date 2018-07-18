package com.yzhh.backstage.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.yzhh.backstage.api.dao.IProjectExperienceDAO;
import com.yzhh.backstage.api.entity.ProjectExperience;
import com.yzhh.backstage.api.entity.ProjectExperienceExample;

@Repository("projectExperienceDAO")
public class ProjectExperienceDAOImpl extends DAOImpl<ProjectExperience, ProjectExperienceExample> implements IProjectExperienceDAO {

}
