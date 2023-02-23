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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Orders")
public class Order implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String phone;
	private String address;
	private String note;
	private Double total;
	@Temporal(TemporalType.DATE)
	private Date create_date = new Date();
	@Temporal(TemporalType.DATE)
	private Date update_date = new Date();

	// Order_Detail
	@JsonIgnore
	@OneToMany(mappedBy = "order")
	private List<OrderDetail> oderDetails;

	// orderstatus_id
	@ManyToOne
	@JoinColumn(name = "orderstatus_id")
	private OrderStatus status;

	// ordermethod_id
	@ManyToOne
	@JoinColumn(name = "ordermethod_id")
	private OrderPayment payment;

	// profile_id
	@ManyToOne
	@JoinColumn(name = "profile_id")
	private Profile profile;
}
