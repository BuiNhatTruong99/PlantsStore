package com.datamining.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.datamining.DTO.OrderDTO;

import com.datamining.entity.Order;
import com.datamining.entity.OrderDetail;

import com.datamining.service.OrderService;
import com.fasterxml.jackson.databind.JsonNode;

import lombok.RequiredArgsConstructor;
import lombok.var;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderRest {
	private final OrderService orderService;
	

	@GetMapping()
	public ObjectResponse getAll() {
		var orders = orderService.findAll();
		var orderDTO = orders.stream().map(OrderDTO::convert).collect(Collectors.toList());
		return new ObjectResponse("ok", orderDTO, HttpStatus.OK.value());
	}



	
//	@GetMapping("/detailById")
//	public List<OrderDetail> findByOrderId(@RequestParam("id") Integer idOrder){
//		return orderDetailDAO.findByOrderId(idOrder);
//	}

	}

