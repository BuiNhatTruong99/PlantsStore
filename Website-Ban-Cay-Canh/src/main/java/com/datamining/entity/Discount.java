package com.datamining.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Discounts")
public class Discount implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String title;
	private Float scale;
	@Temporal(TemporalType.DATE)
	private Date create_date = new Date();
	@Temporal(TemporalType.DATE)
	private Date update_date = new Date();

	// staff_id
	@ManyToOne
	@JoinColumn(name = "staff_id")
	private Account staff;

	// Product
	@OneToMany(mappedBy = "discount")
	private List<Product> productDiscounts;

}
