package com.datamining.servide.impl;

import com.datamining.dao.ProductDao;
import com.datamining.entity.Product;
import com.datamining.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductDao pdao;

    @Override
    public Product save(Product product) {
        return pdao.save(product);
    }

    @Override
    public List<Product> findAll() {
        return pdao.findAll();
    }

    @Override
    public Product findById(Integer id) {
        // TODO Auto-generated method stub
        return pdao.findById(id).get();
    }

    @Override
    public Product findByUrlEquals(String url) {
        return pdao.findByUrlEquals(url);
    }

    @Override
    public List<Product> findByCategoryId(String cid) {
        // TODO Auto-generated method stub
        return pdao.findByCategoryId(cid);
    }

    @Override
    public List<Product> findByKeyword(String keyword) {
        // TODO Auto-generated method stub
        return pdao.findByKeyword(keyword);
    }


    @Override
    public List<Product> findByPriceBetween(Double price1, Double price2) {
        return pdao.findByPriceBetween(price1, price2);
    }

    @Override
    public Product create(Product product) {
        return pdao.save(product);
    }

    @Override
    public Product update(Integer id, Product product) {
        return pdao.save(product);
    }

    @Override
    public void delete(Integer id) {
        pdao.deleteById(id);
    }

    @Override
    public List<Product> findTop5Seller() {
        return pdao.findTop5Seller();
    }

	@Override
	public List<Product> selectAllFeedbacks() {
		return pdao.selectAllFeedbacks();
	}

    @Override
    public Page<Product> findAllByPage(Pageable page) {
        return pdao.findAll(page);
    }


}
