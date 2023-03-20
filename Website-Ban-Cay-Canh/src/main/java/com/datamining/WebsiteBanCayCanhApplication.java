package com.datamining;

import com.datamining.entity.Product;
import com.datamining.service.CategoryService;
import com.datamining.service.ProductService;
import com.datamining.utils.StringUtils;
import lombok.var;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebsiteBanCayCanhApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebsiteBanCayCanhApplication.class, args);
    }



//    @Bean
//    CommandLineRunner run(ProductService productService) {
//        return args -> {
//            var products = productService.findAll();
//            products.forEach(p -> {
//                String name = p.getName();
//                String nameEn = StringUtils.removeAccent(name);
//                nameEn = nameEn.toLowerCase();
//                String urlSlug = nameEn.replaceAll(" ", "-");
//                String urlHtml = urlSlug + ".html";
//                p.setUrl(urlHtml);
//                productService.save(p);
//            });
//
//            var product = productService.findByUrlEquals("npk-20-10-15+-te");
//            System.out.println(product);
//
//
//        };
//    }

//    @Bean
//    CommandLineRunner run(CategoryService categoryService) {
//        return args -> {
//            var categories = categoryService.findAll();
//            categories.forEach(p -> {
//                String name = p.getName();
//                String nameEn = StringUtils.removeAccent(name);
//                nameEn = nameEn.toLowerCase();
//                String urlSlug = nameEn.replaceAll(" ", "-");
//                String urlHtml = urlSlug + ".html";
//                p.setUrl(urlHtml);
//                categoryService.save(p);
//            });
//       };
//   }
};