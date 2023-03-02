package com.datamining.controller;

import java.util.List;

import com.datamining.service.ProductService;
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
	@Autowired
	ProductService pService;
	@RequestMapping({"/", "/home/index"})
	public String home(Model model) {
//		List<Category> sp = dao.findAll();
//		model.addAttribute("items",sp);
		List<Product> list = pService.findAll();
		model.addAttribute("items", list);
		List<Product> bestSale = pService.findTop5Seller();
		model.addAttribute("bestSale", bestSale);
		return "user/layout/index";
	}
	
	@RequestMapping("/contact")
	public String contact()
	{
		return "user/side/contact";
	}
	
	@RequestMapping("/login")
	public String login()
	{
		return "user/security/loginQR";
	}

	@RequestMapping("/register")
	public String register()
	{
		return "user/security/register";
	}
	
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

	@RequestMapping("/gender")
	public String genderQr()
	{
		return "user/security/genderQr";
	}

//	@RequestMapping("/product/detail")
//	public String product_detail()
//	{
//		return "user/product/product-detail";
//	}
	
	@RequestMapping("/product/wish")
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
