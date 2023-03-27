package com.datamining.DTO;

import com.datamining.entity.Category;
import com.datamining.entity.Product;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class ProductDTO {
    private Integer id;
    private String name;
    private Double price;
    private Integer quantity;
    private String image;
    private String description;
    private String url;
    private Date createdDate;
    private Date updatedDate;
    private Category categories;
    private Boolean status;


    public static ProductDTO convert(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setPrice(product.getPrice());
        productDTO.setQuantity(product.getQuantity());
        productDTO.setImage(product.getImage());
        productDTO.setUrl(product.getUrl());
        productDTO.setDescription(product.getDescription());
        productDTO.setCreatedDate(product.getCreate_date());
        productDTO.setUpdatedDate(product.getUpdate_date());
        productDTO.setCategories(product.getCategories());
        productDTO.setStatus(product.getStatus());
        return productDTO;
    }
}
