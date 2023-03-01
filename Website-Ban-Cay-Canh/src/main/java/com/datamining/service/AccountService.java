package com.datamining.service;

import com.datamining.entity.Account;

import java.util.List;

public interface AccountService {
    List<Account> findAll();

    Account findById(Integer id);

    Account create(Account account);

    Account update(Account account);

    void delete(Integer id);
}
