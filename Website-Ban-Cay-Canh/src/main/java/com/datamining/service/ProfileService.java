package com.datamining.service;

import java.util.List;

import com.datamining.entity.Profile;

public interface ProfileService {

	List<Profile> findAll();
	
	Profile findProfile(String username);

	Profile update(Profile profile);
	
	Profile findbyIdUs(Integer id);
	
	Profile save(Profile p);
}
