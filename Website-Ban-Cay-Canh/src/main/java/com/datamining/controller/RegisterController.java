package com.datamining.controller;

import com.datamining.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
    @Autowired
    AccountService accountService;

    @PostMapping("/registered")
    public String register() {
        return "redirect:/register";
    }
}
