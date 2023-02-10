package com.datamining.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.datamining.dao.CategoryDAO;
import com.datamining.dao.ProductDao;
import com.datamining.entity.Category;
import com.datamining.entity.Product;

@Controller
public class HomeController {
	
//	@Autowired
//	CategoryDAO dao;
	
//	<div th:each="p:${items}" >
//	<h2 th:utext="${p.name}"></h2>
//	
//</div>
	
	@RequestMapping({"/", "/home/index"})
	public String home(Model model) {
//		List<Category> sp = dao.findAll();
//		model.addAttribute("items",sp);
		return "user/layout/index";
	}
	
	@RequestMapping("/contact")
	public String contact()
	{
		return "user/layout/contact";
	}
	
	@RequestMapping("/login")
	public String login()
	{
		return "user/security/login";
	}
	
	@RequestMapping("/cart/detail")
	public String cart_detail()
	{
		return "user/layout/cart-detail";
	}
	
	@RequestMapping("/account/info")
	public String account_info()
	{
		return "user/security/my-account";
	}
	
	@RequestMapping("/product/detail")
	public String product_detail()
	{
		return "user/product/product-detail";
	}
	
	@RequestMapping("/product/wish")
	public String product_wish()
	{
		return "user/product/wishlist";
	}
	
	@RequestMapping("/admin")
	public String admin()
	{
		return "../admin/index";
	}
}
