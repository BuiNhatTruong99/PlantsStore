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

	@RequestMapping({"/", "/home/index"})
	public String home(Model model) {
//		List<Category> sp = dao.findAll();
//		model.addAttribute("items",sp);
		return "redirect:/product/list";
	}
	
	@RequestMapping("/contact")
	public String contact()
	{
		return "user/side/contact";
	}
	
//	@RequestMapping("/login")
//	public String login()
//	{
//		return "user/security/loginQR";
//	}
	
	@RequestMapping("/cart/detail")
	public String cart_detail()
	{
		return "user/cart/cart-detail";
	}
	
	@RequestMapping("/account/info")
	public String account_info()
	{
		return "user/security/my-account";
	}
	
//	@RequestMapping("/product/detail")
//	public String product_detail()
//	{
//		return "user/product/product-detail";
//	}
	
	@RequestMapping("/wishlist")
	public String product_wish()
	{
		return "user/product/wishlist";
	}
	
	@RequestMapping("/admin")
	public String admin()
	{
		return "../static/admin/index";
	}
}
