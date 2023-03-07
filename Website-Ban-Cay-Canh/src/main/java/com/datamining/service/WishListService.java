package com.datamining.service;

import com.datamining.entity.Account;
import com.datamining.entity.Product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishListService {
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private ProductService productService;
	
    public void addProductoWishList(Integer accountId, Integer productId) {
        Account account = accountService.findById(accountId);
        //new RuntimeException("Account not found"));
        Product product = productService.findById(productId);
        //new RuntimeException("Course not found"));
        account.getLikedProducts().add(product);
        accountService.create(account);
    }
    
    public void removeCourseFromStudent(Integer accountId, Integer productId) {
    	Account account = accountService.findById(accountId);
    	Product product = productService.findById(productId);
    	account.getLikedProducts().remove(product);
    	accountService.create(account);
    }
}
