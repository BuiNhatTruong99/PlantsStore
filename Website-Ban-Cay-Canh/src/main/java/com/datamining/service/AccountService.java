package com.datamining.service;

import com.datamining.entity.Account;
import java.util.List;

public interface AccountService {

	Account findByTk(String username);

    List<Account> findAll();

    Account findById(Integer id);


    Account update(Account account);
}
