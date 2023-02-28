package com.datamining.service;

import com.datamining.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();

    //save
    Category save(Category category);

    // findidbyurl
    String findIdByUrlEquals(String url);

    //findbyid
    Category findById(Integer id);


}
