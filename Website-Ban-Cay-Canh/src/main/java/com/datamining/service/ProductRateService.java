package com.datamining.service;

import java.util.List;

import com.datamining.entity.ProductRate;

public interface ProductRateService {

	List<ProductRate> findAllByProID(Integer id);
	
	List<ProductRate> findAllByFilter(Integer productId, Float rate);
	
	List<ProductRate> findAllByFilter(Integer productId, Integer userId);
	
	List<ProductRate> findAllByFilter(Integer productId, Integer userId, Float rate);
	
	ProductRate create(ProductRate productRate);
}
