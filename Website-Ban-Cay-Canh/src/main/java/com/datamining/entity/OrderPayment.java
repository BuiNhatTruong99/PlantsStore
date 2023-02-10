package com.datamining.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Order_Method_Payment")
public class OrderPayment implements Serializable {
	@Id
	private Integer id;
	private String name;

	@OneToMany(mappedBy = "payment")
	private List<Order> orders;
}
