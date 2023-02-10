package com.datamining.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Product_Rate")
public class ProductRate implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Float rate;
	private String comment;
	@Temporal(TemporalType.DATE)
	private Date create_date = new Date();

	// account_id
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account user;

	// product_id
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product productRate;
}
