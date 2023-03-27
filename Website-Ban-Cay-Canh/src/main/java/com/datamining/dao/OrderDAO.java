package com.datamining.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.datamining.entity.Order;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderDAO extends JpaRepository<Order, Integer> {

}
