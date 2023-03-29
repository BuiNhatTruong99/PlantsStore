package com.datamining.servide.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.datamining.dao.ProfileDAO;
import com.datamining.entity.Profile;
import com.datamining.service.ProfileService;
import org.springframework.stereotype.Service;


@Service
public class ProfileServiceImpl implements ProfileService{

	@Autowired
	ProfileDAO pdao;

	@Override
	public Profile findProfile(String username) {
		// TODO Auto-generated method stub
		return pdao.findProfile(username);
	}

	@Override
	public Profile update(Profile profile) {
		return pdao.save(profile);
	}

	@Override
	public List<Profile> findAll() {
		// TODO Auto-generated method stub
		return pdao.findAll();
	}

	@Override
	public Profile findbyIdUs(Integer id) {
		// TODO Auto-generated method stub
		return pdao.findbyIdUs(id);
	}

	@Override
	public Profile save(Profile p) {
		// TODO Auto-generated method stub
		return pdao.save(p);
	}


}
