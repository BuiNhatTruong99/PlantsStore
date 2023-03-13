package com.datamining.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.datamining.entity.Account;
import org.springframework.data.repository.query.Param;

public interface AccountDAO extends JpaRepository<Account, Integer> {

	@Query("select u from Account u where u.username = :username ")
	public Account findByTk(@Param("username")String username);

//	@Query(value="select p.* from Accounts u join Profile p on u.id = p.user_id where u.username = :username ",nativeQuery = true)
//	public Account findProfile(@Param("username")String username);
}
