package com.datamining.controller;

import com.datamining.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
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
		return "redirect:/home/index";
	}

	@RequestMapping("/login/error")
	public String loginError(Model model) {
		model.addAttribute("message", "Sai thông tin đăng nhập!");
		return "user/security/loginQR";
	}

	@RequestMapping("/logoff/success")
	public String logoffSuccess(Model model) {
		model.addAttribute("message", "Bạn đã đăng xuất!");
		return "user/security/loginQR";
	}
	
	@RequestMapping("/login/unauthoried")
	public String loginauthority(Model model) {
		model.addAttribute("message", "Bạn không đủ quyền!");
		return "redirect:/product/list";
	}
	@Autowired
	AccountService accountService;
	@RequestMapping("/oauth2/login/success")
	public String success(OAuth2AuthenticationToken oauth2 ){
		accountService.loginFromOAuth2(oauth2);
		return "redirect:/product/list";
	}
}
