package com.datamining.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.datamining.DTO.ProductDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.var;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Products")
public class Product implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Double price;
	private Double quantity;
	private String image;
	private String description;
	private String status;
	private String url;
	private Boolean is_delete = false;
	@Temporal(TemporalType.DATE)
	private Date create_date = new Date();
	@Temporal(TemporalType.DATE)
	private Date update_date = new Date();

	// discount_id
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "discount_id")
	private Discount discount;

	// material_id
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "prod_material_id")
	ProductMaterial material;

	// categories_id
	@ManyToOne
	@JoinColumn(name = "categories_id")
	private Category categories;

	// Wish_List
	@JsonIgnore
	@ManyToMany(mappedBy = "product_like", fetch = FetchType.LAZY)
	@JsonBackReference
	private Set<Account> likes;

	// Order_Detail
	@JsonIgnore
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	private List<OrderDetail> oderDetails;

	// Product_Rate
	@JsonIgnore
	@OneToMany(mappedBy = "productRate", fetch = FetchType.LAZY)
	private List<ProductRate> productRates;

	// Product_Size
	@JsonIgnore
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	private Set<ProductSize> ratings;


	public static Product convert(ProductDTO productDTO) {
		// TODO Auto-generated method stub
		var product = new Product();
		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
		product.setPrice(productDTO.getPrice());
		product.setQuantity(productDTO.getQuantity());
		product.setImage(productDTO.getImage());
		product.setUrl(productDTO.getUrl());
		product.setDescription(productDTO.getDescription());
		product.setCreate_date(productDTO.getCreatedDate());
		product.setUpdate_date(productDTO.getUpdatedDate());
		product.setCategories(productDTO.getCategories());
		product.setStatus(productDTO.getStatus());
		return product;
	}
}
