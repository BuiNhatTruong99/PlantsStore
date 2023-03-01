package com.datamining.service;

import com.datamining.entity.Product;

import java.util.List;

public interface ProductService {
    Product save(Product product);

    List<Product> findAll();

    Product findById(Integer id);

    Product findByUrlEquals(String url);

    List<Product> findByCategoryId(String cid);

    List<Product> findByKeyword(String keyword);


    List<Product> findByPriceBetween(Double price1, Double price2);

    Product create(Product product);

    Product update(Integer id, Product product);

    void delete(Integer id);

    List<Product> findTop5Seller();
}
