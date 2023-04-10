package com.datamining.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "staff_id")
	private Account staff;

	// Product
	@JsonIgnore
	@OneToMany(mappedBy = "discount", fetch = FetchType.LAZY)
	private List<Product> productDiscounts;

}
