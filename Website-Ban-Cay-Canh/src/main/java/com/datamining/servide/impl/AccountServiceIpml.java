package com.datamining.servide.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datamining.dao.AccountDAO;
import com.datamining.entity.Account;
import com.datamining.service.AccountService;

@Service
public class AccountServiceIpml implements AccountService{
	@Autowired
	private AccountDAO adao;

	@Override
	public Account findByTk(String username) {
		// TODO Auto-generated method stub
		return adao.findByTk(username);
	}

	


}
