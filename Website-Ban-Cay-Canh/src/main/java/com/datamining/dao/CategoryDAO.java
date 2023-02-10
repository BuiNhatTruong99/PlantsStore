package com.datamining.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datamining.entity.Category;

public interface CategoryDAO  extends JpaRepository<Category, Integer>{

}
