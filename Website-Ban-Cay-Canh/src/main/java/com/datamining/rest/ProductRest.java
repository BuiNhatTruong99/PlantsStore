package com.datamining.rest;


import java.util.stream.Collectors;

import com.datamining.DTO.ProductDTO;
import com.datamining.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.var;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/products")
public class ProductRest {
	
	@Autowired
	ProductService productService;
	
	@GetMapping()
    public ObjectResponse getAll() {
        var products = productService.findAll();
        return new ObjectResponse("success", products, HttpStatus.OK.value());
    }
	
    @GetMapping("/feedbacks")
    public ObjectResponse getAllFeedbacks() {
        var products = productService.selectAllFeedbacks();
        return new ObjectResponse("success", products, HttpStatus.OK.value());
    }
    
    @GetMapping("/search/{kw}")
    public ResponseEntity<ObjectResponse> search(@PathVariable("kw") String kw) {
        try {
            var products = productService.findByKeyword(kw);
            return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("success", products, HttpStatus.OK.value()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ObjectResponse("error", e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }
  
    
}
