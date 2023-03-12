package com.datamining.servide.impl;

import com.datamining.dao.AccountDAO;
import com.datamining.entity.Account;
import com.datamining.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountDAO accountDAO;

    @Override
    public List<Account> findAll() {
        return accountDAO.findAll();
    }

    @Override
    public Account findById(Integer id) {
        return accountDAO.findById(id).get();
    }
    
	@Override
	public Account findByTk(String username) {
		return accountDAO.findByTk(username); // Đã trùng
	}
	
	@Override
	public Account create(Account account) {
		return accountDAO.save(account);
	}

    @Override
    public Account create(Account account) {
        return accountDAO.save(account);
    }

    @Override
    public Account update(Account account) {
        return accountDAO.save(account);
    }

    @Override
    public void delete(Integer id) {
        accountDAO.deleteById(id);
    }

    @Override
    public List<Account> findByUsername(String username) {
        return accountDAO.findByUsername(username);
    }

}
