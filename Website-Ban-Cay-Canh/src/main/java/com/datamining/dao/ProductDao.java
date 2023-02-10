package com.datamining.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datamining.entity.Product;

public interface ProductDao extends JpaRepository<Product, Integer> {

}
