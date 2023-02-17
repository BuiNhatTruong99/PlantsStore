package com.datamining.rest;

import com.datamining.entity.Category;
import com.datamining.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryRest {
    @Autowired
    CategoryService categoryService;

    @GetMapping()
    public List<Category> getAllCategory(){
        return categoryService.findAll();
    }
}
