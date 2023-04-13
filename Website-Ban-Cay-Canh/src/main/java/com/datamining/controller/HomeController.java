package com.datamining.controller;


import com.datamining.service.ProductService;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;


import com.datamining.entity.Account;
import com.datamining.entity.Product;
import com.datamining.service.AccountService;


@Controller
public class HomeController {
	@Autowired
	AccountService aService;
	
	@Autowired
	ProductService pService;

	
	@RequestMapping({"/", "/home/index"})
	public String home(Model model, HttpServletRequest req,
					   @RequestParam("page") Optional<Integer> page) {
		Pageable pageable = PageRequest.of(page.orElse(0), 6); // 6 product/1 page

		Page<Product> list = pService.findAllByPage(pageable);
//		List<Product> list = pService.findAll();
		model.addAttribute("items", list);
		List<Product> bestSale = pService.findTop5Seller();
		model.addAttribute("bestSale", bestSale);

		if(req.getRemoteUser() != null) {
			Account us = aService.findByTk(req.getRemoteUser());
			int usId = us.getId();
			model.addAttribute("user_id", usId);
		}

		return "user/layout/index";
	}
	
	@RequestMapping("/contact")
	public String contact()
	{
		return "user/side/contact";
	}
	
	@GetMapping("/account/info")
	public String account_info(Model model,HttpServletRequest req)
	{
		if(req.getRemoteUser() == null)
		{
			return "redirect:/login/form";
		}else {
			Account us = aService.findByTk(req.getRemoteUser());
			int usId = us.getId();
			model.addAttribute("user_id", usId);
			return "user/security/my-account";
		}
	}

	@RequestMapping("/cart/detail")
	public String cart_detail()
	{
		return "user/cart/cart-detail";
	}
	

	
	@RequestMapping("/account/Qrcode")
	public String account_qrCode(Model model,HttpServletRequest req)
	{

		String username = req.getRemoteUser();
		Account us = aService.findByTk(username);
		String tk = us.getUsername();
		String pass = us.getPassword();
		model.addAttribute("username",tk);
		model.addAttribute("password",pass);

		return "user/security/genderQr";
	}
	
	@RequestMapping("/wishlist")
	public String product_wish(Model model, HttpServletRequest req)
	{
		if(req.getRemoteUser() == null)
		{
			return "redirect:/login/form";
		}else {
			Account us = aService.findByTk(req.getRemoteUser());
			int usId = us.getId();
			model.addAttribute("user_id", usId);
			return "user/product/wishlist";
		}
	}
	
	@RequestMapping("/register")
	public String register()
	{
		return "user/security/register";
	}
	
	@RequestMapping("/admin")
	public String admin(Model model,HttpServletRequest respon)
	{
		if(respon.getRemoteUser() == null)
		{
			return "redirect:/login/form";
		}else {
			Account us = aService.findByTk(respon.getRemoteUser());
			int usId = us.getId();
			model.addAttribute("user_id", usId);
			return "../static/admin/index";
		}

	}
}
