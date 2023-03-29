package com.datamining.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.datamining.entity.Profile;
import com.datamining.service.ProfileService;

@RestController
@RequestMapping("/profile")
public class ProfileRest {

	@Autowired
	ProfileService pseProfileService;
	@GetMapping()
	public List<Profile> getAllProfile(){
		return pseProfileService.findAll();
	}

	@GetMapping("{id}")
	public Profile findbyIdUS(@PathVariable("id")Integer id)
	{
		return pseProfileService.findbyIdUs(id);
	}

	@GetMapping("update/{id}")
	public Profile findbyIdUS2(@PathVariable("id")Integer id)
	{
		return pseProfileService.findbyIdUs(id);
	}

	@PutMapping("update/{id}")
	public Profile update(@PathVariable("id")Integer id,@RequestBody Profile p)
	{
		return pseProfileService.save(p);
	}
}
