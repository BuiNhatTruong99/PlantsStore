package com.datamining.rest;

import com.datamining.DTO.ProductDTO;
import com.datamining.entity.Product;
import com.datamining.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dto/products")
public class ProductDTORest {
    private final ProductService productService;

    @GetMapping()
    public ObjectResponse getAll() {
        var products = productService.findAll();
        var productsDTO = products.stream()
                .map(ProductDTO::convert)
                .collect(Collectors.toList());
        return new ObjectResponse("success", productsDTO, HttpStatus.OK.value());
    }

    @GetMapping("{id}")
    public ObjectResponse getOne(@PathVariable("id") Integer id) {
        var product = productService.findById(id);
        var productDTO = ProductDTO.convert(product);
        return new ObjectResponse("success", productDTO, HttpStatus.OK.value());
    }
}
