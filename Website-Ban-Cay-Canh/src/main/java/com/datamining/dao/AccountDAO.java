package com.datamining.dao;

import com.datamining.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccountDAO extends JpaRepository<Account, Integer> {

    // find list account  by username
    @Query("SELECT a FROM Account a WHERE a.username LIKE %?1%")
    List<Account> findByUsername(String username);

    @Query("select u from Account u where u.username = :username ")
    public Account findByTk(@Param("username") String username);

}
