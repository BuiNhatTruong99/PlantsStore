package com.datamining.rest;


import com.datamining.entity.Profile;
import com.datamining.service.profilesService;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profile")
public class ProfilesRest {
    @Autowired
    profilesService profileService;

    @GetMapping()
    public List <Profile> getAll() {
        List<Profile> list = profileService.findAll();
       return list;
    }
    @PutMapping("{id}")
    public  Profile update(@PathVariable("id") Integer id, @RequestBody Profile profile ){
        return  profileService.update(profile);
    }
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Integer id){
      profileService.delete(id);
    }

}
