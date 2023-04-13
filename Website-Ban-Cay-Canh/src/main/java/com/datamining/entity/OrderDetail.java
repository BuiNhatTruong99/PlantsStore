package com.datamining.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Order_Detail")
public class OrderDetail implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Double price;
	private Integer quantity;
	private Float sale;

	// order_id
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	// product_id
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
}
