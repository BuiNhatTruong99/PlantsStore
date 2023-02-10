package com.datamining.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Embeddable
public class ProductSizeKey implements Serializable {
	@Column(name = "product_id")
	private Integer productId;

	@Column(name = "size_id")
	private String sizeId;

	// standard constructors, getters, and setters
    // hashcode and equals implementation
}
