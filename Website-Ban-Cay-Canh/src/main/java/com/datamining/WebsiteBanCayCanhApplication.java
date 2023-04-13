package com.datamining;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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