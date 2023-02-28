package com.datamining.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name = "Categories")
public class Category implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Boolean status = false;
	private String url;

	@JsonIgnore
	@OneToMany(mappedBy = "categories", fetch = FetchType.LAZY)
	private List<Product> products = new ArrayList<>();

	public Category(Integer id) {
		this.id = id;
	}

	public Category() {
	}
}
