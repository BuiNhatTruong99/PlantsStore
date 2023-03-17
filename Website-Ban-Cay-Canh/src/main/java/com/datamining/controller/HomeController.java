package com.datamining.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.datamining.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;


import com.datamining.dao.ProfileDAO;
import com.datamining.entity.Account;

import com.datamining.entity.Profile;
import com.datamining.service.AccountService;


@Controller
public class HomeController {

	@Autowired
	AccountService dao;
	
	@Autowired
	ProfileService pdao;
	
	@RequestMapping({"/", "/home/index"})
	public String home(Model model) {
		return "redirect:/product/list";
	}
	
	@RequestMapping("/contact")
	public String contact()
	{
		return "user/side/contact";
	}
	
	@GetMapping("/account/info")
	public String account_info(Model model,HttpServletRequest respon)
	{
		if(respon.getRemoteUser() == null)
		{
			return "redirect:/login/form";
		}else {
			Account us = dao.findByTk(respon.getRemoteUser());
//		System.out.println(us.getId());
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
	public String account_qrCode(Model model,HttpServletRequest respon)
	{

		String username = respon.getRemoteUser();
		Account us = dao.findByTk(username);
		String tk = us.getUsername();
		String pass = us.getPassword();
		model.addAttribute("username",tk);
		model.addAttribute("password",pass);

		return "user/security/genderQr";
	}
	
	@RequestMapping("/product/wish")
	public String product_wish()
	{
		return "user/product/wishlist";
	}
	
	@RequestMapping("/admin")
	public String admin(Model model,HttpServletRequest respon)
	{
		if(respon.getRemoteUser() == null)
		{
			return "redirect:/login/form";
		}else {
			Account us = dao.findByTk(respon.getRemoteUser());
			int usId = us.getId();
			model.addAttribute("user_id", usId);
			return "../static/admin/index";
		}

	}
}
