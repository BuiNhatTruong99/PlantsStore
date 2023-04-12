package com.datamining.dao;

import com.datamining.entity.Product;
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


    //find product bettwen two price
    @Query(value="select * from Products p left join Product_Size ps on p.id = ps.product_id where (p.price >= ?1 and p.price <= ?2) "
    		+ "or (ps.price >= ?1 and ps.price <= ?2) order by p.price asc", nativeQuery = true)
    List<Product> findByPriceBetween(Double price1, Double price2);

    // Top 5 seller
    @Query(value="select p.* from Order_Detail d join Products p on p.id = d.product_id  group by p.id order by count(p.id) DESC LIMIT 5", nativeQuery = true)
    List<Product> findTop5Seller();
    
    @Query(value="select p.* from products p left join product_rate pr on p.id = pr.product_id group by p.id order by avg(rate) desc, count(*) desc", nativeQuery = true)
    List<Product> selectAllFeedbacks();

}
