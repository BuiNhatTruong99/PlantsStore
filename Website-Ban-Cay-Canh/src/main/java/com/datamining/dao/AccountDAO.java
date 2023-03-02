package com.datamining.dao;

import com.datamining.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AccountDAO extends JpaRepository<Account, Integer> {

    // find list account  by username
    @Query("SELECT a FROM Account a WHERE a.username LIKE %?1%")
    List<Account> findByUsername(String username);


}
