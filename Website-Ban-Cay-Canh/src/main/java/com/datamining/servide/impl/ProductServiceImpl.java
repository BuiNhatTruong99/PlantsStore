package com.datamining.servide.impl;

import com.datamining.dao.ProductDao;
import com.datamining.entity.Product;
import com.datamining.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
