package com.yzhh.backstage.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.yzhh.backstage.api.dao.IAdminDAO;
import com.yzhh.backstage.api.entity.Admin;
import com.yzhh.backstage.api.entity.AdminExample;

@Repository("adminDAO")
public class AdminDAOImpl extends DAOImpl<Admin, AdminExample> implements IAdminDAO{

}
