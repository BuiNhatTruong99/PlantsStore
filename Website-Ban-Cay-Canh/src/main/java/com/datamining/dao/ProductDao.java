package com.datamining.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datamining.entity.Product;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer> {

    @Query(value="SELECT * FROM products WHERE categories_id=?1", nativeQuery = true)
    List<Product> findByCategoryId(String cid);


    Product findByUrlEquals(String url);

    @Query(value="SELECT * FROM Products p WHERE p.name like %?1%", nativeQuery = true)
    List<Product> findByKeyword(String keyword);
    ;

    //find product bettwen two price
    @Query(value="SELECT * FROM Products p WHERE p.price >= ?1 AND p.price <= ?2", nativeQuery = true)
    List<Product> findByPriceBetween(Double price1, Double price2);
}
