package com.datamining.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "Product_Size")
public class ProductSize {
	@EmbeddedId
	private ProductSizeKey id;

	@ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

	@ManyToOne
    @MapsId("sizeId")
    @JoinColumn(name = "size_id")
    private Size size;

	private Double price;
}
