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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Product_Rate")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class , property = "id")
public class ProductRate implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Float rate;
	private String comment;
	@Temporal(TemporalType.TIMESTAMP)
	private Date create_date = new Date();

	// profile_id
	@ManyToOne
	@JoinColumn(name = "profile_id")
	private Profile user;

	// product_id
	private Integer product_id;
//	@JsonIgnore
//	@ManyToOne
//	@JoinColumn(name = "product_id")
//	private Product product;
}