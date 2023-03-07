package com.datamining.rest;

import java.util.List;
import java.util.stream.Collectors;

import com.datamining.entity.ProductRate;
import com.datamining.service.ProductRateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.var;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/productRates")
public class ProductRateRest {
	
	@Autowired
	ProductRateService proRateService;
	
	@GetMapping("{id}")
    public ObjectResponse getAllProRateByID(@PathVariable("id") Integer id){
        var productRates = proRateService.findAllByProID(id);
        return new ObjectResponse("success", productRates, HttpStatus.OK.value());
    }
	
	@GetMapping
    public ObjectResponse getAllProRateByUser(@RequestParam("productId") Integer productId, 
    											@RequestParam("userId") Integer userId,
    											@RequestParam("rate") Float rate){
		List<ProductRate> productRates = null;
		if (userId == 0) {
			productRates = proRateService.findAllByFilter(productId, rate);
		}else if (rate == 0) {
			productRates = proRateService.findAllByFilter(productId, userId);
		} else {
			productRates = proRateService.findAllByFilter(productId, userId, rate);
		}
        
        return new ObjectResponse("success", productRates, HttpStatus.OK.value());
    }
	
	@PostMapping
	public ObjectResponse create(@RequestBody ProductRate productRate) {
		var proRate = proRateService.create(productRate);
		return new ObjectResponse("success", "Thêm đánh giá thành công", HttpStatus.OK.value());
	}
}
