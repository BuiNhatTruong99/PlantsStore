package com.datamining.DTO;

import java.util.Date;
import java.util.List;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.datamining.entity.Order;
import com.datamining.entity.OrderDetail;
import com.datamining.entity.OrderPayment;
import com.datamining.entity.OrderStatus;
import com.datamining.entity.Profile;
import lombok.Data;
@Data
public class OrderDTO {
	private Integer id;
	private String phone;
	private String address;
	@Temporal(TemporalType.DATE)
	private Date create_date = new Date();
	@Temporal(TemporalType.DATE)
	private Date update_date = new Date();
	private Double total;
	private OrderStatus status;
	private OrderPayment payment;
	private List<OrderDetail> details;
	private Profile profile;
	
    public static OrderDTO convert(Order order) {
    	OrderDTO orderDTO = new OrderDTO();
    	orderDTO.setId(order.getId());
		orderDTO.setPhone(order.getPhone());
    	orderDTO.setAddress(order.getAddress());
    	orderDTO.setCreate_date(order.getCreate_date());
    	orderDTO.setUpdate_date(order.getUpdate_date());
    	orderDTO.setTotal(order.getTotal());
    	orderDTO.setStatus(order.getStatus());
    	orderDTO.setPayment(order.getPayment());
    	orderDTO.setDetails(order.getOderDetails());
		orderDTO.setProfile(order.getProfile());
        return orderDTO;
}
}
