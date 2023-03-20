package com.datamining.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.datamining.entity.Profile;

public interface ProfileDAO extends JpaRepository<Profile, Integer> {

	@Query(value="select p.* from Profile p join Accounts u on u.id = p.user_id where u.username = :username ",nativeQuery = true)
	public Profile findProfile(String username);
	
	@Query(value="select p.* from Profile p join Accounts u on u.id = p.user_id where p.user_id = :id ",nativeQuery = true)
	public Profile findbyIdUs(Integer id);
	
	
}
