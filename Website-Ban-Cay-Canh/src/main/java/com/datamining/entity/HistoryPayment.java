package com.datamining.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "History_Payment")
public class HistoryPayment implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	private Double total;
	@JoinColumn(name = "payment_date")
	private Date paymentDate;
}
