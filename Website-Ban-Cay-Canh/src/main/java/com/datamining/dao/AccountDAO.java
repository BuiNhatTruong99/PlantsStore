package com.datamining.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import com.datamining.entity.Account;
import org.springframework.data.repository.query.Param;

public interface AccountDAO extends JpaRepository<Account, Integer> {

	@Query("select u from Account u where u.username = :username ")
	public Account findByTk(@Param("username")String username);

    // find list account  by username
    @Query("SELECT a FROM Account a WHERE a.username LIKE %?1%")
    List<Account> findByUsername(String username);





}
