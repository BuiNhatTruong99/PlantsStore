package com.datamining.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
public class Profile implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String fullname;
	private String avatar;
	private Boolean gender;
	@Temporal(TemporalType.DATE)
	private Date birthday;
	private String email;
	private String phone;
	private String address;

	// Account
	@OneToOne(cascade = CascadeType.ALL) // 1 account chi co 1 profile duy nhat
	@JoinColumn(name = "user_id", referencedColumnName = "id")
    private Account account;
	
	// Product_Rate
	@JsonBackReference
	@OneToMany(mappedBy = "user")
	private List<ProductRate> feedbacks;

	// Order
	@JsonIgnore
	@OneToMany(mappedBy = "profile")
	List<Order> order;
}
