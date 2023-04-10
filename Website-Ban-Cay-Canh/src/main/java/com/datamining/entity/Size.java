package com.datamining.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
public class Size implements Serializable {
	@Id
	private String id;

	// Product_Size
	@OneToMany(mappedBy = "size")
	private Set<ProductSize> size;
}
