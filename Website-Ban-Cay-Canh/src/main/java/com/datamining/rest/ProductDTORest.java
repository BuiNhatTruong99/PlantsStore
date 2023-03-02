package com.datamining.rest;

import com.datamining.DTO.ProductDTO;
import com.datamining.entity.Category;
import com.datamining.entity.Product;
import com.datamining.service.CategoryService;
import com.datamining.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dto/products")
public class ProductDTORest {
    private final ProductService productService;
    private final CategoryService categoryService;


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

    @PostMapping()
    public ResponseEntity<ObjectResponse> create(@RequestBody ProductDTO productDTO) {
        try {
//            var category = categoryService.findById(productDTO.getCategories().getId());
//            productDTO.setCategories(category);
            var product = Product.convert(productDTO);
            var newProduct = productService.create(product);
            var newProductDTO = ProductDTO.convert(newProduct);
            return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("success", newProductDTO, HttpStatus.OK.value()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ObjectResponse("error", e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObjectResponse> update(@PathVariable("id") Integer id, @RequestBody ProductDTO productDTO) {
        try {
            var product = Product.convert(productDTO);
            var newProduct = productService.update(id, product);
            var newProductDTO = ProductDTO.convert(newProduct);
            return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("success", newProductDTO, HttpStatus.OK.value()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ObjectResponse("error", e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ObjectResponse> delete(@PathVariable("id") Integer id) {
        try {
            productService.delete(id);
            return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("success", "Delete success", HttpStatus.OK.value()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ObjectResponse("error", e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

    @GetMapping("/search/{kw}")
    public ResponseEntity<ObjectResponse> search(@PathVariable("kw") String kw) {
        try {
            var products = productService.findByKeyword(kw);
            var productsDTO = products.stream()
                    .map(ProductDTO::convert)
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(new ObjectResponse("success", productsDTO, HttpStatus.OK.value()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ObjectResponse("error", e.getMessage(), HttpStatus.BAD_REQUEST.value()));
        }
    }

}
