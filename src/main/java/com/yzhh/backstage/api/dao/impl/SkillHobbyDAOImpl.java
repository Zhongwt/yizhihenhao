package com.yzhh.backstage.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.yzhh.backstage.api.dao.ISkillHobbyDAO;
import com.yzhh.backstage.api.entity.SkillHobby;
import com.yzhh.backstage.api.entity.SkillHobbyExample;

@Repository("skillHobbyDAO")
public class SkillHobbyDAOImpl extends DAOImpl<SkillHobby, SkillHobbyExample> implements ISkillHobbyDAO {

}
