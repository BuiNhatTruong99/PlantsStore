package com.datamining.service;

import java.util.List;

import com.datamining.entity.ProductRate;

public interface ProductRateService {

	List<ProductRate> findAllByProID(Integer id);
	
	ProductRate create(ProductRate productRate);
}
