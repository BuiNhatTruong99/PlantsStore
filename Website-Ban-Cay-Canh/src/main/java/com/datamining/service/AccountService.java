package com.datamining.service;

import com.datamining.entity.Account;
import com.datamining.entity.Profile;

import java.util.List;

public interface AccountService {

    List<Account> findAll();

    Account findById(Integer id);
    
    Account findByTk(String username);

    Account update(Account account);
    
//    Profile findProfile(String username);
    
  
}
