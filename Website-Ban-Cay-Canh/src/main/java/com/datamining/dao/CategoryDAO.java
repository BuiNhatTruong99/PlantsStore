package com.datamining.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datamining.entity.Category;
import org.springframework.data.jpa.repository.Query;

public interface CategoryDAO  extends JpaRepository<Category, Integer>{

    // findidbyurl
    @Query(value="SELECT id FROM categories WHERE categories.url=?1", nativeQuery = true)
    String findIdByUrlEquals(String url);
}
