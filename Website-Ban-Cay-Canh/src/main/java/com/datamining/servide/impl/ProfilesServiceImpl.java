package com.datamining.servide.impl;
import com.datamining.dao.ProfileDAO;
import com.datamining.entity.Profile;
import com.datamining.service.profilesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProfilesServiceImpl implements profilesService {
@Autowired
    ProfileDAO pro;

    @Override
    public List<Profile> findAll() {
        return pro.findAll();
    }

    @Override
    public Profile update(Profile profile) {
        return pro.save(profile);
    }

    @Override
    public void  delete(Integer id) {
     pro.deleteById(id);
    }
}
