package com.datamining.controller;

import com.datamining.entity.Product;
import com.datamining.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    @Autowired
    ProductService pService;

    @RequestMapping("/product/list")
    public String list(Model model,@RequestParam("cid") Optional<String> cid) {
        if(cid.isPresent()) {
            List<Product> list = pService.findByCategoryId(cid.get());
            model.addAttribute("items", list);
        } else {
            List<Product> list = pService.findAll();
            model.addAttribute("items", list);
        }

        return "user/layout/index";
    }

    @RequestMapping("/product/detail/{id}")
    public String detail(Model model, @PathVariable("id") Integer id) {
        Product item = pService.findById(id);
        model.addAttribute("item", item);
        return "user/product/product-detail";
    }
}
