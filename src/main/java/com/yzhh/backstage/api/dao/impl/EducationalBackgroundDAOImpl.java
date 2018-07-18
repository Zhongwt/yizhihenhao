package com.yzhh.backstage.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.yzhh.backstage.api.dao.IEducationalBackgroundDAO;
import com.yzhh.backstage.api.entity.EducationalBackground;
import com.yzhh.backstage.api.entity.EducationalBackgroundExample;

@Repository("educationalBackgroundDAO")
public class EducationalBackgroundDAOImpl extends DAOImpl<EducationalBackground, EducationalBackgroundExample> implements IEducationalBackgroundDAO {

}
