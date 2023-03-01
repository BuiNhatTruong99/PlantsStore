package com.datamining.dao;

import java.util.List;

import com.datamining.entity.ProductRate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRateDAO extends JpaRepository<ProductRate, Integer>{
	@Query(value= "select * from Product_Rate where product_id = ?1 order by create_date desc", nativeQuery = true)
	List<ProductRate> listRateByProID(Integer productId);
	
	@Query(value= "select * from Product_Rate where product_id = ?1 and rate = ?2 order by create_date desc", nativeQuery = true)
	List<ProductRate> listRateByFilter(Integer productId, Float rate);
	
	@Query(value= "select * from Product_Rate where product_id = ?1 and profile_id = ?2 order by create_date desc", nativeQuery = true)
	List<ProductRate> listRateByFilter(Integer productId, Integer userId);
	
	@Query(value= "select * from Product_Rate where product_id = ?1 and profile_id = ?2 and rate = ?3 order by create_date desc", nativeQuery = true)
	List<ProductRate> listRateByFilter(Integer productId, Integer userId, Float rate);
}
