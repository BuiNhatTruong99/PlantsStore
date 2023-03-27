package com.datamining.service;


import com.datamining.entity.Profile;


import java.util.List;

public interface profilesService {


List<Profile> findAll() ;

    Profile update(Profile profile);

    void delete(Integer id);
}
