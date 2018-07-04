package com.yzhh.backstage.api.dao.impl;

import org.springframework.stereotype.Repository;

import com.yzhh.backstage.api.dao.IAccountDAO;
import com.yzhh.backstage.api.entity.Account;
import com.yzhh.backstage.api.entity.AccountExample;

@Repository("accountDAO")
public class AccountDAOImpl extends DAOImpl<Account, AccountExample> implements IAccountDAO {


}
