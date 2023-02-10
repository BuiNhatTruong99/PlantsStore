package com.datamining.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

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
	private String image;
	private String description;
	private String status;
	private Boolean is_delete = false;
	@Temporal(TemporalType.DATE)
	private Date create_date = new Date();
	@Temporal(TemporalType.DATE)
	private Date update_date = new Date();

	// discount_id
	@ManyToOne
	@JoinColumn(name = "discount_id")
	private Discount discount;

	// material_id
	@ManyToOne
	@JoinColumn(name = "material_id")
	private ProductMaterial material;

	// categories_id
	@ManyToOne
	@JoinColumn(name = "categories_id")
	private Category categories;

	// Wish_List
	@ManyToMany(mappedBy = "product_like")
	@JsonBackReference
	private Set<Account> likes;

	// Order_Detail
	@OneToMany(mappedBy = "product")
	private List<OrderDetail> oderDetails;

	// Product_Rate
	@OneToMany(mappedBy = "productRate")
	private List<ProductRate> productRates;

	// Product_Size
	@OneToMany(mappedBy = "product")
	private Set<ProductSize> ratings;
}
