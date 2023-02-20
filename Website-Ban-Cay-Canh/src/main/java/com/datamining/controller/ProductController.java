package com.datamining.controller;

import com.datamining.entity.Product;
import com.datamining.service.CategoryService;
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

    @Autowired
    CategoryService cService;

    @RequestMapping("/product/list")
    public String list(Model model,@RequestParam("cate") Optional<String> cate) {
        if(cate.isPresent()) {
            String id = cService.findIdByUrlEquals(cate.get());
            List<Product> list = pService.findByCategoryId(id);
            model.addAttribute("items", list);
        } else {
            List<Product> list = pService.findAll();
            model.addAttribute("items", list);
        }

        return "user/layout/index";
    }

    @RequestMapping("/product/detail/{url}")
    public String detail(Model model, @PathVariable("url") String url) {
        Product item = pService.findByUrlEquals(url);
        model.addAttribute("item", item);
        return "user/product/product-detail";
    }

    @RequestMapping("/product/list/search")
    public String search(Model model, @RequestParam("keyword") String keyword) {
        List<Product> list = pService.findByKeyword(keyword);
        model.addAttribute("items", list);
        return "user/layout/index";
    }
}
