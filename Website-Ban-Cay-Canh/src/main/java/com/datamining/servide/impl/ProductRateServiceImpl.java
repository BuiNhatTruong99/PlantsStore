package com.datamining.servide.impl;

import java.util.List;

import com.datamining.dao.ProductRateDAO;
import com.datamining.entity.ProductRate;
import com.datamining.service.ProductRateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductRateServiceImpl implements ProductRateService {
	@Autowired
	ProductRateDAO dao;
	
    @Override
    public List<ProductRate> findAllByProID(Integer id) {
        return dao.listRateByProID(id);
    }

	@Override
	public ProductRate create(ProductRate productRate) {
		return dao.save(productRate);
	}
}
