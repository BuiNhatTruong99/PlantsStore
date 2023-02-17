package com.datamining.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin("*")
public class SecurityController {
	@RequestMapping("/login/form")
	public String loginForm(Model model) {
//		model.addAttribute("message", "Vui lòng đăng nhập!");
		return "user/security/loginQR";
	}

	@RequestMapping("/login/success")
	public String loginSuccess(Model model) {
		model.addAttribute("message", "Đăng nhập thành công!");
		return "redirect:/product/list";
	}

	@RequestMapping("/login/error")
	public String loginError(Model model) {
		model.addAttribute("message", "Sai thông tin đăng nhập!");
		return "user/security/loginQR";
	}

	@RequestMapping("/login/unauthoried")
	public String loginauthority(Model model) {
		model.addAttribute("message", "Bạn không đủ quyền!");
		return "redirect:/product/list";
	}

}
