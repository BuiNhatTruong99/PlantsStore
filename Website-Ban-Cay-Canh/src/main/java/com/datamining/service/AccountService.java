package com.datamining.service;

import com.datamining.entity.Account;
import com.datamining.entity.Profile;

import java.util.List;

public interface AccountService {

    List<Account> findAll();

    Account findById(Integer id);
    
    Account findByTk(String username);
    
	Account create(Account account);

    Account update(Account account);

    void delete(Integer id);

    List<Account> findByUsername(String username);

}
